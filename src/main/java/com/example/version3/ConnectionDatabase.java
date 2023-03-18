package com.example.version3;

import java.sql.*;
import java.sql.DriverManager;

public class ConnectionDatabase {
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/version1db","root","");
        if(conn!=null){
            System.out.println("connected");
        }
        return conn;
    }
}



