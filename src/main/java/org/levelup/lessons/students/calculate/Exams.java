package org.levelup.lessons.students.calculate;

import org.levelup.university.reflact.ReflectionClass;

@ReflectionClass
public interface Exams {

    public double averageRating(int [] point);
    public int countStudent();
    int countFaculties(Long StudentId);


}
