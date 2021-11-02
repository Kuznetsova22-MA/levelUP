package org.levelup.university.reflact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ConfigurationPropertiesProcessor {

    public static  void  processConfigurationFile(String filename){
      InputStream in = ConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
      try(BufferedReader fileReder = new BufferedReader(new InputStreamReader(in))){

      }catch (IOException exc){
          System.out.println("An error occured during reading " + filename + "file.");
          throw  new RuntimeException(exc);
      }

    }

  /*  private static Map<String,Object> readProperties(BufferedReader reader){
        String line;
        while ()
    }*/

}
