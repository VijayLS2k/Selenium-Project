package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@type='email' or contains(@placeholder,'Email') or contains(@aria-label,'Email')]")
    private WebElement emailInput;

    @FindBy(xpath = "//*[normalize-space()=\"No, I don't want Free Cancellation\"]")
    private WebElement noFreeCancellationText;

    @FindBy(xpath = "//button[contains(.,'Proceed to Pay') or contains(.,'Pay Now') or contains(.,'Proceed')]")
    private WebElement proceedToPayBtn;
    @FindBy(xpath = "//*[contains(.,\"Don't miss your full refund\") or contains(.,'miss your full refund')]")
    private WebElement assuredPopupTitle;

    By proceedWithoutAssured = By.xpath(
    		  "//*[contains(.,'Without') and contains(.,'Assured')]" +
    		  "/ancestor::*[self::div or self::section][1]//button[normalize-space()='Proceed']"
    		);
    		
    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        emailInput.sendKeys(Keys.BACK_SPACE);
        emailInput.sendKeys(email);
    }

    public void selectNoFreeCancellation() {
        wait.until(ExpectedConditions.visibilityOf(noFreeCancellationText));
        try {
            noFreeCancellationText.click();
        } catch (Exception e) {
            jsClick(noFreeCancellationText);
        }
    }

    public void clickProceedToPay() {
        wait.until(ExpectedConditions.visibilityOf(proceedToPayBtn));
        jsClick(proceedToPayBtn);
    }
    public boolean isProceedToPayVisible() {
        try {
            return proceedToPayBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void handleAssuredPopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOf(assuredPopupTitle));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",proceedWithoutAssured);

        } catch (Exception ignored) {
        }
    }

}
