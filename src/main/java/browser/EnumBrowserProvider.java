package browser;

/**
 * Represents a browser provider that uses an enumeration to specify the browser type.
 * This class implements the BrowserProvider interface.
 */
public class EnumBrowserProvider implements BrowserProvider {
    private final BrowserType browserType;

    /**
     * Constructs a new EnumBrowserProvider with the specified browser type.
     *
     * @param browserType The type of browser to be provided, represented by a BrowserType enum.
     */
    public EnumBrowserProvider(BrowserType browserType) {
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
