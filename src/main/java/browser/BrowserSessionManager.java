package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 * Manages browser sessions within a web automation framework.
 * <p>
 * This class is responsible for handling the creation, management, and termination of WebDriver instances
 * based on the browser type. It uses the {@link BrowserProvider} to determine which browser to use.
 * It allows for browser actions such as navigating to URLs, restarting the browser, and checking if the browser
 * is active.
 */
public class BrowserSessionManager implements BrowserSessionService {

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
    public BrowserSessionManager(WebDriver driver) {
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
     * the {@link BrowserProvider}. The WebDriver will be set for use in the current session.
     *
     * @param browserProvider the implementation of {@link BrowserProvider} that provides the browser type.
     * @return the initialized WebDriver instance for the specified browser.
     */
    @Override
    public void setDriver(BrowserProvider browserProvider) {
        // Use the BrowserFactory to create the WebDriver based on the browser type.
        driver = BrowserFactory.createDriver(browserProvider);
    }

    /**
     * Closes the current browser session.
     * <p>
     * This method will close the WebDriver instance and the associated browser window, terminating the session.
     *
     * @param driver the WebDriver instance to be closed.
     */
    @Override
    public void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit(); // Close the browser session.
            this.driver = null; // Set the driver to null as the session is closed.
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
     * Restarts the current WebDriver instance.
     * <p>
     * This method will close the current browser session and reinitialize the WebDriver for the same browser.
     *
     * @param driver the WebDriver instance to be restarted.
     */
    @Override
    public void restartDriver(WebDriver driver) {
        closeDriver(driver); // Close the existing driver.
        setDriver(new BrowserProvider() {
            @Override
            public String getBrowserName() {
                return driver.toString(); // Assuming the driver object can provide its browser name.
            }
        });
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
            return driverName.replace("Driver", ""); // Returns "Chrome", "Firefox", etc.
        } else {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }
}
