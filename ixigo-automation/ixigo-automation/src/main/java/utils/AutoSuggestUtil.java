package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class AutoSuggestUtil {

    private AutoSuggestUtil() {}

    public static void selectFromAutoSuggest(WebDriver driver,
                                             WebElement inputBox,
                                             By suggestionLocator,
                                             String expectedText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(inputBox)).click();

        inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputBox.sendKeys(Keys.BACK_SPACE);

        String query = expectedText.split("\\s+")[0];
        inputBox.sendKeys(query);

        // Wait suggestions visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestionLocator));

        // Collect and click exact match
        List<WebElement> options = driver.findElements(suggestionLocator);
        for (WebElement opt : options) {
            String text = opt.getText().trim();
            if (text.equalsIgnoreCase(expectedText)) {
                opt.click();
                return;
            }
        }

        // If not found, try typing full expectedText once (fallback)
        inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputBox.sendKeys(Keys.BACK_SPACE);
        inputBox.sendKeys(expectedText);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestionLocator));
        options = driver.findElements(suggestionLocator);

        for (WebElement opt : options) {
            String text = opt.getText().trim();
            if (text.equalsIgnoreCase(expectedText)) {
                opt.click();
                return;
            }
        }

        throw new RuntimeException("Station not found in autosuggest: " + expectedText);
    }
}
