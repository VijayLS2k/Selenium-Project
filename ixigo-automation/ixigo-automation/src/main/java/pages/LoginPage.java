package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginPage {
	private final WebDriverWait wait;
	public LoginPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
	    PageFactory.initElements(driver, this);
	 }
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginBtn;
    
    @FindBy(xpath = "//input[@placeholder = 'Enter Mobile Number']")
    private WebElement mobileInput;
    
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//h4[contains(text(),'Verify Your Mobile')]")
    private WebElement otpHeading;

    public boolean isOtpScreenDisplayed() {
        return otpHeading.isDisplayed();
    }

    public void openLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
    public void enterMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOf(mobileInput)).clear();
        mobileInput.sendKeys(mobile);
    }
    public void requestOtp() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }
    public boolean isOtpScreenShown() {
        try {
            wait.until(ExpectedConditions.visibilityOf(otpHeading));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
