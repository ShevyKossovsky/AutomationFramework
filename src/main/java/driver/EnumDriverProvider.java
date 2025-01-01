package driver;

/**
 * Represents a browser provider that uses an enumeration to specify the browser type.
 * This class implements the BrowserProvider interface.
 */
public class EnumDriverProvider implements DriverProvider {
    private final DriverType browserType;

    /**
     * Constructs a new EnumBrowserProvider with the specified browser type.
     *
     * @param browserType The type of browser to be provided, represented by a BrowserType enum.
     */
    public EnumDriverProvider(DriverType browserType) {
        this.browserType = browserType;
    }

    /**
     * Retrieves the name of the browser.
     *
     * @return A String representing the name of the browser, obtained from the BrowserType enum.
     */
    @Override
    public String getBrowserName() {
        return browserType.name();
    }
}
