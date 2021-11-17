package org.levelup.lessons.students;

import org.levelup.lessons.students.calculate.FindClasses;
import org.levelup.lessons.students.calculate.Random.RandomFieldValueInt;
import org.levelup.lessons.students.calculate.Random.RandomFieldValueString;
import org.levelup.university.exeption.FieldTypeException;

import java.lang.reflect.InvocationTargetException;

public class StudentApplication {
    public static void main(String[] args) throws FieldTypeException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      //  System.out.println(FindClasses.processAnnotation("org.levelup.lessons.students.domain"));
      //  System.out.println(RandomFieldValueInt.findAllClassesFromPackage("org.levelup.lessons.students"));
        System.out.println(RandomFieldValueString.processAnnotation("org.levelup.lessons.students"));

    }
}
