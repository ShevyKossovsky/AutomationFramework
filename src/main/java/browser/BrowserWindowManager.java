package browser;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class BrowserWindowManager implements BrowserWindowService {

    WebDriver driver;

    public BrowserWindowManager(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @Override
    public void minimizeWindow() {
        driver.manage().window().minimize();
    }

    @Override
    public void setWindowSize(int width, int height) {
        if (driver != null) {
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }
}
