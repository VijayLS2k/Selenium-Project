package stepdefinitions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {
    private LoginPage loginPage;
    @Given("user opens login")
    public void user_opens_login() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.openLogin();
    }
    @When("user enters mobile number")
    public void user_enters_mobile_number() {
        String mobile = ConfigReader.get("mobile");
        loginPage.enterMobile(mobile);
    }
    @When("user clicks continue to get OTP")
    public void user_clicks_continue_to_get_otp() {
        loginPage.requestOtp();
    }
    @Then("OTP screen should be displayed")
    public void otp_screen_should_be_displayed() {
        Assert.assertTrue(loginPage.isOtpScreenDisplayed(),
                "OTP screen not displayed");
        System.out.println("OTP screen displayed. Enter OTP manually.");
    }
    @Then("user enters OTP manually")
    public void user_enters_otp_manually() throws Exception {
        System.out.println("Enter OTP manually, then press ENTER here to continue...");
    }


}
