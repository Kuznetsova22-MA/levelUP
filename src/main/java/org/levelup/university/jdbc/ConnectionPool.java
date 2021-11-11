package org.levelup.university.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
    private Queue<Connection> queue = new LinkedList<>();

    public Connection getConnection() {
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll();
    }
    //вызываем когда хотим вернуть соединение в pool
    public  void returnConnection(Connection connection){
        queue.offer(connection);
    }

}
