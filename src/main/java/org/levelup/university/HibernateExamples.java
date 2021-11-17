package org.levelup.university;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.levelup.university.configuration.DatabaseConfiguration;
import org.levelup.university.configuration.HibernateConfiguration;
import org.levelup.university.damain.University;
import org.levelup.university.reflact.AnnotationConfigurationPropertiesProcessor;

public class HibernateExamples {

    public static void main(String[] args) {
        String configurationFilename = "database.properties";
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        System.out.println("Application loaded all configuration files");
        HibernateConfiguration.configure(DatabaseConfiguration.getInstance());
        System.out.println("Hibernate has been configured successfully");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        SessionFactory factory = HibernateConfiguration.getSessionFactory();
        System.out.println("University application has been started");


        // get/load
        University getU = null;
        University loadU = null;

        try (Session s = factory.openSession()) {
            getU = s.get(University.class, 2010L);
        }

        try (Session s = factory.openSession()) {
            loadU = s.load(University.class, 2010L);
            System.out.println();
        }

        System.out.println("getU   " + getU);
        System.out.println("loadU  " + loadU);

        factory.close();
    }

}
