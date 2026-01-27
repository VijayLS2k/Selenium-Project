package hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import io.cucumber.java.Scenario;

public class hooks {
    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }
    @After
    public void tearDown(Scenario scenario) {
    	 if (scenario.getSourceTagNames().contains("@manualOtp")) {
             System.out.println("Manual OTP scenario: browser kept open.");
             return; // do NOT quit driver
         }
        DriverFactory.quitDriver();
    }
}
