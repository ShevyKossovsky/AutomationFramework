package driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages browser page operations.
 *
 *  * @author Shevy Kossovsky
 */
public class DriverPageManager implements DriverPageService {
    WebDriver driver;

    /**
     * Constructs a BrowserPageManager with the specified WebDriver.
     *
     * @param driver The WebDriver instance to be used for browser operations.
     */
    public DriverPageManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Refreshes the current page if a valid WebDriver instance exists.
     * This method is an implementation of the BrowserPageService interface.
     */
    @Override
    public void refreshPage() {
        if (driver != null) {
            driver.navigate().refresh();
        }
    }
}
