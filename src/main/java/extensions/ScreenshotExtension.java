package extensions;

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
 * ScreenshotExtension is a JUnit 5 extension designed to capture screenshots when test cases fail.
 * <p>
 * This class is particularly useful for debugging and identifying the state of the application at the time of failure.
 * It uses Selenium WebDriver to capture the current browser window's state as an image file.
 * </p>
 * <p>
 * Key Features:
 * <ul>
 *     <li>Captures screenshots only for failed test cases.</li>
 *     <li>Automatically saves screenshots with a timestamp and test name for easy identification.</li>
 *     <li>Ensures the output directory exists before saving files.</li>
 * </ul>
 * </p>
 */
public class ScreenshotExtension implements TestWatcher {

    /**
     * Logger instance to log messages and errors related to screenshot capturing.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotExtension.class);

    /**
     * Invoked when a test fails. This method attempts to capture a screenshot of the current browser state.
     *
     * @param context The ExtensionContext representing the current test execution state.
     * @param cause   The Throwable that caused the test to fail.
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();


        // Retrieve the first available WebDriver instance from DriverStoreManager
        WebDriver driver = DriverStoreManager.getCurrentDriver();

        // Check if a valid WebDriver instance is available
        if (driver != null) {
            takeScreenshot(driver, testName);
        } else {
            logger.warn("WebDriver is not available. Cannot capture screenshot for test: {}", testName);
        }
    }

    /**
     * Captures a screenshot using the provided WebDriver instance and saves it to a file.
     *
     * <p>
     * The screenshot is saved in the "screenshots" directory with a filename that includes the test name
     * and a timestamp to avoid overwriting files from previous test runs.
     * </p>
     *
     * @param driver   The WebDriver instance used to capture the screenshot.
     * @param testName The name of the test, used to construct the screenshot filename.
     */
    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            // Capture the screenshot as a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Format the current date and time for the screenshot filename
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDate = LocalDateTime.now().format(formatter);

            // Define the destination path for the screenshot
            Path destinationPath = Paths.get("screenshots", testName + "_" + formattedDate + ".png");

            // Ensure the destination directory exists
            Files.createDirectories(destinationPath.getParent());

            // Copy the screenshot file to the destination path
            Files.copy(screenshot.toPath(), destinationPath);

            logger.info("Screenshot saved at: {}", destinationPath.toAbsolutePath());
        } catch (Exception e) {
            logger.error("Error capturing screenshot for test: {}", testName, e);
        }
    }
}
