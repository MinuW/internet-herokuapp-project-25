package internet.herokuapp.selenium.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/configuration.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String get(String key){
        return  properties.getProperty(key).trim();//Key is a field in properties file
    }

    public static String getBaseURL(){
        return get("BASE_URL");
    }

    public static String getBrowser(){
        return get("BROWSER");
    }

    public static int getTimeout(){
        String timeout = get("TIMEOUT");
        return Integer.parseInt(timeout);
    }
}
