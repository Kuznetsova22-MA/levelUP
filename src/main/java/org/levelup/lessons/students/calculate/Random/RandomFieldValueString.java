package org.levelup.lessons.students.calculate.Random;

import org.levelup.university.exeption.FieldTypeException;
import org.levelup.university.reflact.RandomInt;
import org.levelup.university.reflact.RandomString;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

public class RandomFieldValueString {
    public static ArrayList<String> processAnnotation(String packageName) throws FieldTypeException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<String> names = RandomFieldValueInt.findAllClassesFromPackage(packageName);
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
                    RandomString annotation = field.getAnnotation(RandomString.class);
                    if (annotation != null) {
                        if (field.getType() == String.class) {
                            int lengthString = annotation.lengthString();
                            int registr = annotation.registr();
                            String str = getRandomString(lengthString, registr);

                            field.setAccessible(true);
                            field.set(actualClass, str);
                            System.out.println(actualClass.toString());
                        } else {
                            throw new FieldTypeException("Type field is not String");
                        }
                    }
                }
            }
        }
        return names;
    }

    public static String getRandomString(int length, int registr) {
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String allChar = CHAR_LOWER + CHAR_UPPER + NUMBER;
        switch (registr) {
            case 1:
                allChar = allChar.toUpperCase(); //если необходим только нижний регистр
                break;
            case 2:
                allChar = allChar.toLowerCase();  //если необходим только верхний регистр
                break;
            default:
                break; //в остальных случаях считаем, что регистр не важен
        }

        StringBuilder str = new StringBuilder(length);
        if (length > 1) {
            for (int i = 0; i < length; i++) {
                str.append(allChar.charAt(RandomFieldValueInt.getRandomNumber(0, allChar.length() - 1)));
            }
        }
        return str.toString();
    }


}
