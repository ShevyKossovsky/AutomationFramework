package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for reading values from JSON files.
 * This class provides a static method to retrieve a value from a JSON file by its key.
 *
 * Usage Example:
 * <pre>
 * String value = JsonFileReader.getValue("path/to/config.json", "driver");
 * </pre>
 *
 * @author Shevy Kossovsky
 */
public class JsonFileReader {

    /**
     * Retrieves the value associated with the specified key from the given JSON file.
     *
     * @param filePath the path to the JSON file
     * @param key      the key whose value is to be retrieved
     * @return the value associated with the key
     * @throws RuntimeException if there is an error reading or parsing the JSON file
     */
    public static String getValue(String filePath, String key) {
        try {
            // Read the content of the JSON file into a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the content of the JSON file into a JsonObject
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();

            // Return the value associated with the specified key
            return json.get(key).getAsString();
        } catch (Exception e) {
            // Handle any exception that occurs during reading or parsing the JSON file
            throw new RuntimeException("Failed to read key '" + key + "' from JSON file: " + filePath, e);
        }
    }
}
