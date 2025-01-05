package driver;

import org.openqa.selenium.WebDriver;

/**
 * Interface for managing browser sessions in a web automation framework.
 * <p>
 * This interface defines the contract for interacting with a browser session, including:
 * <ul>
 *   <li>Initializing and setting the WebDriver instance.</li>
 *   <li>Closing the browser session.</li>
 *   <li>Checking the status of the browser session.</li>
 *   <li>Navigating to specific URLs.</li>
 *   <li>Retrieving browser-specific information.</li>
 * </ul>
 *
 * <p>Implementations of this interface are expected to provide browser-specific logic
 * and behavior for managing WebDriver instances in Selenium-based test frameworks.</p>
 *
 * @author Shevy Kossovsky
 */
public interface DriverSessionService {

    /**
     * Retrieves the current WebDriver instance.
     * <p>
     * This method returns the WebDriver instance currently being used for the browser session.
     * If the WebDriver is not yet initialized, an exception will be thrown.
     * </p>
     *
     * @return the current {@link WebDriver} instance.
     * @throws IllegalStateException if the WebDriver has not been initialized.
     */
    WebDriver getDriver();

    /**
     * Sets and initializes the WebDriver instance using the specified {@link DriverProvider}.
     * <p>
     * This method configures the WebDriver based on the browser type provided by the {@code DriverProvider}
     * and sets it for use in the current session.
     * </p>
     *
     * @param driverProvider the {@link DriverProvider} implementation responsible for supplying the WebDriver.
     * @return the initialized {@link WebDriver} instance.
     */
    WebDriver setDriver(DriverProvider driverProvider);

    /**
     * Terminates the current browser session and closes the WebDriver instance.
     * <p>
     * This method will close the browser window associated with the specified WebDriver and release any
     * resources associated with the session.
     * </p>
     *
     * @param driver the {@link WebDriver} instance to be closed.
     */
    void quitDriver(WebDriver driver);

    /**
     * Checks if the browser session is currently active.
     * <p>
     * This method returns {@code true} if the browser session is active and can be used,
     * or {@code false} if the browser session is closed or unavailable.
     * </p>
     *
     * @return {@code true} if the browser session is active, {@code false} otherwise.
     */
    boolean isBrowserActive();

    /**
     * Navigates to the specified URL in the current browser session.
     * <p>
     * This method instructs the WebDriver to load the given URL in the browser.
     * </p>
     *
     * @param url the URL to navigate to (e.g., "https://google.com").
     */
    void navigateTo(String url);

    /**
     * Retrieves the name of the currently active browser.
     * <p>
     * This method returns a string representing the browser type being used in the session
     * (e.g., "Chrome", "Firefox", "Edge").
     * </p>
     *
     * @return the name of the currently active browser.
     */
    String getCurrentBrowser();
}
