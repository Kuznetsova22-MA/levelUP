package org.levelup.university.jdbc;

import org.levelup.university.damain.University;
import org.levelup.university.exeption.NullRowsExeption;
import org.levelup.university.repository.UniversityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbsUniversityRepository implements UniversityRepository {
    private final DataBaseService dbService; //создается соединение с бд

    //класс JdbsUniversityRepository зависит от класса DataBaseService
    public JdbsUniversityRepository(DataBaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<University> findAll() {
        //конструкция try/catch нужна для открытия соединения и автоматического закрытия соединения,
        // т к Connection реализует интервейс AutoClose
        try (Connection connection = dbService.openConnection()) {
            Statement stmt = connection.createStatement(); // создали statement, который будет выполнять наши запросы к бд
            //метод executeQuery передает запрос к бд, в резульаье получаем список строк
            //объект resultSet - это временная таблица, в которую возвращается результат select-а
            ResultSet resultSet = stmt.executeQuery("select* from university");
            return retriverFromResultSet(resultSet);
        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error" + exc.getMessage());
            return Collections.emptyList();
        }

    }

    @Override
    public University findONEuniversity(Long universityId) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select* from university where id = ?");
            stmt.setLong(1, universityId);
            retriverFromResultSet(stmt.executeQuery()).get(0);
            return retriverFromResultSet(stmt.executeQuery()).get(0);

        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error" + exc.getMessage());
            return null;
        }

    }

    @Override
    public University updateUniversity(University university) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt;
            //собираем элемент University, который собираемся обновить
            stmt = connection.prepareStatement("select* from university where id = ?");
            stmt.setLong(1, university.getUniversityID());

            ResultSet resultSet = stmt.executeQuery();
            List<University> universityList = retriverFromResultSet(resultSet);
            if(universityList.isEmpty()){
                throw new NullRowsExeption("don`t have rows");
            }else{
                //обновляем элемент
                stmt = connection.prepareStatement("update university set  name = ?, short_name = ?, foundation_year = ?  where id = ?");
                stmt.setString(1, university.getName());
                stmt.setString(2, university.getShortName());
                stmt.setInt(3, university.getFoundationYear());
                stmt.setLong(4, university.getUniversityID());

                stmt.executeUpdate();


                return university;
            }
        } catch (SQLException | NullRowsExeption exc) {

            System.out.println("Couldn't get all university becouse of an error  " + exc.getMessage());
        }
        return null;
    }

    @Override
    public University updateUniversity(Long universityId, String name, String shortName, Integer foundtionYear) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt;
            //собираем элемент University, который собираемся обновить
            stmt = connection.prepareStatement("select* from university where id = ?");
            stmt.setLong(1, universityId);

            ResultSet resultSet = stmt.executeQuery();
            List<University> universityList = retriverFromResultSet(resultSet);
            if(universityList.isEmpty()){
                throw new NullRowsExeption("don`t have rows");
            }else{
                //обновляем элемент
                stmt = connection.prepareStatement("update university set  name = ?, short_name = ?, foundation_year = ?  where id = ?");
                stmt.setString(1, name);
                stmt.setString(2, shortName);
                stmt.setInt(3, foundtionYear);
                stmt.setLong(4, universityId);

                stmt.executeUpdate();


                return new University(universityId, name, shortName, foundtionYear);
            }
        } catch (SQLException | NullRowsExeption exc) {

            System.out.println("Couldn't get all university becouse of an error  " + exc.getMessage());
        }
        return null;
    }

    @Override
    public int countFaculties(Long universityId) {
        try(Connection connection = dbService.openConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select count(f.id) from faculties f left join university u on u.id=f.university_id where u.id = ?");
            stmt.setLong(1, universityId);
            //stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            System.out.println("количество  " + resultSet.getInt(1));
           return resultSet.getInt(1);

        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error  " + exc.getMessage());

        }
        return 0;
    }

    @Override
    public University createUniversity(String name, String shortName, Integer foundtionYear) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt = connection
                    .prepareStatement("insert into university (name, short_name, foundation_year) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, shortName);
            if(foundtionYear == null){
                stmt.setNull(3, foundtionYear);
            }else {
                stmt.setInt(3, foundtionYear);
            }
            int createRows = stmt.executeUpdate();
            System.out.println("Count of inserted rows: " + createRows);

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            Long universityId = generatedKeys.getLong(1);
            return new University(universityId, name, shortName, foundtionYear);
        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error" + exc.getMessage());
        }
        return null;
    }

    @Override
    public University deleteUniversity(Long universityId) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt;
            //собираем элемент University, который собираемся удалить
            stmt = connection.prepareStatement("select* from university where id = ?");
            stmt.setLong(1, universityId);
            ResultSet resultSet = stmt.executeQuery();
            List<University> universityList = retriverFromResultSet(resultSet);
            if(universityList.isEmpty()){
                return null;
            }else{
                    //удаляем элемент
                    stmt = connection.prepareStatement("delete from university where id = ?");
                    stmt.setLong(1, universityId);
                    int deleteRows = stmt.executeUpdate();
                    System.out.println("Count of delete rows: " + deleteRows);
                    return universityList.get(0);
            }
        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error" + exc.getMessage());
        }
        return null;
    }


    //метод будет парсить результат select-а из объекта ResultSet
    //ResultSet - это некий Iterator, который ходит по строкам временной таблицы, образовавшейстя в результате select-а
    private List<University> retriverFromResultSet(ResultSet resultSet) throws SQLException {
        List<University> universities = new ArrayList<>();
        while (resultSet.next()) {
            Long universityId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String short_name = resultSet.getString("short_name");
            Integer foundationYear = resultSet.getInt("foundation_year");

            University university = new University(universityId, name, short_name, foundationYear);
            universities.add(university);
        }
        return universities;
    }

}
