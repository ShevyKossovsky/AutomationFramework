package driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages browser sessions within a web automation framework.
 * <p>
 * This class is responsible for handling the creation, management, and termination of WebDriver instances
 * based on the browser type. It uses the {@link DriverProvider} to determine which browser to use.
 * It allows for browser actions such as navigating to URLs, restarting the browser, and checking if the browser
 * is active.
 *
 * @author Shevy Kossovsky
 */
public class DriverSessionManager implements DriverSessionService {

    // The WebDriver instance managing the browser session.
    private WebDriver driver;

    /**
     * Constructor to initialize BrowserSessionManager with an existing WebDriver.
     * <p>
     * This constructor allows the sharing of an existing WebDriver instance between
     * BrowserSessionManager and RegularBrowserManager.
     *
     * @param driver the WebDriver instance to be shared.
     */
    public DriverSessionManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the current WebDriver instance.
     * <p>
     * This method returns the WebDriver instance currently being used for the browser session.
     * If the WebDriver is not yet initialized, an exception will be thrown.
     *
     * @return the current WebDriver instance.
     * @throws IllegalStateException if the WebDriver has not been initialized.
     */
    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("The driver is not initialized.");
        }
        return driver;
    }

    /**
     * Sets the WebDriver instance for the specified browser.
     * <p>
     * This method initializes and configures the WebDriver according to the browser type provided by
     * the {@link DriverProvider}. The WebDriver will be set for use in the current session.
     *
     * @param browserProvider the implementation of {@link DriverProvider} that provides the browser type.
     * @return the initialized WebDriver instance for the specified browser.
     */
    @Override
    public WebDriver setDriver(String driverName) {
        // Use the BrowserFactory to create the WebDriver based on the browser type.
        driver = DriverFactory.createDriver(driverName);
        return driver;
    }

    /**
     * Closes the current browser session.
     * <p>
     * This method will close the WebDriver instance and the associated browser window, terminating the session.
     *
     * @param driver the WebDriver instance to be closed.
     */
    @Override
    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            this.driver = null;
        }
    }

    /**
     * Checks if the browser is currently active.
     * <p>
     * This method returns {@code true} if the current browser session is active and usable,
     * and {@code false} otherwise (e.g., if the browser session is closed).
     *
     * @return {@code true} if the browser is active, {@code false} otherwise.
     */
    @Override
    public boolean isBrowserActive() {
        return driver != null; // If a driver instance exists, the browser is active.
    }

    /**
     * Navigates to the specified URL in the current browser session.
     * <p>
     * This method instructs the WebDriver to navigate to the given URL.
     *
     * @param url the URL to navigate to.
     */
    @Override
    public void navigateTo(String url) {
        if (driver != null)
            driver.get(url);
        else {
            throw new IllegalStateException("Driver is not initialized. Unable to navigate.");
        }
    }

    /**
     * Retrieves the name of the currently active browser.
     * <p>
     * This method returns the name of the browser currently being used in the session (e.g., "Chrome", "Firefox").
     *
     * @return the name of the current browser.
     */
    @Override
    public String getCurrentBrowser() {
        if (driver != null) {
            String driverName = driver.getClass().getSimpleName();
            return driverName.replace("Driver", "");
        } else {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }
}
