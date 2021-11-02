package org.levelup.university.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseService {
    public Connection openConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/postgres", //connection url
                    "postgres", //database login
                    "admin" //database password
            );

        }catch (SQLException exc){
            System.out.println("Couldn't connect to database");
            throw new RuntimeException(exc);
        }
    }

}
