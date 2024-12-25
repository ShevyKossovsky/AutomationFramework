package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Manages browser sessions for automated testing.
 * This class implements the BrowserSessionService interface and provides
 * methods to control WebDriver instances for different browser types.
 */
public class BrowserSessionManager implements BrowserSessionService {

    WebDriver driver;

    /**
     * Constructs a new BrowserSessionManager with the given WebDriver.
     *
     * @param driver The WebDriver instance to be managed.
     */
    public BrowserSessionManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Sets the WebDriver for the specified browser type.
     *
     * @param browserType The type of browser to create a driver for.
     */
    @Override
    public void setDriver(BrowserType browserType) {
        driver = BrowserFactory.createDriver(browserType);
    }

    /**
     * Closes the current WebDriver instance if it exists.
     * Sets the driver to null after closing.
     */
    @Override
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Checks if a browser session is currently active.
     *
     * @return true if a WebDriver instance exists, false otherwise.
     */
    @Override
    public boolean isBrowserActive() {
        return driver != null;
    }

    /**
     * Navigates to the specified URL using the current WebDriver instance.
     *
     * @param url The URL to navigate to.
     */
    @Override
    public void navigateTo(String url) {
        if (driver != null) {
            driver.get(url);
        }
    }

    /**
     * Restarts the current WebDriver instance.
     * Closes the current driver, then creates a new one of the same browser type.
     */
    @Override
    public void restartDriver() {
        String currentBrowser = getCurrentBrowser();
        closeDriver();
        setDriver(BrowserType.valueOf(currentBrowser.toUpperCase()));
    }

    /**
     * Determines the type of the current browser being used.
     *
     * @return A string representing the current browser type ("CHROME", "FIREFOX", "IE", "SAFARI", or "UNKNOWN").
     */
    @Override
    public String getCurrentBrowser() {
        if (driver instanceof ChromeDriver) {
            return "CHROME";
        } else if (driver instanceof FirefoxDriver) {
            return "FIREFOX";
        } else if (driver instanceof InternetExplorerDriver) {
            return "IE";
        } else if (driver instanceof SafariDriver) {
            return "SAFARI";
        }
        return "UNKNOWN";
    }
}
