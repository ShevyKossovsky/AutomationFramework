import driver.DriverStoreManager;
import driver.StandardDriverManager;
import extensions.LoggerExtension;
import extensions.ScreenshotExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.JsonFileReader;

import java.util.Locale;

/**
 * BaseTest serves as a foundation for all test classes, providing a consistent setup and tear down process
 * for managing WebDriver instances. It integrates extensions for logging and screenshot capturing to enhance
 * debugging and traceability.
 *
 * <p>
 * This class ensures that each test:
 * <ul>
 *     <li>Starts with a properly initialized WebDriver instance.</li>
 *     <li>Incorporates logging and screenshot capturing features for better debugging.</li>
 *     <li>Ends with a cleanup process to terminate the WebDriver session.</li>
 * </ul>
 * </p>
 *
 * <p>
 * To create specific test cases, extend this class and leverage its setup functionality.
 * </p>
 *
 * <p>
 * This class reads configuration values (like the base URL and driver type) from a JSON file to
 * ensure flexibility and separation of configuration from code.
 * </p>
 *
 * @author Shevy Kossovsky
 */
@ExtendWith({LoggerExtension.class, ScreenshotExtension.class})
public class BaseTest {
    /**
     * Manages the lifecycle of the WebDriver instance, including its creation, configuration, and cleanup.
     * The driver manager is responsible for providing and controlling the WebDriver instance.
     */
    protected StandardDriverManager driverManager;

    /**
     * Represents the WebDriver instance used for browser interactions during the test.
     * This WebDriver instance is used by the test methods to interact with the browser.
     */
    protected WebDriver driver;

    /**
     * The base URL used as the starting point for all tests.
     * This URL is fetched from a configuration file and used to navigate to the application before each test.
     */
    private final String baseUrl;

    /**
     * The name of the WebDriver (e.g., "chrome", "firefox") specified in the configuration file.
     * This is used to determine which browser driver to initialize.
     */
    private String driverName;

    /**
     * The file path to the configuration JSON file that contains settings such as URL and driver type.
     * The configuration file is expected to contain details like the base URL for the application and the desired WebDriver.
     */
    private String configFilePath = "C:/Users/Shevy/Desktop/AutomationFramework/config.json";

    /**
     * Constructs a BaseTest instance with the specified driver manager.
     * It reads the configuration values (base URL and driver name) from the JSON file.
     *
     * @param driverManager the manager responsible for handling WebDriver instances.
     *                      This is used to manage WebDriver setup, configuration, and cleanup.
     */
    public BaseTest(StandardDriverManager driverManager) {
        this.driverManager = driverManager;
        // Fetching the base URL and driver type from the configuration file
        this.baseUrl = JsonFileReader.getValue(configFilePath, "url");
        this.driverName = JsonFileReader.getValue(configFilePath, "driver");
    }

    /**
     * Sets up the WebDriver environment before each test.
     * <p>
     * This includes:
     * <ul>
     *     <li>Initializing the WebDriver instance using the specified driver name (e.g., "chrome", "firefox").</li>
     *     <li>Adding the WebDriver instance to a shared map for global access.</li>
     *     <li>Maximizing the browser window to ensure consistent test execution.</li>
     *     <li>Navigating to the specified base URL to prepare the application for testing.</li>
     * </ul>
     * </p>
     */
    @BeforeEach
    public void setUp() {
        // Setting the driver based on the driver name read from the configuration file
        driverManager.setDriver(driverName);
        driver = driverManager.getDriver();
        // Storing the WebDriver instance in a shared map for future access
        DriverStoreManager.addDriverToDriversMap(driverName.toLowerCase() + "Driver", driver);
        // Setting the current driver for later use
        DriverStoreManager.setCurrentDriver(driver);
        // Maximizing the browser window
        driverManager.maximizeWindow();
        // Navigating to the base URL
        driverManager.navigateTo(baseUrl);
    }

    /**
     * Cleans up the WebDriver environment after each test.
     * <p>
     * This includes:
     * <ul>
     *     <li>Quitting the WebDriver instance to release resources and prevent memory leaks.</li>
     * </ul>
     * </p>
     */
    @AfterEach
    public void tearDown() {
        if (driverManager != null) {
            // Quitting the WebDriver instance to ensure resources are released after the test
            driverManager.quitDriver();
        }
    }
}
