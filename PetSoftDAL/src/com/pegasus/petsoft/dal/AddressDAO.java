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
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis.moraes
 */
public class AddressDAO implements IRepository<Address> {

    @Override
    public int insert(Address t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            ResultSet generatedKeys;
            PreparedStatement st = connection.prepareStatement("INSERT INTO [Address] ([street], [neighborhood], [city], [cep], [complement], [uf], [number]) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, t.getStreet());
            st.setString(2, t.getneighborhood());
            st.setString(3, t.getCity());
            st.setInt(4, t.getCep());
            st.setInt(5, t.getNumber());
            st.setString(6, t.getUf().toString());
            st.setInt(7, t.getNumber());
            st.executeUpdate();
            generatedKeys = st.getGeneratedKeys();
            int id = -1;
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
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
                a.setUf(UF.valueOf(result.getString("uf").toUpperCase()));
                a.setNumber(result.getInt("number"));
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
            Connection connectionLocal = ConnectionFactory.GetConnect();
            Statement localStatement = connectionLocal.createStatement();
            ArrayList<Address> addressList = new ArrayList<Address>();
            ResultSet result = localStatement.executeQuery("SELECT [id],[cep],[city],[neighborhood],[street], [uf], [number] FROM [Address]");
            while (result.next()) {
                Address a = new Address();
                a.setId(result.getInt("id"));
                a.setCep(result.getInt("cep"));
                a.setCity(result.getString("city"));
                a.setneighborhood(result.getString("neighborhood"));
                a.setStreet(result.getString("street"));
                a.setUf(UF.valueOf(result.getString("uf").toUpperCase()));
                a.setNumber(result.getInt("number"));
                addressList.add(a);
            }
            return addressList;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean update(Address t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("UPDATE [Address] SET [street] = ?, [neighborhood] = ?, [city] = ?, [cep] = ?, [complement] = ?, [uf] = ?, [number] = ? WHERE [id] = " + t.getId());
            st.setString(1, t.getStreet());
            st.setString(2, t.getneighborhood());
            st.setString(3, t.getCity());
            st.setInt(4, t.getCep());
            st.setString(5, t.getComplement());
            st.setString(6, t.getUf().toString());
            st.setInt(7, t.getNumber());
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
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
