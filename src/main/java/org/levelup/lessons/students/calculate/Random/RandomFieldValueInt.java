package org.levelup.lessons.students.calculate.Random;

import org.levelup.university.exeption.FieldTypeException;
import org.levelup.university.reflact.RandomInt;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

public class RandomFieldValueInt {
    public static ArrayList<String> processAnnotation(String packageName) throws FieldTypeException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<String> names = findAllClassesFromPackage(packageName);
        Class clazz;
        Constructor constructor;
        Field[] fields;
        for (String name : names) {
            name = name.replace("/", ".");
            clazz = Class.forName(name);
            if (!clazz.isInterface()) {
                fields = clazz.getDeclaredFields();
                constructor = clazz.getConstructor(); //возращаю конструктор, чтобы получить создать объект
                Object actualClass = constructor.newInstance();
                for (Field field : fields) {
                    RandomInt annotation = field.getAnnotation(RandomInt.class);
                    if (annotation != null) {
                        if (field.getType() == Integer.class || field.getType() == int.class) {
                            int min = annotation.minRange();
                            int max = annotation.maxRange();
                            Integer randomNumber = getRandomNumber(min, max);

                            field.setAccessible(true);
                            field.set(actualClass, randomNumber);
                            System.out.println(actualClass.toString());
                        } else {
                            throw new FieldTypeException("Type field is not Integer or int");
                        }
                    }
                }
            }
        }
        return names;
    }

    public static Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //метод отбирает все классы в заданном пакете, а также во всех его подпакетах
    public static ArrayList<String> findAllClassesFromPackage(String packageName) {
        //массив, куда будем складывать имена найденных классов
        ArrayList<String> names = new ArrayList<>();
        packageName = packageName.replace(".", "/");
        /*
         * возвращаем ClassLoader текущего класса
         * возвращаем Полный адрес пакета ввиде url адреса
         * System.out.println("packageURL = " + packageURL);
         */
        URL packageURL = RandomFieldValueInt.class.getClassLoader().getResource(packageName);

        /*
         * packageURL.getPath() - Полный адрес пакета ввиде String
         * folder будет управлять файлами и папками в дирктории packageName
         */
        File folder = new File(packageURL.getPath());
        File[] fileList = folder.listFiles();
        String entryName;
        int lastIndexOf;
        for (File actual : fileList) {
            entryName = actual.getName();
            lastIndexOf = entryName.lastIndexOf('.');
            if (lastIndexOf > 0) {
                entryName = entryName.substring(0, lastIndexOf);
                names.add(packageName + "." + entryName);
            } else {
                names.addAll(findAllClassesFromPackage(packageName + "/" + entryName));
            }

        }
        return names;
    }

}
