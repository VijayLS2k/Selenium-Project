package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        		plugin = {
        			    "pretty",
        			    "html:target/cucumber.html",
        			    "json:target/cucumber.json",
        			    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/extent"
        			},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

