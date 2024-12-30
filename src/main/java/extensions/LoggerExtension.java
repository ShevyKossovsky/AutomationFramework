package extensions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerExtension class is a JUnit 5 extension for logging and handling test lifecycle events.
 * This class logs information about the lifecycle of tests but does not handle screenshots.
 */
public class LoggerExtension implements BeforeAllCallback,
        BeforeEachCallback,
        AfterAllCallback,
        TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(LoggerExtension.class);

    /**
     * This method is called before all tests in the class.
     *
     * @param context The ExtensionContext for the current test class
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.info("Before all tests in class: {}", context.getTestClass().orElseThrow().getName());
    }

    /**
     * This method is called before each individual test.
     *
     * @param context The ExtensionContext for the current test
     */
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName();
        logger.info("Before each test: {}", testName);
    }

    /**
     * This method is called after all tests in the class.
     *
     * @param context The ExtensionContext for the current test class
     */
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("After all tests in class: {}", context.getTestClass().orElseThrow().getName());
    }

    /**
     * This method is called when a test fails. It logs the failure information.
     *
     * @param context The ExtensionContext for the current test
     * @param cause   The exception that caused the test failure
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        logger.error("Test failed: {}", testName, cause);
    }
}
