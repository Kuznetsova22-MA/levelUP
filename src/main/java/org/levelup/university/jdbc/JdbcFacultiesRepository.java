package org.levelup.university.jdbc;
//Добавление нового факультета к существующему университету. Если указанного университета нет, то генерируем исключение.
//Получение списка факультетов у университет

import org.levelup.university.damain.Faculties;
import org.levelup.university.damain.University;
import org.levelup.university.exeption.NullRowsExeption;
import org.levelup.university.repository.FacultiesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcFacultiesRepository implements FacultiesRepository {
    private final DataBaseService dbService; //создается соединение с бд

    public JdbcFacultiesRepository(DataBaseService dbService) {
        this.dbService = dbService;
    }


    @Override
    public Faculties createFaculties(String nameFacultet, Long universityId) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt;
            //ищем университет по id
            stmt = connection.prepareStatement("select* from university where id = ?");
            stmt.setLong(1, universityId);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                stmt = connection.prepareStatement("insert into faculties (name, university_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, nameFacultet);
                stmt.setLong(2, universityId);

                int createRows = stmt.executeUpdate();
                System.out.println("Count of inserted rows: " + createRows);

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                generatedKeys.next();
                Integer facultityId = generatedKeys.getInt(1);
                return new Faculties(facultityId, nameFacultet, universityId);

            } else {
                throw new NullRowsExeption("don`t have rows");
            }

        } catch (SQLException | NullRowsExeption exc) {
            System.out.println("Couldn't get all university becouse of an error  " + exc.getMessage());
        }
        return null;
    }

    @Override
    public List<Faculties> facultiesList(Long universityId) {
        try (Connection connection = dbService.openConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select* from faculties where university_id = ?");
            stmt.setLong(1, universityId);
            // stmt.executeUpdate();
            stmt.executeQuery();
            ResultSet resultSet = stmt.executeQuery();
            return retriverFromResultSet(resultSet);
        } catch (SQLException exc) {
            System.out.println("Couldn't get all university becouse of an error " + exc.getMessage());
            return Collections.emptyList();
        }

    }


    public List<Faculties> retriverFromResultSet(ResultSet resultSet) throws SQLException {
        List<Faculties> faculties = new ArrayList<>();
        while (resultSet.next()) {
            Integer facultiesId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Long universityId = resultSet.getLong("university_id");

            Faculties facultity = new Faculties(facultiesId, name, universityId);
            faculties.add(facultity);
        }
        return faculties;
    }

}
