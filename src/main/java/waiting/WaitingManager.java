package waiting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class serves as a simple interface for managing waits.
 * It delegates calls to the appropriate wait manager classes for implicit and explicit waits.
 */
public class WaitingManager {
    private final WebDriver driver;
    private final ImplicitlyWaitManager implicitlyWaitManager;
    private final ExplicitlyWaitManager explicitlyWaitManager;

    /**
     * Constructor to initialize the WaitingManager with the given WebDriver.
     * It also initializes the ImplicitlyWaitManager and ExplicitlyWaitManager.
     *
     * @param driver the WebDriver instance to apply waits on.
     */
    public WaitingManager(WebDriver driver) {
        this.driver = driver;
        this.implicitlyWaitManager = new ImplicitlyWaitManager(driver);
        this.explicitlyWaitManager = new ExplicitlyWaitManager(driver);
    }

    /**
     * Calls the ImplicitlyWaitManager to apply an implicit wait to the WebDriver.
     *
     * @param timeoutInSeconds the time in seconds to wait for elements.
     */
    public void applyImplicitWait(long timeoutInSeconds) {
        implicitlyWaitManager.applyImplicitWait(timeoutInSeconds);
    }

    /**
     * Calls the ExplicitlyWaitManager to wait for an element to become visible.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be visible.
     */
    public void waitForElementToBeVisible(WebElement element, long timeoutInSeconds) {
        explicitlyWaitManager.waitForElementToBeVisible(element, timeoutInSeconds);
    }

    /**
     * Calls the ExplicitlyWaitManager to wait for an element to be clickable.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be clickable.
     */
    public void waitForElementToBeClickable(WebElement element, long timeoutInSeconds) {
        explicitlyWaitManager.waitForElementToBeClickable(element, timeoutInSeconds);
    }

    /**
     * Calls the ExplicitlyWaitManager to wait for an element to be present in the DOM.
     *
     * @param element the WebElement to wait for.
     * @param timeoutInSeconds the timeout in seconds to wait for the element to be present.
     */
    public void waitForElementToBePresent(WebElement element, long timeoutInSeconds) {
        explicitlyWaitManager.waitForElementToBePresent(element, timeoutInSeconds);
    }

    /**
     * Waits for the page to fully load within the specified timeout.
     *
     * @param timeoutInSeconds the timeout in seconds to wait for the page to load.
     */
    public void waitForPageToLoad(long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(driver -> ((String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState")).equals("complete"));
    }
}
