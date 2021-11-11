package org.levelup.university;

import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.damain.University;
import org.levelup.university.jdbc.DataBaseService;
import org.levelup.university.jdbc.JdbcFacultiesRepository;
import org.levelup.university.jdbc.JdbsUniversityRepository;
import org.levelup.university.reflact.AnnotationConfigurationPropertiesProcessor;
import org.levelup.university.reflact.ConfigurationPropertiesProcessor;
import org.levelup.university.repository.FacultiesRepository;
import org.levelup.university.repository.UniversityRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UniversityApplication {
    public static void main(String[] args) throws SQLException {


        /*String configurationFilename = "database.properties";
        ConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        System.out.println("Application loaded all configuration files");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        System.out.println("University Application has been started");


*/

        String configurationFilename = "database.properties";
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        System.out.println("Application loaded all configuration files");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        System.out.println("University Application has been started");

       // DataBaseService dbService = new DataBaseService(DatabaseConfiguration.getInstance());

       /* System.out.println("University application has been sterted");
        DataBaseService dbService = new DataBaseService();
        UniversityRepository universityRepository = new JdbsUniversityRepository(dbService);
        //System.out.println(universityRepository.deleteUniversity(2080L));
        // System.out.println(universityRepository.createUniversity("Национальный Исследовательский Университет Информационных Технологий, Механики и Оптики", "ИТМО", 1900));

        University universityUpdate = new University(2090L, "Национальный Исследовательский Университет Информационных Технологий, Механики и Оптики", "ИТМО", 1900);
        // universityRepository.updateUniversity(universityUpdate);
        // universityRepository.updateUniversity(2090L, "Национальный Исследовательский Университет Информационных Технологий, Механики и Оптики", "new_ИТМО", 1900);
        System.out.println(universityRepository.findONEuniversity(2090L));

        FacultiesRepository facultiesRepository = new JdbcFacultiesRepository(dbService);
        //System.out.println(facultiesRepository.createFaculties("Факультет инфокоммуникационных технологий", 2091L));
        System.out.println("количество факультетов = "+universityRepository.countFaculties(2090L));


        List<University> universities = universityRepository.findAll();
        for (University u : universities) {
            System.out.println(u.getShortName() + " " + u.getFoundationYear());
        }


        Connection connection = dbService.openConnection();
        System.out.println("Connection has been opened");
        connection.close();
        System.out.println("Connection has been closed");

*/
    }
}
