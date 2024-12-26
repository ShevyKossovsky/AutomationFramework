package browser;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * The {@code RegularBrowserManager} class is a central manager for handling WebDriver instances
 * and browser-related operations. This class abstracts session management, window management,
 * and page navigation functionalities, making it easier to interact with browsers in a unified way.
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *     <li>Initialize WebDriver instances for supported browsers.</li>
 *     <li>Manage browser sessions, navigation, and state.</li>
 *     <li>Handle browser window resizing, maximizing, and minimizing.</li>
 *     <li>Perform page-related actions such as refreshing and retrieving the current URL.</li>
 * </ul>
 *
 * <h2>Components:</h2>
 * <ul>
 *     <li>{@code BrowserSessionService}: Manages browser sessions, including initialization and closing.</li>
 *     <li>{@code BrowserWindowService}: Handles browser window operations like resizing and maximizing.</li>
 *     <li>{@code BrowserPageService}: Manages page-specific operations like refreshing and navigation.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * Create an instance of {@code RegularBrowserManager}, set the browser driver using a {@code BrowserProvider},
 * and invoke methods to perform browser operations.
 */
public class RegularBrowserManager {

    private WebDriver driver;
    private BrowserSessionService browserSessionManager;
    private BrowserWindowService browserWindowManager;
    private BrowserPageService browserPageManager;

    /**
     * Constructor to initialize the {@code RegularBrowserManager} and its services.
     */
    public RegularBrowserManager() {
        browserSessionManager = new BrowserSessionManager(driver);
        browserWindowManager = new BrowserWindowManager(driver);
        browserPageManager = new BrowserPageManager(driver);
    }

    public WebDriver getDriver() {
        // Return the WebDriver instance.
        return driver;
    }

    /**
     * Sets the WebDriver using the provided {@link BrowserProvider}.
     *
     * @param browserProvider the implementation of {@code BrowserProvider} to set the WebDriver.
     */
    public void setDriver(BrowserProvider browserProvider) {
        browserSessionManager.setDriver(browserProvider);
    }

    /**
     * Closes the active WebDriver session.
     */
    public void closeDriver() {
        browserSessionManager.closeDriver();
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
        browserSessionManager.restartDriver();
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
