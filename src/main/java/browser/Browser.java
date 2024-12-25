package browser;

import org.openqa.selenium.WebDriver;

/**
 * The {@code Browser} interface defines the contract for browser operations.
 * It includes methods for initializing, navigating, and managing browser sessions.
 */
public interface Browser {

    /**
     * Sets the WebDriver for the specified browser type.
     *
     * @param browserType the type of browser to set the driver for.
     */
    void setDriver(BrowserType browserType);

    /**
     * Navigates to the specified URL.
     *
     * @param url the URL to navigate to.
     */
    void navigateTo(String url);

    /**
     * Checks if the browser is currently active.
     *
     * @return {@code true} if the browser is active; {@code false} otherwise.
     */
    boolean isBrowserActive();

    /**
     * Restarts the current WebDriver instance.
     */
    void restartDriver();

    /**
     * Retrieves the URL of the current page.
     *
     * @return the current URL as a {@code String}.
     */
    String getCurrentUrl();

    /**
     * Closes the current WebDriver and browser session.
     */
    void closeDriver();

    /**
     * Retrieves the name of the current browser.
     *
     * @return the name of the current browser as a {@code String}.
     */
    String getCurrentBrowser();
}
