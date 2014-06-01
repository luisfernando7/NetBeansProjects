/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pegasus.petsoft.dal;

import com.pegasus.petsoft.model.Services;
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
 * @author Marina Silva
 */
public class ServicesDAO implements IRepository<Services> {

   
    @Override
    public int insert(Services t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("INSERT INTO [Service] ([type],[price]) VALUES (?,?)");
            st.setString(1, t.getType());
            st.setFloat(2, t.getPrice());
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PetsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Services retrieve(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [type], [price] FROM [Service] WHERE [id] = " + id);
            Services s = new Services();
            while (result.next()) {
                s.setType(result.getString("type"));
                s.setPrice(result.getFloat("price"));
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Services> retrieveAll() {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT [id], [type], [price] FROM [Service]");
            ArrayList<Services> servicesList = new ArrayList<>();
            while (result.next()) {
                Services s = new Services();
                s.setId(result.getInt("id"));
                s.setType(result.getString("type"));
                s.setPrice(result.getFloat("price"));
                servicesList.add(s);                
            }
            return servicesList;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public boolean update(Services t) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("UPDATE [Service] SET [type] = ?, [price] = ? WHERE [id] = " + t.getId());
            st.setString(1, t.getType());
            st.setFloat(2, t.getPrice());
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionFactory.GetConnect();
            PreparedStatement st = connection.prepareStatement("DELETE FROM [Service] WHERE [id] = " + id);
            return st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
