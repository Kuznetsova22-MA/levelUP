package org.levelup.university.configuration;

public class DatabaseConfiguration {
    private String url;
    private String login;
    private String password;

    private int minPoolSize; //минимальное количество соединений к базе
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
}
