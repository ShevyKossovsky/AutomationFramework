package driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages browser sessions within a web automation framework.
 * <p>
 * This class handles the lifecycle of WebDriver instances, including initialization, management,
 * and termination, based on the specified browser type. It utilizes the {@link DriverProvider}
 * interface to determine the desired browser and provides utility methods to interact with
 * the browser session, such as navigating to URLs, checking browser activity, and retrieving
 * browser information.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * <ul>
 *   <li>Creating and setting up WebDriver instances</li>
 *   <li>Managing browser lifecycle (start, navigate, quit)</li>
 *   <li>Providing information about the current browser session</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * DriverSessionManager manager = new DriverSessionManager(null);
 * WebDriver driver = manager.setDriver(new ChromeDriverProvider());
 * manager.navigateTo("https://example.com");
 * manager.quitDriver(driver);
 * }</pre>
 * </p>
 *
 * @author Shevy Kossovsky
 */
public class DriverSessionManager implements DriverSessionService {

    /**
     * The WebDriver instance managing the browser session.
     */
    private WebDriver driver;

    /**
     * Initializes a DriverSessionManager with an existing WebDriver instance.
     * <p>
     * This constructor allows sharing of an existing WebDriver instance between
     * different managers or components.
     * </p>
     *
     * @param driver the WebDriver instance to initialize with, or {@code null} if no driver is set initially.
     */
    public DriverSessionManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the current WebDriver instance.
     * <p>
     * If the WebDriver instance has not been initialized, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @return the current WebDriver instance.
     * @throws IllegalStateException if the WebDriver instance is not initialized.
     */
    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("The driver is not initialized.");
        }
        return driver;
    }

    /**
     * Sets up and initializes a WebDriver instance based on the specified browser provider.
     * <p>
     * This method uses the {@link DriverFactory} to create a WebDriver instance corresponding
     * to the browser type provided by the {@link DriverProvider}.
     * </p>
     *
     * @param driverProvider the {@link DriverProvider} implementation specifying the desired browser type.
     * @return the initialized WebDriver instance.
     */
    @Override
    public WebDriver setDriver(DriverProvider driverProvider) {
        String driverName = driverProvider.getBrowserName();
        driver = DriverFactory.createDriver(driverName);
        return driver;
    }

    /**
     * Closes the current WebDriver session and releases associated resources.
     * <p>
     * If the WebDriver instance is not null, it will be quit, and the internal reference
     * will be set to {@code null}.
     * </p>
     *
     * @param driver the WebDriver instance to be closed. Can be {@code null}.
     */
    @Override
    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            this.driver = null;
        }
    }

    /**
     * Checks whether the browser is currently active.
     * <p>
     * A browser is considered active if the WebDriver instance is not null.
     * </p>
     *
     * @return {@code true} if the browser is active, {@code false} otherwise.
     */
    @Override
    public boolean isBrowserActive() {
        return driver != null;
    }

    /**
     * Navigates to the specified URL in the current browser session.
     * <p>
     * If the WebDriver is not initialized, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @param url the URL to navigate to.
     * @throws IllegalStateException if the WebDriver is not initialized.
     */
    @Override
    public void navigateTo(String url) {
        if (driver != null) {
            driver.get(url);
        } else {
            throw new IllegalStateException("Driver is not initialized. Unable to navigate.");
        }
    }

    /**
     * Retrieves the name of the currently active browser.
     * <p>
     * The browser name is derived from the class name of the WebDriver instance (e.g., "ChromeDriver" becomes "Chrome").
     * If the WebDriver is not initialized, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @return the name of the currently active browser.
     * @throws IllegalStateException if the WebDriver is not initialized.
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
