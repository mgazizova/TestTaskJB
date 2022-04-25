package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Reader of configuration.properties file
 */
public class ConfigFileReader {
    private static ConfigFileReader instance;
    private final String PROPERTIES_FILE_PATH = "config.properties";
    private Properties properties;

    private ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTIES_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File configuration.properties not found at " + PROPERTIES_FILE_PATH);
        }
    }

    public static ConfigFileReader getInstance() {
        if (instance == null) {
            instance = new ConfigFileReader();
        }
        return instance;
    }

    public String getChromeDriverPath() {
        String driverPath = properties.getProperty("chromedriver");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("chromedriver parameter not specified at configuration.properties file");
    }

    public String getWebAddress() {
        String webAddress = properties.getProperty("webaddress");
        if (webAddress != null) return webAddress;
        else throw new RuntimeException("webaddress parameter not specified at configuration.properties file");
    }

    public String getUserName() {
        String username = properties.getProperty("username");
        if (username != null) return username;
        else throw new RuntimeException("username parameter not specified at configuration.properties file");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password parameter not specified at configuration.properties file");
    }
}
