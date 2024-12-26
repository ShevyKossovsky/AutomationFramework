package waiting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * This class manages the Explicit Wait functionality for WebDriver.
 * It provides various methods to wait for elements under specific conditions.
 */
public class ExplicitlyWaitManager {
    private final WebDriver driver;

    /**
     * Constructor to initialize the ExplicitlyWaitManager with the given WebDriver.
     *
     * @param driver the WebDriver instance to apply explicit waits on.
     */
    public ExplicitlyWaitManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an element to become visible on the page within the specified timeout.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be visible.
     */
    public void waitForElementToBeVisible(WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable on the page within the specified timeout.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable.
     */
    public void waitForElementToBeClickable(WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for an element to be present in the DOM of the page within the specified timeout.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present.
     */
    public void waitForElementToBePresent(WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }


}
