package org.levelup.lessons.students.repository;

import org.levelup.lessons.students.domain.Student;
import org.levelup.university.reflact.ReflectionClass;

import java.util.Date;
@ReflectionClass
public interface StudentRepository {

    Student createStudent(Integer studentTicketId, Integer universityId, String fio, Date birthDate, String adress, Integer facultyId);
    Student createStudent(Integer studentTicketId, String fio, Date birthDate, String adress);
    Student createStudent(Integer studentTicketId, String fio, Date birthDate);
    Student deleteStudent(Integer studentId);
    Student findONEStudent(Integer studentId);
    Student updateStudent(Student Student);
}
