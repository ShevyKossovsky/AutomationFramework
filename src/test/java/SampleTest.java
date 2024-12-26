import browser.RegularBrowserManager;
import browser.BrowserType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
