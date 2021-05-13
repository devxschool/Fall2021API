package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;
    static final String FILE_PATH;
    static final String env = System.getProperty("server");

    static {
        System.out.println("Starting " + env);

        if (env.equalsIgnoreCase("local")) {
            FILE_PATH = "src/main/resources/food_delivery_local.properties";
        }
        else if (env.equalsIgnoreCase("qa1")) {
            FILE_PATH = "src/main/resources/food_delivery_qa.properties";
        }
        else {
            FILE_PATH = "src/main/resources/food_delivery_qa.properties";
        }


        FileInputStream input;
        try {
            input = new FileInputStream(FILE_PATH);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}