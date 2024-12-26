import browser.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import waiting.WaitingManager;

public class SampleTest {
    RegularBrowserManager browserManager = new RegularBrowserManager();

    @Test
    public void testSample() {
        browserManager.setDriver(new JsonBrowserProvider("./settings.json"));
        browserManager.navigateTo("https://www.google.com/");
        browserManager.setWindowSize(200, 400);
        WebElement ele=browserManager.getDriver().findElement(By.id("APjFqb"));
        WaitingManager waitingManager = new WaitingManager(browserManager.getDriver());
        waitingManager.waitForElementToBeVisible(ele, 10);



        browserManager.closeDriver();

    }
}
