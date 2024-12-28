package browser;

import org.openqa.selenium.WebDriver;

/**
 * The {@code RegularBrowserManager} class is a central manager for handling WebDriver instances
 * and browser-related operations.
 */
public class RegularBrowserManager {

    private WebDriver driver;
    private BrowserSessionService browserSessionManager;
    private BrowserWindowService browserWindowManager;
    private BrowserPageService browserPageManager;

    /**
     * Constructor to initialize the {@code RegularBrowserManager} and its services with the provided WebDriver.
     *
     * @param driver the WebDriver instance that will be used across the manager's operations.
     */
    public RegularBrowserManager() {
        this.browserSessionManager = new BrowserSessionManager(driver);
        this.browserWindowManager = new BrowserWindowManager(driver);
        this.browserPageManager = new BrowserPageManager(driver);
    }

    /**
     * Retrieves the current WebDriver instance.
     *
     * @return the current WebDriver instance.
     * @throws IllegalStateException if the driver is not initialized.
     */
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Please provide a valid driver.");
        }
        return driver;
    }

    /**
     * Sets the WebDriver by creating a new driver based on the provided {@link BrowserProvider}.
     * This will update the {@code driver} instance for the current session.
     *
     * @param browserProvider the implementation of {@code BrowserProvider} to set the WebDriver.
     */
    public void setDriver(BrowserProvider browserProvider) {
        // Use BrowserFactory to create a new driver and assign it to the instance variable.
        this.driver = this.browserSessionManager.setDriver(browserProvider);
    }

    /**
     * Closes the active WebDriver session.
     */
    public void closeDriver() {
        browserSessionManager.closeDriver(driver);
    }

    /**
     * Checks if the browser is currently active.
     *
     * @return {@code true} if the browser is active, {@code false} otherwise.
     */
    public boolean isBrowserActive() {
        return browserSessionManager.isBrowserActive();
    }

    /**
     * Navigates to the specified URL in the active browser.
     *
     * @param url the URL to navigate to.
     */
    public void navigateTo(String url) {
        browserSessionManager.navigateTo(url);
    }

    /**
     * Restarts the active WebDriver session.
     */
    public void restartDriver() {
        browserSessionManager.restartDriver(driver);
    }

    /**
     * Retrieves the name of the current browser being used.
     *
     * @return the current browser name as a {@code String}.
     */
    public String getCurrentBrowser() {
        return browserSessionManager.getCurrentBrowser();
    }

    /**
     * Refreshes the current page in the active browser.
     */
    public void refreshPage() {
        browserPageManager.refreshPage();
    }

    /**
     * Retrieves the current URL of the active browser.
     *
     * @return the current URL as a {@code String}, or {@code null} if no browser is active.
     */
    public String getCurrentUrl() {
        return driver != null ? driver.getCurrentUrl() : null;
    }

    /**
     * Maximizes the current browser window.
     */
    public void maximizeWindow() {
        browserWindowManager.maximizeWindow();
    }

    /**
     * Minimizes the current browser window.
     */
    public void minimizeWindow() {
        browserWindowManager.minimizeWindow();
    }

    /**
     * Sets the window size of the browser to the specified dimensions.
     *
     * @param width  the width of the window in pixels.
     * @param height the height of the window in pixels.
     */
    public void setWindowSize(int width, int height) {
        browserWindowManager.setWindowSize(width, height);
    }
}
