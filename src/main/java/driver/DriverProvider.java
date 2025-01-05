package driver;

/**
 * Provides an interface for retrieving the name of a browser.
 */
public interface DriverProvider {
    /**
     * Gets the name of the browser.
     *
     * @return A String representing the name of the browser.
     */
    String getBrowserName();
}