package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CheckoutPage;
import utils.DriverFactory;

public class CheckoutSteps {

    private CheckoutPage checkoutPage;
    @When("user is on checkout page")
    public void user_is_on_checkout_page() {
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.handleAssuredPopupIfPresent();
    }


    @When("user enters email {string}")
    public void user_enters_email(String email) {
        checkoutPage.enterEmail(email);
    }

    @When("user selects no free cancellation")
    public void user_selects_no_free_cancellation() {
        checkoutPage.selectNoFreeCancellation();
    }

    @When("user clicks proceed to pay")
    public void user_clicks_proceed_to_pay() {
        checkoutPage.clickProceedToPay();
    }

    @Then("proceed to pay should be visible")
    public void proceed_to_pay_should_be_visible() {
        Assert.assertTrue(checkoutPage.isProceedToPayVisible());
    }
}
