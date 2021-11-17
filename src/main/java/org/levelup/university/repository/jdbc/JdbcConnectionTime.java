package org.levelup.university.repository.jdbc;

import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.jdbc.DataBaseService;
import org.levelup.university.jdbc.JdbcDatabaseInvokationHandler;
import org.levelup.university.jdbc.JdbcDatabaseServiceImpl;
import org.levelup.university.jdbc.JdbcService;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnectionTime {

    private final JdbcDatabaseServiceImpl jdbcDatabaseService;

    public JdbcConnectionTime(JdbcDatabaseServiceImpl jdbcDatabaseService) {

        this.jdbcDatabaseService = jdbcDatabaseService;
    }

    public void openConnectionfromPool() {
        JdbcService proxyJdbcDatabaseService = (JdbcService) Proxy.newProxyInstance(
                jdbcDatabaseService.getClass().getClassLoader(),
                jdbcDatabaseService.getClass().getInterfaces(),
                new JdbcDatabaseInvokationHandler(jdbcDatabaseService));

        Connection connection = proxyJdbcDatabaseService.openConnection();


    }


}
