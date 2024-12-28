package junitLifeCycle;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class LoggerExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback  {

    private static final Logger logger = LoggerFactory.getLogger(LoggerExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.info("Before all tests in class: {}", context.getTestClass().orElseThrow().getName());
        // Initialize global resources, e.g., WebDriver, DB connections
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        logger.info("Before each test: {}", testName);
        // Load test data or set up preconditions for each test
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        Optional<Throwable> testException = context.getExecutionException();

        if (testException.isPresent()) {
            logger.error("Test failed: {}", testName, testException.get());
            takeScreenshot(testName); // Capture screenshot on failure
        } else {
            logger.info("Test passed: {}", testName);
        }

        // Clean up resources specific to the test
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("After all tests in class: {}", context.getTestClass().orElseThrow().getName());
        // Clean up global resources, e.g., close WebDriver, DB connections
    }

    /**
     * Takes a screenshot and saves it to the `screenshots` directory.
     * This is just a placeholder for real screenshot logic.
     */
    private void takeScreenshot(String testName) {
        try {
            Path screenshotDir = Paths.get("screenshots");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }
            Path screenshotPath = screenshotDir.resolve(testName + ".png");
            // Replace with actual screenshot logic (e.g., WebDriver screenshot)
            Files.createFile(screenshotPath);
            logger.info("Screenshot saved at: {}", screenshotPath.toAbsolutePath());
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test: {}", testName, e);
        }
    }

    /**
     * Example method to send test results to an external reporting tool.
     */
    private void sendTestStatusToReportPortal(String testName, String status) {
        // Replace with integration logic for Report Portal or another tool
        logger.info("Sending test result to Report Portal: Test={} Status={}", testName, status);
    }
}
