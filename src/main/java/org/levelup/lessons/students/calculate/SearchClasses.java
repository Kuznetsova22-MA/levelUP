package org.levelup.lessons.students.calculate;

import org.levelup.university.reflact.ConfigurationPropertiesProcessor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class SearchClasses {
    public static void main(String[] args) {

        //InputStream in = ConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
        URL p = SearchClasses.class.getClassLoader().getResource("/src/main/java/org/levelup/lessons/students");
        File f = new File(p.getFile());

       /* File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;*/

        System.out.println(p.get);

    }

/*
    public void searchClassFromPackage(String packageName){
        //InputStream in = ConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
        SearchClasses.class.getClassLoader()

    }
 */
    //public String[] processAnnotation(String packageName){}
}
