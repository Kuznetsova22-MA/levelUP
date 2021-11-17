package org.levelup.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.university.damain.University;
import org.levelup.university.repository.UniversityRepository;

import java.util.List;

// конструктор с final полями - public HibernateUniversityRepository(SessionFactory factory) { this.factory = factory; }
@RequiredArgsConstructor
public class HibernateUniversityRepositor  implements UniversityRepository {
    private final SessionFactory factory;

    @Override
    public List<University> findAll() {
        try (Session session = factory.openSession()) {
            // HQL - Hibernate Query Language
            // select * from university where short_name = '...'
            // from University where shortName = '...'
            //первый параметр запрос на HQL
            //второй параметр - класс, объект которого хотим получить
            return session.createQuery("from University", University.class)
                    .getResultList(); // возвращает список строк, преобразованных в объекты сущности
        }
    }

    @Override
    public University createUniversity(String name, String shortName, Integer foundationYear) {
        //открываем соединение к бд(в Hibernate соединение называют сессией)
        try (Session session = factory.openSession()) {
            //создаем начало транзакции
            //оборачиваем в транзакцию всегда, когда делаем :insert, update, delete
            Transaction tx = session.beginTransaction();

            University u = new University(name, shortName, foundationYear);
            session.persist(u); // insert
            System.out.println("Newly inserted row ID: " + u.getUniversityID());

            tx.commit(); //завершение транзакции и фиксирование изменений
            return u;
        }
    }

    @Override
    public University deleteUniversity(Long universityId) {
        return null;
    }

    @Override
    public University findONEuniversity(Long universityId) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from University where universityId = :uid", University.class)
                    .setParameter("uid", universityId) //передаем значение параметра uid = universityId
                    .getSingleResult(); // возвращается ОДНА строка таблицы, если получилось больше строк, то кидает исключение
        }

    }

    @Override
    public University updateUniversity(University university) {
        return null;
    }

    @Override
    public University updateUniversity(Long universityId, String name, String shortName, Integer foundtionYear) {
        return null;
    }

    @Override
    public int countFaculties(Long universityId) {
        return 0;
    }





}
