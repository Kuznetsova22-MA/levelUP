package org.levelup.university.repository.jdbc;

import org.levelup.university.jdbc.DataBaseService;
import org.levelup.university.jdbc.JdbcDatabaseServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFacultiesRepository2 {

    private final DataBaseService dbService;

    public JdbcFacultiesRepository2(DataBaseService dbService) {
        this.dbService = dbService;
    }


    public void getAllFaculties() {

        //теперь соединение берется из Connection pool
        try(Connection connection = dbService.openConnection()){

        } catch (SQLException exception) {
            System.out.println();
        }
    }

}
