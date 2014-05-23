/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pegasus.petsoft.dal;

import com.pegasus.petsoft.model.Client;
import com.pegasus.petsoft.model.Pets;
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

    @Override
    public boolean insert(Pets t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pets retrieve(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pets> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Pets t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
