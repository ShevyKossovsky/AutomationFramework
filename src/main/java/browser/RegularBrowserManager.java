package browser;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * The {@code RegularBrowserManager} class manages WebDriver instances for various browsers.
 * It provides methods to initialize, navigate, refresh, and close browsers,
 * as well as check if a browser is active and retrieve the current URL or browser type.
 */
public class RegularBrowserManager {

    private WebDriver driver;
    private BrowserSessionService browserSessionManager;
    private BrowserWindowService browserWindowManager;
    private BrowserPageService browserPageManager;

    /**
     * Constructor to initialize the RegularBrowserManager and the BrowserSessionService.
     */
    public RegularBrowserManager() {
        browserSessionManager = new BrowserSessionManager(driver);
        browserWindowManager = new BrowserWindowManager(driver);
        browserPageManager = new BrowserPageManager(driver);
    }

    /**
     * Sets the WebDriver instance for the specified browser type.
     *
     * @param browserType the type of browser to initialize.
     */
    public void setDriver(BrowserType browserType) {
        browserSessionManager.setDriver(browserType);
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
     * @return the current browser as a {@code String}.
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
     * @return the current URL as a {@code String}.
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
