package browser;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * Manages browser window operations using WebDriver.
 * This class implements the BrowserWindowService interface.
 */
public class BrowserWindowManager implements BrowserWindowService {

    /** The WebDriver instance used for browser interactions. */
    WebDriver driver;

    /**
     * Constructs a new BrowserWindowManager with the specified WebDriver.
     *
     * @param driver The WebDriver instance to be used for browser window operations.
     */
    public BrowserWindowManager(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Maximizes the browser window.
     * This method uses the WebDriver to maximize the current browser window.
     */
    @Override
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Minimizes the browser window.
     * This method uses the WebDriver to minimize the current browser window.
     */
    @Override
    public void minimizeWindow() {
        driver.manage().window().minimize();
    }

    /**
     * Sets the size of the browser window to the specified dimensions.
     *
     * @param width  The desired width of the browser window in pixels.
     * @param height The desired height of the browser window in pixels.
     */
    @Override
    public void setWindowSize(int width, int height) {
        if (driver != null) {
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }
}
