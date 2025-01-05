package driver;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import utils.JsonFileReader;

/**
 * Implementation of {@link DriverProvider} that retrieves the driver type from a JSON file.
 * <p>
 * This class reads a JSON file containing a key-value pair where the key is "driver",
 * and the value specifies the type of driver to be used for the browser session (e.g., "chrome", "firefox").
 * It is useful for dynamically configuring the driver type based on an external JSON file.
 * </p>
 * <p>
 * Example Usage:
 * <pre>
 * JsonDriverProvider provider = new JsonDriverProvider("path/to/config.json");
 * String driverName = provider.getBrowserName();
 * </pre>
 * </p>
 */
public class JsonDriverProvider implements DriverProvider {
    private final String filePath;

    /**
     * Initializes the {@link JsonDriverProvider} with the path to the JSON file.
     * <p>
     * The JSON file should contain a key "driver" with the value being the name of the driver.
     * </p>
     *
     * @param filePath the path to the JSON file containing the driver configuration.
     */
    public JsonDriverProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the driver name from the JSON file.
     * <p>
     * This method reads the JSON file, parses it, and retrieves the value associated with the "driver" key.
     * The returned value represents the driver name (e.g., "chrome", "firefox", "edge").
     * </p>
     *
     * @return the driver name configured in the JSON file.
     * @throws RuntimeException if an error occurs while reading or parsing the JSON file.
     */
    @Override
    public String getBrowserName() {
        try {
            return JsonFileReader.getValue(filePath, "driver");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read the driver from the JSON file", e);
        }
    }
}
