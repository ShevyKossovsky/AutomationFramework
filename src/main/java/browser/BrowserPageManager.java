package browser;

import org.openqa.selenium.WebDriver;

public class BrowserPageManager implements BrowserPageService {
    WebDriver driver;

    public BrowserPageManager(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void refreshPage() {
        if (driver != null) {
            driver.navigate().refresh();
        }
    }
}
