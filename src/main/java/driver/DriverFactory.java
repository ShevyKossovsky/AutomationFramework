package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory class responsible for creating WebDriver instances for various browsers.
 * <p>
 * This class provides a static method to create a WebDriver instance based on the
 * browser name provided by a {@link DriverProvider}.
 * <p>
 * Usage:
 * - Implement the {@link DriverProvider} interface to define how the browser name
 * is retrieved (e.g., from an enum, a JSON file, etc.).
 * - Pass the implementation to the `createDriver` method to initialize the desired
 * WebDriver.
 * <p>
 * Supported browsers:
 * - CHROME: Google Chrome
 * - EDGE: Microsoft Edge
 * - FIREFOX: Mozilla Firefox
 * - IE: Internet Explorer
 * - SAFARI: Safari (Mac only)
 * <p>
 * Dependency:
 * This class uses WebDriverManager for automatic setup of browser drivers.
 * Ensure that WebDriverManager is properly included in your project dependencies.
 */
public class DriverFactory {

    /**
     * Creates a WebDriver instance based on the browser name provided by the {@link DriverProvider}.
     *
     * @param browserProvider an implementation of {@link DriverProvider} that provides the browser name.
     * @return the WebDriver instance for the specified browser.
     * @throws IllegalArgumentException if the browser name is not supported.
     */
    public static WebDriver createDriver(DriverProvider browserProvider) {
        // Retrieve the browser name from the provider and convert it to uppercase.
        String browserName = browserProvider.getBrowserName().toUpperCase();
        // Determine the appropriate WebDriver based on the browser name.
        switch (browserName) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();

            case "SAFARI":
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();

            default:
                // Throw an exception if the browser name is not supported.
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}
