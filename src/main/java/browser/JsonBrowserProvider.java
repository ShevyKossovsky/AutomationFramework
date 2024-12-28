package browser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implementation of {@link BrowserProvider} that retrieves the browser type from a JSON file.
 * <p>
 * This class is designed to read a JSON file that contains a key-value pair where the key is "browser",
 * and the value is the name of the browser to be used for the browser session.
 * It is useful when you want to configure the browser type dynamically based on an external JSON file.
 * </p>
 * <p>
 * Usage Example:
 * <pre>
 * JsonBrowserProvider provider = new JsonBrowserProvider("path/to/config.json");
 * String browserName = provider.getBrowserName();
 * </pre>
 * </p>
 */
public class JsonBrowserProvider implements BrowserProvider {
    private final String filePath;

    /**
     * Constructor to initialize the {@link JsonBrowserProvider} with the path to the JSON file.
     * <p>
     * The JSON file should contain a key "browser" with the value being the name of the browser.
     * </p>
     *
     * @param filePath the path to the JSON file containing the browser configuration.
     */
    public JsonBrowserProvider(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the browser name from the JSON file.
     * <p>
     * This method reads the JSON file, parses it, and looks for the "browser" key.
     * The value of the "browser" key is then returned as the browser name.
     * </p>
     *
     * @return the browser name (e.g., "chrome", "firefox", "edge", etc.).
     * @throws RuntimeException if there is an error reading or parsing the JSON file.
     */
    @Override
    public String getBrowserName() {
        try {
            // Read the content of the JSON file into a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the content of the JSON file into a JsonObject
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();

            // Get the value of the "browser" property from the JSON object
            return json.get("browser").getAsString();
        } catch (Exception e) {
            // Handle any exception that occurs during reading or parsing the JSON file
            throw new RuntimeException("Failed to read browser from JSON file", e);
        }
    }
}
