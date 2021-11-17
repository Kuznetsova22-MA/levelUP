package org.levelup.university.reflact;

import org.levelup.university.configuration.DatabaseConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationPropertiesProcessor {

    public static  void  processConfigurationFile(String filename){
      InputStream in = ConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
      try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(in))){
          Map<String, Object> configurationProperties = readProperties(fileReader);//парсим строки из файла конфиг
          fillConfiguration(configurationProperties);

      }catch (IOException exc){
          System.out.println("An error occured during reading " + filename + "file.");
          throw  new RuntimeException(exc);
      } catch (IllegalAccessException exc) {
          System.out.println("Couldn't set property value to object field");
          throw new RuntimeException(exc);
      }

    }

   private static Map<String,Object> readProperties(BufferedReader reader) throws IOException {
       Map<String, Object> properties = new HashMap<>();

       String line; //строка файла
        while ((line = reader.readLine()) != null){
            //если строка не пустая
            if (!line.isBlank()) { // isBlank() -> trim().isEmpty() -> "   " -> true
                //разбиваем строку конфигурационного файла по знаку "="
                String[] elements = line.split("="); // "database.password = root".split("=") -> String["database.password ", " root"]
                //Добавляем в мапу пары
                properties.put(
                        //trim() - удаляет пробелы вначале и вконце строки
                        //replace - заменяет одни элементы на другие, например "database" на ""
                        // database.connection.timeout -> connectiontimeout
                        elements[0].trim().replace("database", "").replace(".", ""),
                        elements[1].trim()
                );
            }
        }
        return properties;
    }

    private static void fillConfiguration(Map<String, Object> properties) throws IllegalAccessException {
        // класс Singleton
        Class<?> dbConfigurationClass = DatabaseConfiguration.class;

        //проходим по списку полей класса Singleton
        Field[] fields = dbConfigurationClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("instance")) {
                //toLowerCase() - перевод текстовой строки в нижний регистр
                String lowerCaseFieldName = field.getName().toLowerCase(); // "connectionTimeout".toLowerCase() -> "connectiontimeout"
                //возращаем из мапы значение по наименованию поля класса Singleton
                Object propertyValue = properties.get(lowerCaseFieldName);
                //позволяет добавить значение в приватное поле
                field.setAccessible(true); // allow set value to private field
                //DatabaseConfiguration.getInstance() - объект, которому будем присваивать значение
                field.set(DatabaseConfiguration.getInstance(), castStringToFieldType(field.getType(), propertyValue));
            }
        }

    }

    private static Object castStringToFieldType(Class<?> fieldType, Object propertyValue) {
        //если поле класса Singleton типа String
        if (fieldType == String.class) {
            return propertyValue;
        }

        //если поле класса Singleton примитив  - число
        if (fieldType.isPrimitive() && fieldType != boolean.class) {
            return Integer.parseInt((String) propertyValue);
        }
        return null;
    }

}
