/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pegasus.petsoft.dal;

import com.pegasus.petsoft.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis.moraes
 */
public class ClientDAO implements IRepository<Client> {

    private AddressDAO addressDAO;
    private PetsDAO petsDAO;

    public ClientDAO() {
        addressDAO = new AddressDAO();
        petsDAO = new PetsDAO();
    }

    @Override
    public int insert(Client t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("INSERT INTO [Client]([name],[bornDate],[address_id],[phone],[celphone]) VALUES (?,?,?,?,?)");
            st.setString(1, t.getName());
            st.setString(2, String.format("%s-%s-%s", t.getBornDate().getInstance().YEAR, t.getBornDate().getInstance().MONTH, t.getBornDate().getInstance().DAY_OF_MONTH));
            int id_endereco = addressDAO.insert(t.getAddress());
            st.setInt(3, id_endereco);//E se o obj ainda não estiver no bd?
            
            st.setInt(4, t.getPhone());
            st.setInt(5, t.getCelphone());
            return st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Client retrieve(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [name],[bornDate],[address_id],[phone],[celphone] FROM [Client] WHERE [id] = " + id);
            Client c = new Client();
            while (result.next()) {
                c.setId(id);
                c.setName(result.getString("name"));
                c.setBornDate(parseGregorianCalendar(result.getString("bornDate")));
                c.setAddress(addressDAO.retrieve(result.getInt("address_id")));
                c.setPhone(result.getInt("phone"));
                c.setCelphone(result.getInt("celphone"));
                c.setPets(petsDAO.retrieveAllByClient(c));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Client> retrieveAll() {
        try {
            //TODO: Implementar o restante.
            Connection connectionLocal = ConnectionFactory.GetConnect();
            Statement localStatement = connectionLocal.createStatement();
            ArrayList<Client> clientsList = new ArrayList<>();
            ResultSet result = localStatement.executeQuery("SELECT [id],[name],[bornDate],[address_id],[phone],[celphone] FROM [Client]");
            while (result.next()) {
                Client c = new Client();
                c.setId(result.getInt("id"));
                c.setName(result.getString("name"));
                String[] date = result.getString("bornDate").split("-");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                c.setBornDate(new GregorianCalendar(year, month, day));
                c.setAddress(addressDAO.retrieve(result.getInt("address_id")));//Implementar todos os metodos do repositorio de endereços.
                c.setPhone(result.getInt("phone"));
                c.setCelphone(result.getInt("celphone"));
                c.setPets(petsDAO.retrieveAllByClient(c));
                clientsList.add(c);
            }
            return clientsList;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override //ver com Luís
    public boolean update(Client t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("UPDATE [Client] SET [name] = ?, [borndate] = ?, [address_id] = ?,"
                    + " [phone] = ?, [celphone] = ? WEHRE [id] = " + t.getId());
            st.setString(1, t.getName());
            st.setString(2, String.format("%s-%s-%s", t.getBornDate().getInstance().YEAR, t.getBornDate().getInstance().MONTH, t.getBornDate().getInstance().DAY_OF_MONTH));
            st.setInt(3, t.getAddress().getId());
            st.setInt(4, t.getPhone());
            st.setInt(5, t.getCelphone());
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override //ver com Luís
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("DELETE FROM [Client] WHERE [id] = " + id);
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private GregorianCalendar parseGregorianCalendar(String d) {
        String[] date = d.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        return new GregorianCalendar(year, month, day);
    }
}
