package extensions;

import browser.RegularBrowserManager;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotExtension class is a JUnit 5 extension for capturing screenshots when tests fail.
 * It captures a screenshot only if a valid WebDriver instance is available.
 */
public class ScreenshotExtension implements TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotExtension.class);
    private static StoreManager storeManager;

    // Constructor: Ensure storeManager is initialized correctly
    public ScreenshotExtension() {
        storeManager = StoreManager.getInstance(); // Initialize StoreManager using Singleton pattern
    }

    /**
     * This method is called when a test fails. It captures a screenshot if a valid WebDriver is available.
     *
     * @param context The ExtensionContext for the current test
     * @param cause   The exception that caused the test failure
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();

        // Get the browserManager from the store
        RegularBrowserManager browserManager = (RegularBrowserManager) storeManager.getStoreValue("browserManager");

        // Get the WebDriver instance from browserManager
        WebDriver driver = getDriver(browserManager);

        // If WebDriver is available, take the screenshot
        if (driver != null) {
            takeScreenshot(driver, testName);
        } else {
            logger.warn("WebDriver is not available. Cannot capture screenshot for test: {}", testName);
        }
    }

    /**
     * Takes a screenshot and saves it to a file.
     *
     * @param driver   WebDriver instance
     * @param testName The name of the test (used for naming the screenshot file)
     */
    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            // Take the screenshot and save it to a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDate = LocalDateTime.now().format(formatter);

            Path destinationPath = Paths.get("screenshots", testName + "_" + formattedDate + ".png");

            // Ensure the directory exists and copy the screenshot
            Files.createDirectories(destinationPath.getParent());
            Files.copy(screenshot.toPath(), destinationPath);

        } catch (Exception e) {
            logger.error("Error capturing screenshot for test: {}", testName, e);
        }
    }

    /**
     * Retrieves the WebDriver instance from the RegularBrowserManager.
     *
     * @param browserManager RegularBrowserManager instance
     * @return WebDriver instance or null if not found
     */
    private WebDriver getDriver(RegularBrowserManager browserManager) {
        if (browserManager != null) {
            return browserManager.getDriver();
        }
        logger.warn("RegularBrowserManager is not set or does not have a driver.");
        return null;
    }
}
