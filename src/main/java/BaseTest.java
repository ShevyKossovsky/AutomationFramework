import driver.DriverProvider;
import driver.DriverStoreManager;
import driver.StandardDriverManager;
import extensions.LoggerExtension;
import extensions.ScreenshotExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private final DriverProvider driverProvider;

    /**
     * Represents the WebDriver instance used for browser interactions during the test.
     */
    protected WebDriver driver;

    /**
     * The base URL used as the starting point for all tests, fetched from a configuration file.
     */
    private final String baseUrl;


    /**
     * Constructs a BaseTest instance with the specified driver manager and provider.
     * Reads configuration values (base URL and driver name) from a JSON file.
     *
     * @param driverManager  the manager responsible for handling WebDriver instances.
     * @param driverProvider the provider responsible for WebDriver creation and configuration.
     */
    public BaseTest(StandardDriverManager driverManager, DriverProvider driverProvider) {
        this.driverManager = driverManager;
        this.driverProvider = driverProvider;
        this.baseUrl = JsonFileReader.getValue("config.json", "url");
    }

    /**
     * Sets up the WebDriver environment before each test.
     * <ul>
     *     <li>Initializes the WebDriver instance using the driver provider.</li>
     *     <li>Adds the WebDriver instance to a shared map for global access.</li>
     *     <li>Maximizes the browser window for consistent test execution.</li>
     *     <li>Navigates to the specified base URL to prepare the application for testing.</li>
     * </ul>
     */
    @BeforeEach
    public void setUp() {
        // Initialize the WebDriver instance using the specified driver name
        driverManager.setDriver(driverProvider);
        driver = driverManager.getDriver();

        // Store the WebDriver instance in a shared map for future access
        DriverStoreManager.addDriverToDriversMap(driverProvider.getBrowserName().toLowerCase() + "Driver", driver);

        // Set the current driver for use in tests
        DriverStoreManager.setCurrentDriver(driver);

        // Maximize the browser window
        driverManager.maximizeWindow();

        // Navigate to the base URL
        driverManager.navigateTo(baseUrl);
    }

    /**
     * Cleans up the WebDriver environment after each test by quitting the WebDriver instance.
     * This ensures resources are released and prevents memory leaks.
     */
    @AfterEach
    public void tearDown() {
        if (driverManager != null) {
            driverManager.quitDriver();
        }
    }
}
