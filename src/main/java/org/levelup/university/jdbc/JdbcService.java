package org.levelup.university.jdbc;

import org.levelup.university.reflact.ConnectionTime;

import java.sql.Connection;

public interface JdbcService {
    @ConnectionTime
    public Connection openConnection();
}
