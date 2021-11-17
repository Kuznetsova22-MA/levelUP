package org.levelup.university;

import org.hibernate.SessionFactory;
import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.configuration.HibernateConfiguration;
import org.levelup.university.jdbc.DataBaseService;
import org.levelup.university.jdbc.JdbcDatabaseInvokationHandler;
import org.levelup.university.jdbc.JdbcDatabaseServiceImpl;
import org.levelup.university.jdbc.JdbcService;
import org.levelup.university.reflact.AnnotationConfigurationPropertiesProcessor;
import org.levelup.university.reflact.ConfigurationPropertiesProcessor;
import org.levelup.university.repository.jdbc.JdbcConnectionTime;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class HomeWorkMain {
    public static void main(String[] args) {

        String configurationFilename = "database.properties";
       ConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);

        JdbcDatabaseServiceImpl jdbcDatabaseService = new JdbcDatabaseServiceImpl(DatabaseConfiguration.getInstance());
        JdbcConnectionTime connectionTime = new JdbcConnectionTime(jdbcDatabaseService);
        jdbcDatabaseService.fillPool();
        connectionTime.openConnectionfromPool();

    }
}
