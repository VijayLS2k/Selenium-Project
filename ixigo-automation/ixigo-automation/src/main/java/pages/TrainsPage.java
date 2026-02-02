package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AutoSuggestUtil;

import java.time.Duration;

public class TrainsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public TrainsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-testid='autocompleter-input' and @placeholder='Enter Origin']")
    private WebElement fromInput;

    @FindBy(xpath = "//input[@data-testid='autocompleter-input' and @placeholder='Enter Destination']")
    private WebElement toInput;

    @FindBy(xpath = "//*[contains(text(),'Departure Date')]/following::span[1]")
    private WebElement departureDateValue;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchBtn;

    private final By suggestionLocator =
            By.xpath("//*[contains(@class,'overflow-auto')]//*[normalize-space()!='']");

    public void openTrainsPage() {
        By trainsP = By.xpath("//p[normalize-space()='Trains']");
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(trainsP));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        js.executeScript("arguments[0].click();", el);
        wait.until(ExpectedConditions.visibilityOf(fromInput));
    }

    public void selectFromStation(String stationText) {
        AutoSuggestUtil.selectFromAutoSuggest(driver, fromInput, suggestionLocator, stationText);
    }

    public void selectToStation(String stationText) {
        AutoSuggestUtil.selectFromAutoSuggest(driver, toInput, suggestionLocator, stationText);
    }

    public void selectDepartureDay(int day) {
        wait.until(ExpectedConditions.elementToBeClickable(departureDateValue)).click();
        By dayButton = By.xpath("//button[contains(@class,'react-calendar__tile') and not(@disabled)]//abbr[text()='" + day + "']/parent::button");
        wait.until(ExpectedConditions.elementToBeClickable(dayButton)).click();
    }

    public void clickSearchTrains() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public boolean isResultsDisplayed() {
        try {	
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("trains"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(.,'Trains') and contains(.,'found')] | //button[contains(.,'Book')]"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
