package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class TrainResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public TrainResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[normalize-space()='Best Available']/preceding::input[@type='checkbox'][1]")
    private WebElement bestAvailableCheckbox;

    @FindBy(xpath = "//div[contains(@class,'_avail-card_')]")
    private List<WebElement> availabilityCards;

    @FindBy(xpath = "//button[contains(.,'Book') or contains(.,'BOOK')]")
    private List<WebElement> bookButtons;

    @FindBy(xpath = "//*[normalize-space()='Saved Passengers']")
    private WebElement savedPassengersTitle;

    @FindBy(xpath = "//button[normalize-space()='Select Passengers']")
    private WebElement selectPassengersBtn;

    public void enableBestAvailable() {

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[normalize-space()='Best Available']")));

        if (bestAvailableCheckbox.isSelected()) return;

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bestAvailableCheckbox);
        wait.until(d -> bestAvailableCheckbox.isSelected());
    }
    public void selectClass(String classCode) {
        wait.until(ExpectedConditions.visibilityOfAllElements(availabilityCards));

        for (WebElement card : availabilityCards) {
            try {
                WebElement span = card.findElement(By.xpath(".//span[normalize-space()='" + classCode + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", card);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
                return;
            } catch (NoSuchElementException ignored) {
            }
        }
        throw new RuntimeException("Class card not found: " + classCode);
    }

    public void clickBook() {
        wait.until(d -> !bookButtons.isEmpty());

        for (WebElement btn : bookButtons) {
            try {
                if (btn.isDisplayed() && btn.isEnabled()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                    wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        throw new RuntimeException("No clickable Book button found.");
    }
    public void waitForSavedPassengersPopup() {
        wait.until(ExpectedConditions.visibilityOf(savedPassengersTitle));
    }
    public void selectPassengerByName(String passengerName) {
        waitForSavedPassengersPopup();

        By passengerRow = By.xpath("//*[normalize-space()='" + passengerName + "']/ancestor::*[self::div or self::li][1]");
        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(passengerRow));
        List<WebElement> cb = row.findElements(By.xpath(".//input[@type='checkbox']"));
        if (!cb.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb.get(0));
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", row);
        }
    }

    public void confirmPassengers() {
        wait.until(ExpectedConditions.visibilityOf(selectPassengersBtn));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", selectPassengersBtn);
        js.executeScript("arguments[0].click();", selectPassengersBtn);
    }

}
