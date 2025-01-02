package driver;

import org.openqa.selenium.WebDriver;

/**
 * Abstract base class for managing WebDriver instances. This class defines
 * the core contract for WebDriver management, including driver lifecycle
 * operations, browser navigation, and window control.
 *
 * <p>The implementing subclasses should provide specific driver
 * initialization logic and browser-specific behaviors.</p>
 *
 * <p>This class ensures consistent management of WebDriver instances
 * across different browser types or platforms, improving maintainability
 * and scalability of Selenium-based test frameworks.</p>
 *
 * @author Shevy Kossovsky
 */
public abstract class DriverManager {
    /**
     * The WebDriver instance managed by this class.
     */
    protected WebDriver driver;

    /**
     * Retrieves the current WebDriver instance. If the driver has not been
     * initialized yet, this method should ensure initialization occurs.
     *
     * @return the active WebDriver instance.
     */
    public abstract WebDriver getDriver();

    /**
     * Sets up the WebDriver instance using the specified DriverProvider.
     *
     * @param driverProvider the provider responsible for configuring and
     *                       supplying the WebDriver instance.
     */
    public abstract void setDriver(String driverName);

    /**
     * Terminates the current WebDriver session and releases associated resources.
     */
    public abstract void quitDriver();

    /**
     * Checks if the browser managed by the WebDriver is currently active.
     *
     * @return true if the browser is active, false otherwise.
     */
    public abstract boolean isBrowserActive();

    /**
     * Navigates the browser to the specified URL.
     *
     * @param url the URL to navigate to.
     */
    public abstract void navigateTo(String url);


    /**
     * Retrieves the name of the current browser managed by the WebDriver.
     *
     * @return the name of the current browser.
     */
    public abstract String getCurrentBrowser();

    /**
     * Refreshes the current page in the browser.
     */
    public abstract void refreshPage();

    /**
     * Maximizes the browser window.
     */
    public abstract void maximizeWindow();

    /**
     * Minimizes the browser window.
     */
    public abstract void minimizeWindow();

    /**
     * Sets the size of the browser window to the specified width and height.
     *
     * @param width  the width of the window in pixels.
     * @param height the height of the window in pixels.
     */
    public abstract void setWindowSize(int width, int height);

    /**
     * Retrieves the current URL being viewed in the browser.
     *
     * @return the current URL as a string.
     */
    public abstract String getCurrentUrl();
}
