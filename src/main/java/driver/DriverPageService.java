package driver;

/**
 * Provides services for browser page operations.
 */
public interface DriverPageService {
    /**
     * Refreshes the current page in the browser.
     * This method reloads the current page, discarding any cached content.
     */
    void refreshPage();
}