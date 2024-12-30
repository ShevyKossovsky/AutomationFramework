package extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that implements the Store interface and BeforeAllCallback,
 * providing a mechanism to store and retrieve key-value pairs.
 * This class is designed as a Singleton.
 */
public class StoreManager implements Store, BeforeAllCallback {
    private static StoreManager instance;
    private static final Map<String, Object> storeMap = new HashMap<>();

    // Private constructor to prevent instantiation
    private StoreManager() {
    }

    /**
     * Returns the single instance of StoreManager (Singleton pattern).
     *
     * @return the single instance of StoreManager
     */
    public static StoreManager getInstance() {
        if (instance == null) {
            synchronized (StoreManager.class) {
                if (instance == null) {
                    instance = new StoreManager();
                }
            }
        }
        return instance;
    }

    /**
     * Stores a key-value pair in the internal map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void putStoreValue(String key, Object value) {
        storeMap.put(key, value);
    }

    /**
     * Retrieves the value associated with the given key from the internal map.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if the map contains no mapping for the key
     */
    @Override
    public Object getStoreValue(String key) {
        return storeMap.get(key);
    }

    /**
     * Callback method that is invoked before all tests in the current context.
     * This method is currently empty and can be implemented to perform setup operations.
     *
     * @param context the current extension context
     * @throws Exception if an error occurs during the callback
     */
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // Setup operations can go here if needed
    }
}
