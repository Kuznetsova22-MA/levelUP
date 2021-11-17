package org.levelup.lessons.students.domain;

import org.levelup.university.reflact.RandomInt;
import org.levelup.university.reflact.RandomString;
import org.levelup.university.reflact.ReflectionClass;

import java.util.Date;
@ReflectionClass
public class Student {
    Integer studentId;
    @RandomInt(minRange = 0, maxRange = 100)
    Integer studentTicketId;
    @RandomInt(minRange = -200, maxRange = 900)
    int universityId;
    @RandomString(lengthString = 18, registr = 1)
    String fio;
    Date birthDate;
    @RandomString(lengthString = 18, registr = 2)
    String adress;
    Integer facultyId;

    public Student() {
        studentId = 0;
        studentTicketId = 0;
        universityId = 0;
        fio = " ";
       // birthDate = '01.01.1900';
        adress = " ";
        facultyId = 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentTicketId=" + studentTicketId +
                ", universityId=" + universityId +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", adress='" + adress + '\'' +
                ", facultyId=" + facultyId +
                '}';
    }
}
