package org.levelup.university.configuration;

import org.levelup.university.reflact.Property;

// класс Singleton
public class DatabaseConfiguration {
    @Property(key = "database.url")
    private String url;
    @Property(key = "database.login")
    private String login;
    @Property(key = "database.password")
    private String password;

    @Property(key = "database.min.pool.size")
    private int minPoolSize; //минимальное количество открытых соединений к базе
    @Property(key = "database.connection.timeout")
    private long connectionTimeout;

    private DatabaseConfiguration(){}

    private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();

    public  static  DatabaseConfiguration getInstance(){
        return INSTANCE;
    }
    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
                "url='" + url + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", minPoolSize=" + minPoolSize +
                ", connectionTimeout=" + connectionTimeout +
                '}';
    }
}
