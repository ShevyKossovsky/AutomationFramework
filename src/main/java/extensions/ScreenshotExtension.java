package extensions;

import driver.DriverStoreManager;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonFileReader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This extension is particularly useful for debugging and identifying the state
 * of the application during test failures. Screenshots are saved with a timestamp
 * and the test name for easy identification.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Captures screenshots only for failed test cases.</li>
 *     <li>Saves screenshots with a timestamp and test name.</li>
 *     <li>Ensures the output directory exists before saving the screenshot.</li>
 * </ul>
 *
 * @author Shevy Kossovsky
 */
public class ScreenshotExtension implements TestWatcher {

    /**
     * Logger instance for logging messages and errors related to screenshot capturing.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotExtension.class);

    /**
     * Called when a test fails. This method captures a screenshot of the current browser state.
     *
     * @param context The {@link ExtensionContext} that provides information about the test execution.
     * @param cause   The {@link Throwable} that caused the test to fail.
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        WebDriver driver = DriverStoreManager.getCurrentDriver();

        if (driver != null) {
            takeScreenshot(driver, testName);
        } else {
            logger.warn("WebDriver is not available. Cannot capture screenshot for test: {}", testName);
        }
    }

    /**
     * Captures a screenshot using the provided WebDriver instance and saves it to a file.
     *
     * @param driver   The {@link WebDriver} instance used to capture the screenshot.
     * @param testName The name of the test, used in the screenshot filename.
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
