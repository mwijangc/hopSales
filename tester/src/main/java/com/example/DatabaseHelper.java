package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// literally taken from discussion
public class DatabaseHelper {

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", dbCredentials.USER, dbCredentials.PASS);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
