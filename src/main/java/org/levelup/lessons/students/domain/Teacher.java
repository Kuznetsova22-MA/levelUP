package org.levelup.lessons.students.domain;

import org.levelup.university.reflact.ReflectionClass;

import java.util.Date;

public class Teacher {

    Integer teacherId;
    String fio;
    Date birthDate;
    Integer universityId;
    Integer facultyId;
    Integer documentId;

    public Teacher() {
        teacherId = 0;
        fio = " ";
        //birthDate;
        universityId = 0;
        facultyId = 0;
        documentId = 0;

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", universityId=" + universityId +
                ", facultyId=" + facultyId +
                ", documentId=" + documentId +
                '}';
    }
}
