package driver;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * The DriverStoreManager class is a singleton implementation that manages a central store
 * for storing and retrieving key-value pairs of WebDriver objects. This class is designed to be used in scenarios
 * where WebDriver instances or other objects need to be stored and retrieved globally throughout the execution flow.
 * The class implements a thread-safe mechanism to ensure that the map can be accessed by different threads, ensuring
 * efficient storage and retrieval of WebDriver objects associated with specific keys.
 * <p>
 * The DriverStoreManager ensures that only one instance of the store exists throughout the lifecycle of the application or test execution.
 * </p>
 *
 * @author Shevy Kossovsky
 */
public class DriverStoreManager {

    // A map that holds key-value pairs where keys are Strings and values are WebDriver objects.
    private static final Map<String, WebDriver> driversMap = new HashMap<>();
    private static WebDriver currentDriver;

    /**
     * Private constructor to prevent external instantiation of the class.
     * As a singleton, the DriverStoreManager ensures that only one instance of the store exists
     * throughout the lifecycle of the application or test execution.
     */
    private DriverStoreManager() {
    }

    /**
     * Adds a WebDriver instance to the internal drivers map.
     * The key is a String that uniquely identifies the WebDriver instance, and the value is the WebDriver itself.
     * <p>
     * This method is static, so it can be called without needing an instance of the class.
     * </p>
     *
     * @param key   the key to associate with the WebDriver instance, must not be null
     * @param value the WebDriver instance to be stored, may be null
     * @throws IllegalArgumentException if the key is null
     */
    public static void addDriverToDriversMap(String key, WebDriver value) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        driversMap.put(key, value);
    }

    /**
     * Retrieves the WebDriver instance associated with the given key from the internal map.
     * The key is used to look up the value stored in the map.
     * <p>
     * This method returns null if no WebDriver instance is associated with the key.
     * </p>
     *
     * @param key the key whose associated WebDriver instance is to be returned, must not be null
     * @return the WebDriver instance associated with the key, or null if the key does not exist in the map
     * @throws IllegalArgumentException if the key is null
     */
    public static WebDriver getDriverFromDriversMap(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        return driversMap.get(key);
    }

    public static void setCurrentDriver(WebDriver driver) {
        currentDriver = driver;
    }

    public static WebDriver getCurrentDriver() {
        return currentDriver;
    }

    public static void removeCurrentDriver() {
        currentDriver = null;
    }

    /**
     * Removes the WebDriver instance associated with the given key from the internal map.
     *
     * @param key the key whose associated WebDriver instance is to be removed
     * @throws IllegalArgumentException if the key is null
     */
    public static void removeDriverFromDriversMap(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        driversMap.remove(key);
    }

    /**
     * Checks if the map contains an entry for the given key.
     *
     * @param key the key to check for in the map
     * @return true if the map contains an entry for the key, false otherwise
     * @throws IllegalArgumentException if the key is null
     */
    public static boolean containsDriverInDriversMap(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        return driversMap.containsKey(key);
    }

    /**
     * Clears all entries from the internal map.
     * This removes all stored WebDriver instances or objects.
     */
    public static void clearDriversMap() {
        driversMap.clear();
    }

    /**
     * Returns the number of entries currently stored in the internal map.
     *
     * @return the number of WebDriver instances in the map
     */
    public static int driversNoInMap() {
        return driversMap.size();
    }

    /**
     * Retrieves all WebDriver instances stored in the internal map.
     *
     * @return a Map containing all keys and corresponding WebDriver instances
     */
    public static Map<String, WebDriver> getAllDrivers() {
        return driversMap;
    }
}
