package waiting;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

/**
 * This class manages the Implicit Wait functionality for WebDriver.
 * It allows for setting an implicit wait time for all elements.
 */
public class ImplicitlyWaitManager {
    private final WebDriver driver;

    /**
     * Constructor to initialize the ImplicitlyWaitManager with the given WebDriver.
     *
     * @param driver the WebDriver instance to apply the implicit wait on.
     */
    public ImplicitlyWaitManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Applies an implicit wait for the given time in seconds.
     * It will be applied globally to all elements.
     *
     * @param timeoutInSeconds the time in seconds to wait for elements to be found.
     */
    public void applyImplicitWait(long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }
}
