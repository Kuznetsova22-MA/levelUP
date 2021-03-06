package org.levelup.university;

import org.hibernate.SessionFactory;
import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.configuration.HibernateConfiguration;
import org.levelup.university.damain.University;
import org.levelup.university.reflact.AnnotationConfigurationPropertiesProcessor;
import org.levelup.university.repository.UniversityRepository;
import org.levelup.university.repository.hbm.HibernateUniversityRepositor;

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
        HibernateConfiguration.configure(DatabaseConfiguration.getInstance());
        System.out.println("Hibernate has been configured successfully");
        SessionFactory factory = HibernateConfiguration.getSessionFactory();

        UniversityRepository universityRepository = new HibernateUniversityRepositor(factory);
        List<University> universities = universityRepository.findAll();
        for (University u: universities){
            System.out.println(u);
        }
//        University u = universityRepository.createUniversity("Высшая Школа Экономики", "ВШЭ", null);
//        System.out.println(u);

        factory.close();

        /*System.out.println(DatabaseConfiguration.getInstance().toString());
        DataBaseService dbService = new DataBaseService(DatabaseConfiguration.getInstance());
        dbService.fillPool();
        System.out.println("Connection pool has been initialized");

        System.out.println("University Application has been started");
        Connection proxy = dbService.openConnection();
        proxy.close();
        System.out.println();*/

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
