package org.levelup.university.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

//ConnectionPool будет хранить отдавать соединения к бд
public class ConnectionPool {
    //в очереди будем хранить соединения к бд
    private Queue<Connection> queue = new LinkedList<>();

    public Connection getConnection() {
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll(); //достаем Connection из очереди
    }
    //вызываем когда хотим вернуть соединение в Connection pool
    public  void returnConnection(Connection connection){
        //использованный Connection возвращается в очередь
        //после использования Connection, он возвращается в Connection pool
        queue.offer(connection);
    }

}
