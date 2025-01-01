package driver;

import org.openqa.selenium.WebDriver;

/**
 * Interface for managing browser sessions in a web automation framework.
 * This interface defines methods for interacting with a browser session, including setting the driver,
 * closing the browser, checking if the browser is active, navigating to URLs, and more.
 */
public interface DriverSessionService {

    /**
     * Retrieves the current WebDriver instance.
     * <p>
     * This method returns the WebDriver instance currently being used for the browser session.
     * If the WebDriver is not yet initialized, an exception will be thrown.
     *
     * @return the current WebDriver instance.
     * @throws IllegalStateException if the WebDriver has not been initialized.
     */
    WebDriver getDriver();

    /**
     * Sets the WebDriver instance for the specified browser.
     * <p>
     * This method initializes and configures the WebDriver according to the browser type provided by
     * the {@link DriverProvider}. The WebDriver will be set for use in the current session.
     *
     * @param browserProvider the implementation of {@link DriverProvider} that provides the browser type.
     */
    WebDriver setDriver(DriverProvider browserProvider);

    /**
     * Closes the current browser session.
     * <p>
     * This method will close the WebDriver instance and the associated browser window, terminating the session.
     *
     * @param driver the WebDriver instance to be closed.
     */
    void quitDriver(WebDriver driver);

    /**
     * Checks if the browser is currently active.
     * <p>
     * This method returns {@code true} if the current browser session is active and usable,
     * and {@code false} otherwise (e.g., if the browser session is closed).
     *
     * @return {@code true} if the browser is active, {@code false} otherwise.
     */
    boolean isBrowserActive();

    /**
     * Navigates to the specified URL in the current browser session.
     * <p>
     * This method instructs the WebDriver to navigate to the given URL.
     *
     * @param url the URL to navigate to.
     */
    void navigateTo(String url);

    /**
     * Restarts the current WebDriver instance.
     * <p>
     * This method will close the current browser session and reinitialize the WebDriver for the same browser.
     *
     * @param driver the WebDriver instance to be restarted.
     */
    void restartDriver(WebDriver driver);

    /**
     * Retrieves the name of the currently active browser.
     * <p>
     * This method returns the name of the browser currently being used in the session (e.g., "Chrome", "Firefox").
     *
     * @return the name of the current browser.
     */
    String getCurrentBrowser();
}
