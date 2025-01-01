package driver;
import org.openqa.selenium.WebDriver;

/**
 * The {@code RegularDriverManager} class serves as a central point for managing browser-related operations
 * and handling WebDriver instances in a structured and modular way. This class provides a variety of methods
 * to control and interact with the browser, including setting up WebDriver, managing browser windows, and
 * navigating through web pages.
 *
 * <p>It integrates session management, window management, and page interaction into a single
 * cohesive interface, making it easier to write maintainable and reusable browser automation code.</p>
 */
public class RegularDriverManager {

    private WebDriver driver;
    private DriverSessionService driverSessionManager;
    private DriverWindowService browserWindowManager;
    private DriverPageService browserPageManager;

    /**
     * Default constructor for {@code RegularDriverManager}.
     *
     * <p>By default, this constructor initializes a {@link DriverSessionService} instance with a
     * {@code null} driver. Other services such as {@link DriverWindowService} and
     * {@link DriverPageService} will be initialized when a valid WebDriver instance is set using
     * {@link #setDriver(DriverProvider)}.</p>
     */
    public RegularDriverManager() {
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
    public void setDriver(DriverProvider driverProvider) {
        this.driver = this.driverSessionManager.setDriver(driverProvider);
        this.browserWindowManager = new DriverWindowManager(driver);
        this.browserPageManager = new DriverPageManager(driver);
    }

    /**
     * Closes the WebDriver session currently in use.
     *
     * <p>Releases all resources associated with the driver and terminates the browser instance.
     * This method should be called at the end of a test or operation to ensure proper cleanup.</p>
     */
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
    public void navigateTo(String url) {
        driverSessionManager.navigateTo(url);
    }

    /**
     * Restarts the WebDriver session by closing the current browser and starting a new session.
     *
     * <p>This method can be used to reset the browser state, ensuring a clean environment for
     * subsequent operations or tests.</p>
     */
    public void restartDriver() {
        driverSessionManager.restartDriver(driver);
    }

    /**
     * Retrieves the name of the current browser being used.
     *
     * <p>The browser name is determined by the {@link DriverProvider} used to initialize the WebDriver.
     * This can be useful for logging or debugging purposes.</p>
     *
     * @return the name of the browser as a {@code String}.
     */
    public String getCurrentBrowser() {
        return driverSessionManager.getCurrentBrowser();
    }

    /**
     * Refreshes the current page in the browser.
     *
     * <p>This method reloads the current web page using the WebDriver instance. It can be useful
     * for testing page reload behavior or applying updates to dynamic content.</p>
     */
    public void refreshPage() {
        browserPageManager.refreshPage();
    }

    /**
     * Maximizes the browser window to fill the screen.
     *
     * <p>This method adjusts the browser window size for optimal visibility, which is particularly
     * useful when running tests on desktop environments.</p>
     */
    public void maximizeWindow() {
        browserWindowManager.maximizeWindow();
    }

    /**
     * Minimizes the browser window.
     *
     * <p>This can be used to reduce the browser's visibility, which may be useful in scenarios
     * where the browser needs to remain open but not actively displayed.</p>
     */
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
    public String getCurrentUrl() {
        if (driver != null) {
            return driver.getCurrentUrl();
        }
        return null;
    }

}
