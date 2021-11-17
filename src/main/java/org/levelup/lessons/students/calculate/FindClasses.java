package org.levelup.lessons.students.calculate;

import org.levelup.university.reflact.ReflectionClass;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

public class FindClasses {
/*
* метод отбирает список классов, имеющих анотацию @ReflectionClass
* создает экземпляры класоов и вызывает метод toString
 */
    public static ArrayList<String> processAnnotation(String packageName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayList<String> className = new ArrayList<>();
        ArrayList<String> names = findClassesFromPackage(packageName);
        Class clazz;
        Method method;
        for (String name : names) {
            clazz = Class.forName(packageName + "." + name);
            if (!clazz.isInterface()) {
                if (clazz.isAnnotationPresent(ReflectionClass.class)) {
                    className.add(name);
                    method = clazz.getDeclaredMethod("toString");
                    System.out.println(method.invoke(clazz.newInstance()));
                   }
            }
        }
        return className;
    }

    //метод отбирает все файлы в заданном пакете, отделяя от других пакетов
    public static ArrayList<String> findClassesFromPackage(String packageName) {
        //массив, куда будем складывать имена найденных классов
        ArrayList<String> names = new ArrayList<>();
        packageName = packageName.replace(".", "/");

        /*
         * возвращаем ClassLoader текущего класса
         * возвращаем Полный адрес пакета ввиде url адреса
         * System.out.println("packageURL = " + packageURL);
         */
        URL packageURL = FindClasses.class.getClassLoader().getResource(packageName);

        /*
         * packageURL.getPath() - Полный адрес пакета ввиде String
         * folder будет управлять файлами и папками в дирктории packageName
         */
        File folder = new File(packageURL.getPath());
        File[] fileList = folder.listFiles(); //отравляю данные по папкам и пакетам в массив
        String entryName;
        int lastIndexOf;
        for (File actual : fileList) {
            entryName = actual.getName();
            lastIndexOf = entryName.lastIndexOf('.');
            if (lastIndexOf > 0) {
                entryName = entryName.substring(0, lastIndexOf);
                names.add(entryName);
            }
        }
        return names;
    }


}
