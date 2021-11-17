package org.levelup.university.jdbc;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseService {
    /*DatabaseConfiguration - настройка/конфигурация подключения к бд
    * url
    * login
    * password
     */
    private DatabaseConfiguration dbConfiguration;
    private ConnectionPool connectionPool;

    public DataBaseService(DatabaseConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
        this.connectionPool = new ConnectionPool();
    }

    //заполнение pool-а открытыми соединениями
    public void fillPool() {
        for (int i = 0; i < dbConfiguration.getMinPoolSize(); i++) {
            Connection connection = createConnection();
            connectionPool.returnConnection(connection); //добавляем в очередь pool-а соединение
        }
    }
    //метод создает соедения к БД
    //openConnection делает тоже самое, но он много где исполуется, поэтому его не трогаем
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
        // connection.getClass() -> PgConnection.class
        //вернется класс PgConnection, так как интерфейс DriverManager реализует имеено класс для бд posgres
        Connection connection = connectionPool.getConnection(); // connection.getClass() -> PgConnection.class
        //вместо реально Connection(объекта класса PgConnection) возвращаем объект proxy
        return (Connection) Proxy.newProxyInstance(
                connection.getClass().getClassLoader(), //класслоудер, который загрузил в память класс
                connection.getClass().getInterfaces(), //список интерфейсов, реализующих класс PgConnection
                new ConnectionInvocationHandler(connection, connectionPool)//передали соединение в перехватчик
        );

    }

    //версия openConnection без использования Connection pool, при подключении к базе напрямую
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
