/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pegasus.petsoft.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marina.silva
 */
public class ConnectionFactory {

    static Connection connection;

    public static Connection GetConnect() throws SQLException {
        if (connection != null) {
            return connection;
        } else {
            try {
                System.out.println("Test");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=DBPetSoft;"
                        + "user=sa;password=P@55W0RD;MultipleActiveResultSets=true;";
                connection = DriverManager.getConnection(connectionURL);
                System.out.println("Conexão efetuada com sucesso");
                return connection;

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                throw new SQLException();
            }

        }
    }
}
