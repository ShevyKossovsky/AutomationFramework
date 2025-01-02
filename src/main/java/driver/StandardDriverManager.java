package driver;

import org.openqa.selenium.WebDriver;

/**
 * The {@code StandardDriverManager} class is a concrete implementation of the {@link DriverManager} abstract class.
 * It centralizes WebDriver management and provides tools for browser operations such as session handling,
 * window management, and page interactions.
 *
 * <p>This class is designed to work with web-based drivers (e.g., ChromeDriver, FirefoxDriver) and integrates
 * functionality for managing the browser lifecycle, navigation, and interaction seamlessly.</p>
 *
 * <p>By extending {@link DriverManager}, this class provides a cohesive and modular structure for
 * WebDriver-based automation, enabling easier maintenance and reusability of code across projects.</p>
 *
 * @author Shevy Kossovsky
 */
public class StandardDriverManager extends DriverManager {

    private WebDriver driver;
    private DriverSessionService driverSessionManager;
    private DriverWindowService browserWindowManager;
    private DriverPageService browserPageManager;

    /**
     * Default constructor for {@code StandardDriverManager}.
     *
     * <p>By default, this constructor initializes a {@link DriverSessionService} instance with a
     * {@code null} driver. Other services such as {@link DriverWindowService} and
     * {@link DriverPageService} will be initialized when a valid WebDriver instance is set using
     */
    public StandardDriverManager() {
        this.driverSessionManager = new DriverSessionManager(null);
    }

    /**
     * Retrieves the currently active WebDriver instance being used in this session.
     *
     * <p>If no WebDriver has been initialized, this method will throw an {@link IllegalStateException}.
     * This ensures that any attempt to access the driver without proper initialization is flagged.</p>
     *
     * @return the active {@link WebDriver} instance.
     * @throws IllegalStateException if the driver is not initialized.
     */
    @Override
    public WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        throw new IllegalStateException("Driver is not initialized. Please provide a valid driver.");
    }

    /**
     * Configures the WebDriver by using a specific {@link DriverProvider} implementation.
     *
     * <p>This method initializes the WebDriver instance for the current session, and also sets up
     * related services such as window management and page management to enable further operations.</p>
     *
     * @param driverProvider an implementation of {@link DriverProvider} responsible for creating
     *                       and configuring the WebDriver instance.
     */
    @Override
    public void setDriver(String driverName) {
        this.driver = this.driverSessionManager.setDriver(driverName);
        this.browserWindowManager = new DriverWindowManager(driver);
        this.browserPageManager = new DriverPageManager(driver);
    }

    /**
     * Closes the WebDriver session currently in use.
     *
     * <p>Releases all resources associated with the driver and terminates the browser instance.
     * This method should be called at the end of a test or operation to ensure proper cleanup.</p>
     */
    @Override
    public void quitDriver() {
        driverSessionManager.quitDriver(driver);
    }

    /**
     * Checks whether the browser is currently active.
     *
     * <p>This method verifies if a WebDriver session is still active and valid, which can be useful
     * for ensuring that operations are only performed on an active session.</p>
     *
     * @return {@code true} if the browser is active, {@code false} otherwise.
     */
    @Override
    public boolean isBrowserActive() {
        return driverSessionManager.isBrowserActive();
    }

    /**
     * Navigates to the specified URL in the active browser session.
     *
     * <p>This method uses the WebDriver instance to load a web page by its URL. The browser must
     * already be active, and a valid URL must be provided.</p>
     *
     * @param url the URL to navigate to. This should be a valid web address.
     */
    @Override
    public void navigateTo(String url) {
        driverSessionManager.navigateTo(url);
    }


    /**
     * Retrieves the name of the current browser being used.
     *
     * <p>The browser name is determined by the {@link DriverProvider} used to initialize the WebDriver.
     * This can be useful for logging or debugging purposes.</p>
     *
     * @return the name of the browser as a {@code String}.
     */
    @Override
    public String getCurrentBrowser() {
        return driverSessionManager.getCurrentBrowser();
    }

    /**
     * Refreshes the current page in the browser.
     *
     * <p>This method reloads the current web page using the WebDriver instance. It can be useful
     * for testing page reload behavior or applying updates to dynamic content.</p>
     */
    @Override
    public void refreshPage() {
        browserPageManager.refreshPage();
    }

    /**
     * Maximizes the browser window to fill the screen.
     *
     * <p>This method adjusts the browser window size for optimal visibility, which is particularly
     * useful when running tests on desktop environments.</p>
     */
    @Override
    public void maximizeWindow() {
        browserWindowManager.maximizeWindow();
    }

    /**
     * Minimizes the browser window.
     *
     * <p>This can be used to reduce the browser's visibility, which may be useful in scenarios
     * where the browser needs to remain open but not actively displayed.</p>
     */
    @Override
    public void minimizeWindow() {
        browserWindowManager.minimizeWindow();
    }

    /**
     * Sets the browser window to a specific size.
     *
     * <p>This method allows precise control over the browser window dimensions, which is useful
     * for testing responsive web designs.</p>
     *
     * @param width  the desired width of the browser window in pixels.
     * @param height the desired height of the browser window in pixels.
     */
    @Override
    public void setWindowSize(int width, int height) {
        browserWindowManager.setWindowSize(width, height);
    }

    /**
     * Retrieves the current URL of the active browser session.
     *
     * <p>If no browser is active, this method will return {@code null}. This can be useful for
     * verification purposes in automated tests.</p>
     *
     * @return the current URL as a {@code String}, or {@code null} if no session is active.
     */
    @Override
    public String getCurrentUrl() {
        if (driver != null) {
            return driver.getCurrentUrl();
        }
        return null;
    }
}
