package driver;

/**
 * Provides services for browser page operations.
 *
 * @author Shevy Kossovsky
 */
public interface DriverPageService {
    /**
     * Refreshes the current page in the browser.
     * This method reloads the current page, discarding any cached content.
     */
    void refreshPage();
}