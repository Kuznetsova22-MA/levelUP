package org.levelup.university.jdbc;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseService {
    private DatabaseConfiguration dbConfiguration;
    private ConnectionPool connectionPool;

    public DataBaseService(DatabaseConfiguration dbConfiguration, ConnectionPool connectionPool) {
        this.dbConfiguration = dbConfiguration;
        this.connectionPool = connectionPool;
    }
   /* public  void fillPool(){
        for(int = 0; i<dbConfiguration. )
    }*/
    private Connection createConnection(){
        try{
            return DriverManager.getConnection(
                    dbConfiguration.getUrl(),
                    dbConfiguration.getLogin(),
                    dbConfiguration.getPassword()
            );
        } catch (SQLException exception) {
            throw new RuntimeException();
        }
    }

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

    /*
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
    */
}
