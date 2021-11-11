package org.levelup.university.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JavaReflectionExamples {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //ClassLoarder.loadClass(Subject) --> Class<Subject> - загрузили класс Subject и получили объект Class
        Subject subject = new Subject("Программирование");

        //Class можем получить 2 способами
        // 1) через объект
        Class<?> subjectClass = subject.getClass();

        // 2) через имя класса и литерал .class
        Class<?> subjClass = Subject.class;

        Field[] fields = subjectClass.getDeclaredFields(); //в массиве каждый элемент - поле в классе Subject
        for(Field f: fields){
            System.out.println(f.getType().getName() + " " + f.getName());
        }
        System.out.println("Constructors: ");
        Constructor<?>[] constructors = subjectClass.getDeclaredConstructors(); //возвращает все конструкторы, даже приватные
        for(Constructor<?> c: constructors) {
            Class<?>[] types = c.getParameterTypes();
            System.out.println(Arrays.toString(types));
        }

        Field subjectNameField = subjectClass.getDeclaredField("name");
        subjectNameField.setAccessible(true);
        String subjectName = (String) subjectNameField.get(subject);
        System.out.println(subjectName);
        subjectNameField.set(subject, "Programming");
        System.out.println((String) subjectNameField.get(subject));


    }

}
