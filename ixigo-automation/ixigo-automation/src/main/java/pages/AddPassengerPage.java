package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class AddPassengerPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AddPassengerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[normalize-space()='Add New Passenger']")
    private WebElement modalTitle;
    @FindBy(xpath = "//*[normalize-space()='Add New Passenger']/following::button[1] | //button[contains(@aria-label,'Close') or contains(@class,'close')]")
    private WebElement closeBtn;
    @FindBy(xpath = "//input[contains(@placeholder,'Full name') or contains(@placeholder,'Govt') or contains(@aria-label,'Full name')]")
    private WebElement fullNameInput;
    @FindBy(xpath = "//input[@placeholder='Age' or contains(@aria-label,'Age')]")
    private WebElement ageInput;
    @FindBy(xpath = "//*[normalize-space()='Male']")
    private WebElement maleOption;
    @FindBy(xpath = "//*[normalize-space()='Female']")
    private WebElement femaleOption;
    @FindBy(xpath = "//*[normalize-space()='Transgender']")
    private WebElement transgenderOption;
    @FindBy(xpath = "//*[contains(.,'Birth Preference')]/following::*[self::button or self::div][1]")
    private WebElement birthPreferenceDropdown;
    @FindBy(xpath = "//*[contains(.,'Nationality')]/following::*[self::button or self::div][1]")
    private WebElement nationalityDropdown;
    @FindBy(xpath = "//button[normalize-space()='Save Passenger']")
    private WebElement savePassengerBtn;
    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
    public boolean isOpen() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(modalTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void enterFullName(String name) {
        wait.until(ExpectedConditions.visibilityOf(fullNameInput));
        fullNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fullNameInput.sendKeys(Keys.BACK_SPACE);
        fullNameInput.sendKeys(name);
    }
    public void enterAge(int age) {
        wait.until(ExpectedConditions.visibilityOf(ageInput));
        ageInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        ageInput.sendKeys(Keys.BACK_SPACE);
        ageInput.sendKeys(String.valueOf(age));
    }
    public void selectGender(String gender) {
        String g = gender.trim().toLowerCase();
        if (g.equals("male")) jsClick(maleOption);
        else if (g.equals("female")) jsClick(femaleOption);
        else if (g.equals("transgender")) jsClick(transgenderOption);
        else throw new IllegalArgumentException("Unknown gender: " + gender);
    }
    public void openBirthPreference() {
        jsClick(birthPreferenceDropdown);
    }
    public void openNationality() {
        jsClick(nationalityDropdown);
    }
    public void selectDropdownOption(String visibleText) {
        By option = By.xpath("//*[normalize-space()='" + visibleText + "']");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(option));
        jsClick(el);
    }
    public void clickSavePassenger() {
        jsClick(savePassengerBtn);
    }
    public void close() {
        jsClick(closeBtn);
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
    }
}
