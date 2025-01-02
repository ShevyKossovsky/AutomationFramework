package waiting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * This class provides static utility methods for managing implicit and explicit waits in Selenium WebDriver.
 *
 * @author Shevy Kossovsky
 */
public class WaitingManager {

    /**
     * Applies an implicit wait for the given time in seconds to the provided WebDriver instance.
     * This wait is applied globally to all element searches.
     *
     * @param driver           the WebDriver instance to apply the implicit wait on.
     * @param timeoutInSeconds the time in seconds to wait for elements to be found.
     */
    public static void applyImplicitWait(WebDriver driver, long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }

    /**
     * Waits for an element to become visible on the page within the specified timeout.
     *
     * @param driver           the WebDriver instance to use for waiting.
     * @param element          the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be visible.
     */
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable on the page within the specified timeout.
     *
     * @param driver           the WebDriver instance to use for waiting.
     * @param element          the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable.
     */
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for an element to be present in the DOM of the page within the specified timeout.
     *
     * @param driver           the WebDriver instance to use for waiting.
     * @param locator          the By locator of the element to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present.
     */
    public static void waitForElementToBePresent(WebDriver driver, By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for the page to fully load within the specified timeout.
     *
     * @param driver           the WebDriver instance to use for waiting.
     * @param timeoutInSeconds the timeout in seconds to wait for the page to load.
     */
    public static void waitForPageToLoad(WebDriver driver, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(d -> ((String) ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState")).equals("complete"));
    }
}
