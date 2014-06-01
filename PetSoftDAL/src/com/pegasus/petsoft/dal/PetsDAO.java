/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pegasus.petsoft.dal;

import com.pegasus.petsoft.model.Client;
import com.pegasus.petsoft.model.Pets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis.moraes
 */
public class PetsDAO implements IRepository<Pets> {

    /**
     *
     * @param owner Objeto do tipo Client
     * @return Lista de Pets
     * @throws SQLException
     */
    public ArrayList<Pets> retrieveAllByClient(Client owner) throws SQLException {
        try {
            Statement localStatement = ConnectionFactory.GetConnect().createStatement();
            ResultSet result = localStatement.executeQuery("SELECT [id],[name],[breed],[bornDate] FROM [Pet] where [client_id] =" + owner.getId());
            ArrayList<Pets> petList = new ArrayList<>();
            while (result.next()) {
                Pets p = new Pets();
                p.setId(result.getInt("id"));
                p.setName(result.getString("name"));
                p.setBreed(result.getString("breed"));
                String[] date = result.getString("bornDate").split("-");
                int day = Integer.parseInt(date[2]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[0]);
                p.setBornDate(new GregorianCalendar(year, month, day));
                p.setOwner(owner);
                petList.add(p);
            }
            return petList;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override //ver com Luis
    public int insert(Pets t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("INSERT INTO [Pet] ([name], [breed], [bornDate], [Client_id]) VALUES (?,?,?)");
            st.setString(1, t.getName());
            st.setString(2, t.getBreed());
            st.setString(3, String.format("%s-%s-%s", t.getBornDate().getInstance().YEAR, t.getBornDate().getInstance().MONTH, t.getBornDate().getInstance().DAY_OF_MONTH));
            st.setInt(4, t.getOwner().getId());
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override // ver com  Luis
    public Pets retrieve(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [name], [breed], [bornDate], [Client_id] FROM [Pet] WHERE [id] = " + id);
            Pets p = new Pets();
            while (result.next()) {                
                p.setName(result.getString("name"));
                p.setBreed(result.getString("breed"));
                p.setBornDate(parseGregorianCalendar(result.getString("bornDate")));
                //p.setOwner(); nao sei
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override // ver com  Luis
    public ArrayList<Pets> retrieveAll() {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [id], [name], [breed], [bornDate], [Client_id] FROM [Pet]");
            ArrayList<Pets> petsList = new ArrayList<>();
            while (result.next()) {                
                Pets p = new Pets();
                p.setId(result.getInt("id"));
                p.setName(result.getString("name"));
                p.setBreed(result.getString("breed"));
                p.setBornDate(parseGregorianCalendar(result.getString("bornDate")));
                //p.setOwner();
                petsList.add(p);
            }
            return petsList;
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }

    @Override // ver com  Luis
    public boolean update(Pets t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("UPDATE [Pet] SET [name] = ?, [breed] = ?, [bornDate] = ?, [Client_id] = ? WHERE [id] = " + t.getId());
            st.setString(1, t.getName());
            st.setString(2, t.getBreed());
            st.setString(3, String.format("%s-%s-%s",t.getBornDate().getInstance().YEAR, t.getBornDate().getInstance().MONTH, t.getBornDate().getInstance().DAY_OF_MONTH));
            st.setInt(4, t.getOwner().getId());
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }

    @Override // ver com Luis
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("DELETE FROM [Pet] WHERE [id] = " + id);
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
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
