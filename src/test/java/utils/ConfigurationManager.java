package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.in;

public class ConfigurationManager {

    private static final String PROPERTIES_FILE = "config.properties";
    private static Properties properties = null;

    private static synchronized void loadProperties(){
        try (InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            if (in == null){
                throw new FileNotFoundException("Property file '" +  PROPERTIES_FILE + "' not found in the classpath");
            }
            properties = new Properties();
            properties.load(in);
        }catch (IOException e){
            throw new ConfigurationLoadException("Failed to load properties: " + e.getMessage(), e);
        }
    }
    public static String getProperty(String key){
        if (properties == null){
            loadProperties();
        }
        return properties.getProperty(key);
    }

    // custom exception to handle if file not loaded
    static class ConfigurationLoadException extends RuntimeException{
        public ConfigurationLoadException(String message, Throwable cause){
            super(message, cause);
        }
    }

}
