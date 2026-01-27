package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;
    public static Properties load() {
        if (prop != null) return prop;
        prop = new Properties();
        try (FileInputStream ip =
                     new FileInputStream("src/test/resources/config/config.properties")) {
            prop.load(ip);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        return prop;
    }
    public static String get(String key) {
        return load().getProperty(key);
    }
}
