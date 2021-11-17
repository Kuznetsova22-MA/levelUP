package org.levelup.university.jdbc;

import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import org.levelup.university.reflact.ConnectionTime;

@RequiredArgsConstructor
public class JdbcDatabaseInvokationHandler implements InvocationHandler {

    private final JdbcDatabaseServiceImpl jdbcDatabaseService;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation annotation = method.getAnnotation(ConnectionTime.class);

       /* Annotation[] annotation = proxy.getClass().getAnnotations();
        for (Annotation a: annotation) {
            System.out.println(a.toString());

        }*/
        long beginTime, endTime;

        if (annotation != null){
            System.out.println("Annotation "+annotation.toString());
            beginTime = System.nanoTime();
            method.invoke(jdbcDatabaseService,args);

            endTime = System.nanoTime();
            System.out.println("Время получения соединения с бд = " + (endTime - beginTime));
        }


        //считать время тут
        return null;
    }

    /*
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
     */
}
