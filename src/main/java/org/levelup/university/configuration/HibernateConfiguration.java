package org.levelup.university.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.levelup.university.damain.University;
import org.postgresql.Driver;


import java.util.HashMap;
import java.util.Map;

public class HibernateConfiguration {

    private static SessionFactory factory;


    // Настройка Hibernate
    public static void configure(DatabaseConfiguration dbConfiguration) {
        Map<String, String> hibernateProperties = buildHibernateProperties(dbConfiguration);
        //StandardServiceRegistry - класс используемый для настройки Hibernate. Передаст настроки в движок Hibernate
        //applySettings передаем группу настроек Map-у
        //build
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        //Configuration - именно из пакета org.hibernate.cfg.Configuration
        //Configuration - класс, который настраивает Hibernate. В него и передаем все параметры.
        //addAnnotatedClass
        Configuration cfg = new Configuration()
                .addAnnotatedClass(University.class);

        factory = cfg.buildSessionFactory(ssr);
    }

    //метод возвращает настройку Hibernate в map-е
    //аргумент DatabaseConfiguration, так как мы уже делали настройку подключения к бд - использовали, что было

    private static Map<String, String> buildHibernateProperties(DatabaseConfiguration dbConfiguration) {
        Map<String, String> properties = new HashMap<>();

        /*
        * указание какой драйвер нужно использовать
        * в jdbc этого не нужно, DriverManager понимал сам по url
        * Driver - выбрать именно из пакета org.postgresql.Driver
         */

        properties.put("hibernate.connection.driver_class", Driver.class.getName());


        properties.put("hibernate.connection.url", dbConfiguration.getUrl());
        properties.put("hibernate.connection.username", dbConfiguration.getLogin());
        properties.put("hibernate.connection.password", dbConfiguration.getPassword());

        properties.put("hibernate.show_sql", "true");         // Выводит в консоль все запросы, которые выполняет Hibernate
        properties.put("hibernate.format_sql", "true");       // Выводит запросы в более читаемом виде

        // validate - проверяет, что все сущности соответствуют бд(столбцы в бд и поля в классах)
        //если поле есть в классе, но нет бд, то Hibernate выдаст ошибку
        // update - устраняет несоответствия сужностей в бд (должно соответствовать классам-сущностям
        // create -при кажном подключении к базе удаляет все таблицы на которые есть классы сущности и создаст новые таблицы
        // create-drop - создает таблицы на которые есть классы сущности при старте, а по окончанию работы приложения удаляет все созданное
        // create, create-drop используется только в тестировании
        properties.put("hibernate.hbm2ddl.auto", "validate"); // Отвечает за генерацию схемы

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            throw new RuntimeException("SessionFactory isn't configured");
        }
        return factory;
    }

}
