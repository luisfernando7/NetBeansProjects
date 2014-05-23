/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegasus.petsoft.dal;
import com.pegasus.petsoft.model.*;
import java.sql.Connection;
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

    @Override
    public boolean insert(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address retrieve(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Address> retrieveAll() {
        try {
            //TODO: Implementar o restante.
            Connection connectionLocal = ConnectionFactory.GetConnect(); 
            Statement localStatement = connectionLocal.createStatement();
            ArrayList<Address> addressList = new ArrayList<Address>();
            ResultSet result = localStatement.executeQuery("SELECT [id],[cep],[city],[neighborhood],[street] FROM [Address]");
            while (result.next()) {
                Address a = new Address();
                a.setId(result.getInt("id"));
                a.setCep(result.getInt("cep"));
                a.setCity(result.getString("city"));
                a.setneighborhood(result.getString("neighborhood"));
                a.setStreet(result.getString("street"));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
