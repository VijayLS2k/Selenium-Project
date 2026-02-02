package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver;
    public static WebDriver initDriver() {
        if (driver != null) return driver;
        String browser = ConfigReader.get("browser");
        if ("edge".equalsIgnoreCase(browser)) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver(); // default
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));
        return driver;
    }
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver is null. initDriver() was not called.");
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
