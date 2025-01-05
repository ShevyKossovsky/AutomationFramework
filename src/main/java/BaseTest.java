import driver.DriverProvider;
import driver.DriverStoreManager;
import driver.StandardDriverManager;
import extensions.LoggerExtension;
import extensions.ScreenshotExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.JsonFileReader;

/**
 * BaseTest serves as a foundation for all test classes, providing a consistent setup and tear down process
 * for managing WebDriver instances. It integrates extensions for logging and screenshot capturing to enhance
 * debugging and traceability.
 *
 * <p>
 * This class ensures:
 * <ul>
 *     <li>Each test starts with a properly initialized WebDriver instance.</li>
 *     <li>Logging and screenshot capturing features are integrated for better debugging.</li>
 *     <li>Proper cleanup is performed to terminate the WebDriver session after each test.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Configuration values (like the base URL and driver type) are read from a JSON file, providing flexibility
 * and separation of configuration from code.
 * </p>
 *
 * <p>
 * To create specific test cases, extend this class and utilize its setup functionality.
 * </p>
 *
 * @author Shevy Kossovsky
 */
@ExtendWith({LoggerExtension.class, ScreenshotExtension.class})
public class BaseTest {

    /**
     * Manages the lifecycle of the WebDriver instance, including its creation, configuration, and cleanup.
     */
    protected StandardDriverManager driverManager;

    /**
     * Represents the WebDriver instance used for browser interactions during the test.
     */
    protected WebDriver driver;

    /**
     * The base URL used as the starting point for all tests, fetched from a configuration file.
     * The base URL is used to navigate to the starting page for all test scenarios.
     */
    private final String baseUrl;

    /**
     * Constructs a BaseTest instance with the specified driver manager and provider.
     * Reads configuration values (base URL and driver name) from a JSON file.
     *
     * <p>
     * This constructor initializes the base URL for the test environment by reading it from
     * a configuration file. The WebDriver manager and driver provider are expected to be set
     * when initializing the driver.
     * </p>
     *
     * @param driverManager  the manager responsible for handling WebDriver instances.
     * @param driverProvider the provider responsible for WebDriver creation and configuration.
     */
    public BaseTest() {
        // Read the base URL from a JSON configuration file.
        baseUrl = JsonFileReader.getValue("config.json", "url");
    }

    /**
     * Sets up the WebDriver environment before each test.
     * <ul>
     *     <li>Initializes the WebDriver instance using the driver provider.</li>
     *     <li>Adds the WebDriver instance to a shared map for global access.</li>
     *     <li>Maximizes the browser window for consistent test execution.</li>
     *     <li>Navigates to the specified base URL to prepare the application for testing.</li>
     * </ul>
     *
     * @param driverManager  the manager used to handle WebDriver initialization and configuration.
     * @param driverProvider the provider used to create and configure the WebDriver instance.
     */
    public void initializeDriver(StandardDriverManager driverManager, DriverProvider driverProvider) {
        // Assign the driver manager to the class field
        this.driverManager = driverManager;

        // Initialize the WebDriver instance using the specified driver provider
        driverManager.setDriver(driverProvider);
        driver = driverManager.getDriver();

        // Store the WebDriver instance in a shared map for global access
        DriverStoreManager.addDriverToDriversMap(driverProvider.getBrowserName().toLowerCase() + "Driver", driver);

        // Set the current driver for use in tests
        DriverStoreManager.setCurrentDriver(driver);

        // Maximize the browser window for consistent test execution
        driverManager.maximizeWindow();

        // Navigate to the base URL to prepare the application for testing
        driverManager.navigateTo(baseUrl);
    }

    /**
     * Cleans up the WebDriver environment after each test by quitting the WebDriver instance.
     * This ensures resources are released, preventing memory leaks and freeing up the WebDriver
     * for subsequent tests.
     *
     * <p>
     * The `tearDown()` method is called automatically after each test method execution to ensure
     * that the WebDriver instance is properly disposed of.
     * </p>
     */
    @AfterEach
    public void tearDown() {
        // Quit the driver to clean up resources
        if (driverManager != null) {
            driver.quit();
        }
    }
}
