package org.levelup.lessons.students.domain;

import org.levelup.university.reflact.ReflectionClass;

import java.util.Date;
@ReflectionClass
public class Student {
    Integer studentId;
    Integer studentTicketId;
    Integer universityId;
    String fio;
    Date birthDate;
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
