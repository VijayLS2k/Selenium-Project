package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class hooks {
    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }
    @After
    public void tearDown(Scenario scenario) {
    	 if (scenario.isFailed()) {
             byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
             scenario.attach(screenshot,"image/png","Failure Screenshot");
         }

    	 if (scenario.getSourceTagNames().contains("@manualOtp")) {
             System.out.println("Manual OTP scenario: browser kept open.");
             return;
         }
        DriverFactory.quitDriver();
    }
}
