import browser.Browser;
import browser.RegularBrowserManager;
import browser.BrowserType;
import org.junit.jupiter.api.Test;

public class SampleTest {
    RegularBrowserManager browserManager = new RegularBrowserManager();

    @Test
    public void testSample() {
        browserManager.setDriver(BrowserType.CHROME);
        browserManager.navigateTo("https://www.google.com/");
        browserManager.setWindowSize(200, 400);
        browserManager.closeDriver();
    }
}
