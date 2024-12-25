package browser;

/**
 * Interface for managing browser sessions in a web automation framework.
 */
public interface BrowserSessionService {
    /**
     * Sets the driver for the specified browser type.
     *
     * @param browserType The type of browser to set the driver for.
     */
    void setDriver(BrowserType browserType);

    /**
     * Closes the current browser driver.
     */
    void closeDriver();

    /**
     * Checks if the browser is currently active.
     *
     * @return true if the browser is active, false otherwise.
     */
    boolean isBrowserActive();

    /**
     * Navigates to the specified URL in the current browser session.
     *
     * @param url The URL to navigate to.
     */
    void navigateTo(String url);

    /**
     * Restarts the current browser driver.
     */
    void restartDriver();

    /**
     * Retrieves the name of the currently active browser.
     *
     * @return A string representing the name of the current browser.
     */
    String getCurrentBrowser();
}
