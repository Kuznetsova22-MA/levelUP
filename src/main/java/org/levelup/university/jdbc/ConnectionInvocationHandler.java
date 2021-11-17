package org.levelup.university.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

//класс - перехватчик вызовов
//вызывая любой метод объекта Proxy мы всегла попадаем в метод invoke
public class ConnectionInvocationHandler implements InvocationHandler {
    private final Connection originalConnection;
    private final ConnectionPool connectionPool;

    public ConnectionInvocationHandler(Connection originalConnection, ConnectionPool connectionPool) {
        this.originalConnection = originalConnection;
        this.connectionPool = connectionPool;
    }

    //после перехватки объекта в методе invoke происходит обработка
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        // void close(); - сигнатура метода close
        if (methodName.equals("close")) {
            //если вызывался метод close, то вместо закрытия соединения с бд
            //передаем Connection в pool
            connectionPool.returnConnection(originalConnection);
            return null;
        }

        // Вызов метода у оригинального объекта
        //method.invoke - вызов метода через рефлексию
        return method.invoke(originalConnection, args);
    }

}
