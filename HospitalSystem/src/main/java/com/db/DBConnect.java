package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static Connection connection;

    public static final String URL = "jdbc:mysql://localhost:3306/system_hospital";
    public static final String USER = "root";
    public static final String PASSWORD = "Qwerty123!";

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
