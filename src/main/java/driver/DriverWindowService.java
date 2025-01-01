package driver;

/**
 * Provides services for managing browser window operations.
 */
public interface DriverWindowService {
    
    /**
     * Maximizes the browser window to fill the entire screen.
     */
    void maximizeWindow();
    
    /**
     * Minimizes the browser window to the taskbar.
     */
    void minimizeWindow();
    
    /**
     * Sets the browser window size to the specified dimensions.
     *
     * @param width  The desired width of the browser window in pixels.
     * @param height The desired height of the browser window in pixels.
     */
    void setWindowSize(int width, int height);
}
