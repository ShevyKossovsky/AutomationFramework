package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserSessionManager implements BrowserSessionService {

    WebDriver driver;

    public BrowserSessionManager() {

    }

    @Override
    public void setDriver(BrowserType browserType) {
        driver = BrowserFactory.createDriver(browserType);

    }

    @Override
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Override
    public boolean isBrowserActive() {
        return driver != null;

    }

    @Override
    public void navigateTo(String url) {
        if (driver != null) {
            driver.get(url);
        }
    }

    @Override
    public void restartDriver() {
        String currentBrowser = getCurrentBrowser();
        closeDriver();
        setDriver(BrowserType.valueOf(currentBrowser.toUpperCase()));
    }

    @Override
    public String getCurrentBrowser() {
        if (driver instanceof ChromeDriver) {
            return "CHROME";
        } else if (driver instanceof FirefoxDriver) {
            return "FIREFOX";
        } else if (driver instanceof InternetExplorerDriver) {
            return "IE";
        } else if (driver instanceof SafariDriver) {
            return "SAFARI";
        }
        return "UNKNOWN";
    }

}
