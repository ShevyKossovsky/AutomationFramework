package extensions;

/**
 * Represents a key-value store for storing and retrieving objects.
 */
public interface Store {
    /**
     * Stores a value associated with a given key in the store.
     *
     * @param key   The unique identifier for the value being stored.
     * @param value The object to be stored.
     */
    void putStoreValue(String key, Object value);

    /**
     * Retrieves a value from the store based on the provided key.
     *
     * @param key The unique identifier of the value to be retrieved.
     * @return The object associated with the given key, or null if the key is not found.
     */
    Object getStoreValue(String key);
}
