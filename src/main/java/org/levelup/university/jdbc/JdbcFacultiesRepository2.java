package org.levelup.university.jdbc;

import java.sql.Connection;

public class JdbcFacultiesRepository2 {
    private Connection pool;
    private final DataBaseService dbService;

    public JdbcFacultiesRepository2(Connection pool, DataBaseService dbService) {
        this.pool = pool;
        this.dbService = dbService;
    }

    public void getAllFaculties(){
       // try(Connection connection = db)
    }
}
