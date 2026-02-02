package stepdefinitions;

import io.cucumber.java.en.*;
import pages.TrainResultsPage;
import utils.DriverFactory;

public class TrainResultsSteps {

    private TrainResultsPage resultsPage;

    @When("user enables best available")
    public void user_enables_best_available() {
        resultsPage = new TrainResultsPage(DriverFactory.getDriver());
        resultsPage.enableBestAvailable();
    }

    @When("user selects class {string}")
    public void user_selects_class(String classCode) {
        resultsPage.selectClass(classCode);
    }

    @When("user clicks book button")
    public void user_clicks_book_button() {
        resultsPage.clickBook();
    }

    @When("user selects saved passenger {string}")
    public void user_selects_saved_passenger(String passengerName) {
        resultsPage.selectPassengerByName(passengerName);
    }

    @When("user confirms passengers")
    public void user_confirms_passengers() {
        resultsPage.confirmPassengers();
    }
}
