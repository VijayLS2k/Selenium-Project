package utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AutoSuggestUtil {
    public static void selectFromAutoSuggest(WebDriver driver,
                                             WebElement inputBox,
                                             By suggestionLocator,
                                             String expectedText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        wait.until(ExpectedConditions.elementToBeClickable(inputBox)).click();
        for (int i = 1; i <= expectedText.length(); i++) {
            String partial = expectedText.substring(0, i);

            inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a")); //have to check
            inputBox.sendKeys(Keys.DELETE);
            inputBox.sendKeys(partial);

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestionLocator));

            List<WebElement> options = driver.findElements(suggestionLocator);
            for (WebElement opt : options) {
                if (opt.getText().trim().equalsIgnoreCase(expectedText)) {
                    opt.click();
                    return;
                }
            }
        }
        throw new RuntimeException("Station not found: " + expectedText);
    }
}
