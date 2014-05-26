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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author luis.moraes
 */
public class AddressDAO implements IRepository<Address>{

    @Override //ver com Luis
    public int insert(Address t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("INSERT INTO [Address] ([street], [neighborhood], [city], [cep], [complement], [uf], [number]) VALUES (?,?,?,?,?,?,?) @@IDENTITY");
            st.setString(1, t.getStreet());
            st.setString(2, t.getneighborhood());
            st.setString(3, t.getCity());
            st.setInt(4, t.getCep());
            st.setInt(5, t.getNumber());
            //ver enum
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override //ver com Luis
    public Address retrieve(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [street], [neighborhood], [city], [cep], [complement], [uf], [number] FROM [Address] WHERE [id] = " + id);
            Address a = new Address();
            while (result.next()) {                
                a.setStreet(result.getString("street"));
                a.setneighborhood(result.getString("neighborhood"));
                a.setCity(result.getString("city"));
                a.setCep(result.getInt("cep"));
                a.setComplement(result.getString("complement"));
                a.setNumber(result.getInt("number"));
                //ver enum
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Address> retrieveAll() {
        try {
            //TODO: Implementar o restante.
            Connection connectionLocal = ConnectionFactory.GetConnect(); 
            Statement localStatement = connectionLocal.createStatement();
            ArrayList<Address> addressList = new ArrayList<Address>();
            ResultSet result = localStatement.executeQuery("SELECT [id],[cep],[city],[neighborhood],[street], [UF], [number] FROM [Address]");
            while (result.next()) {
                Address a = new Address();
                a.setId(result.getInt("id"));
                a.setCep(result.getInt("cep"));
                a.setCity(result.getString("city"));
                a.setneighborhood(result.getString("neighborhood"));
                a.setStreet(result.getString("street"));
                a.setNumber(result.getInt("number"));
                //falta uf
                addressList.add(a);
            }
            return addressList;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }

    @Override //ver com Luis
    public boolean update(Address t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("UPDATE [Address] SET [street] = ?, [neighborhood] = ?, [city] = ?, [cep] = ?, [complement] = ?, [uf] = ?, [number] = ? WHERE [id] = " + t.getId());
            st.setString(1, t.getStreet());
            st.setString(2, t.getneighborhood());
            st.setString(3, t.getCity());
            st.setInt(4, t.getCep());
            st.setString(5, t.getComplement());
            st.setInt(6, t.getNumber());
            //ver enum
            return st.execute();                    
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override //ver com Luis
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("DELETE FROM [Address] WHERE [id] = " + id);
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
