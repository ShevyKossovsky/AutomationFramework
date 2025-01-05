package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for reading values from JSON files.
 * This class provides a static method to retrieve a value from a JSON file located in the resources folder by its key.
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * String value = JsonFileReader.getValue("config.json", "driver");
 * System.out.println(value); // Prints the value of the "driver" key from the config.json file
 * </pre>
 *
 * @author Shevy Kossovsky
 */
public class JsonFileReader {

    /**
     * Retrieves the value associated with the specified key from the given JSON file located in the resources folder.
     *
     * <p>This method uses the {@link ClassLoader#getResource(String)} method to locate the file and reads its content.
     * It then parses the content into a {@link JsonObject} and retrieves the value associated with the provided key.</p>
     *
     * @param resourceFileName the name of the JSON file (e.g., "config.json"). The file must be located in the resources folder.
     * @param key              the key whose value is to be retrieved from the JSON file.
     * @return the value associated with the key as a {@link String}.
     * @throws RuntimeException if there is an error reading the file, the file does not exist, or the key is not found in the JSON file.
     *
     * <h3>Example:</h3>
     * <pre>
     * String driver = JsonFileReader.getValue("config.json", "driver");
     * System.out.println(driver); // Outputs the value of "driver" from config.json
     * </pre>
     */
    public static String getValue(String resourceFileName, String key) {
        try {
            // Get the file from resources using ClassLoader
            String content = new String(Files.readAllBytes(
                    Paths.get(JsonFileReader.class.getClassLoader().getResource(resourceFileName).toURI())
            ));

            // Parse the content of the JSON file into a JsonObject
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();

            // Return the value associated with the specified key
            return json.get(key).getAsString();
        } catch (Exception e) {
            // Handle any exception that occurs during reading or parsing the JSON file
            throw new RuntimeException("Failed to read key '" + key + "' from JSON file: " + resourceFileName, e);
        }
    }

}
