package stepdefinitions;

import io.cucumber.java.en.*;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.TrainsPage;
import utils.DriverFactory;

public class TrainsSteps {
    private TrainsPage trainsPage;
    @Given("user is logged in manually")
    public void user_is_logged_in_manually() {
    		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(180));
    		System.out.println("Please complete OTP login manually... waiting up to 3 minutes.");
    		By otpHeading = By.xpath("//*[contains(.,'Verify Your Mobile') or contains(.,'Verify Your Mobile Number')]");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(otpHeading));
    	 	trainsPage = new TrainsPage(DriverFactory.getDriver());
        System.out.println("Assuming OTP is completed manually. Continuing...");
      
    }	

    @When("user opens trains page")
    public void user_opens_trains_page() {
        trainsPage.openTrainsPage();
    }

    @When("user selects from station {string}")
    public void user_selects_from_station(String from) {
        trainsPage.selectFromStation(from);
    }

    @When("user selects to station {string}")
    public void user_selects_to_station(String to) {
        trainsPage.selectToStation(to);
    }
    @When("user selects departure day {int}")
    public void user_selects_departure_day(Integer day) {
        trainsPage.selectDepartureDay(day);
    }
    @When("user clicks search trains")
    public void user_clicks_search_trains() {
        trainsPage.clickSearchTrains();
    }
    @Then("train results should be displayed")
    public void train_results_should_be_displayed() {
        Assert.assertTrue(trainsPage.isResultsDisplayed(), "Train results not displayed");
    }


}
