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
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        logger.info("Before each test: {}", testName);
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
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("After all tests in class: {}", context.getTestClass().orElseThrow().getName());
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

}
