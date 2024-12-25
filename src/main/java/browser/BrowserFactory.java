package browser;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory class responsible for creating WebDriver instances for various browsers.
 */
public class BrowserFactory {

    /**
     * Creates and returns a WebDriver instance based on the provided browser type.
     *
     * @param browserType the type of the browser (e.g., CHROME, FIREFOX, IE, SAFARI).
     * @return WebDriver instance for the specified browser.
     * @throws IllegalArgumentException if the browser type is unsupported.
     */
    public static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case SAFARI:
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
    }
}