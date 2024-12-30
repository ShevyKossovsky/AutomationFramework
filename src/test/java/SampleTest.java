import browser.*;
import extensions.LoggerExtension;
import extensions.ScreenshotExtension;
import extensions.StoreManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import waiting.WaitingManager;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith({LoggerExtension.class, ScreenshotExtension.class})
public class SampleTest {
    private static RegularBrowserManager browserManager;
    private static StoreManager storeManager;

    @BeforeAll
    public static void setUp() {
        storeManager = StoreManager.getInstance();
        browserManager = new RegularBrowserManager();
        storeManager.putStoreValue("browserManager", browserManager);
        browserManager.setDriver(new JsonBrowserProvider("./settings.json"));

    }

    @DisplayName("Test01")
    @Test
    public void testSample1() {
        browserManager.navigateTo("https://www.google.com/");
        browserManager.maximizeWindow();
        WebElement searchBox = browserManager.getDriver().findElement(By.name("q"));
        searchBox.sendKeys("Testing Automation Framework");
        searchBox.submit();
        WaitingManager waitingManager = new WaitingManager(browserManager.getDriver());
        waitingManager.waitForPageToLoad(5);
        assertTrue(browserManager.getCurrentUrl().contains("Testing"));

    }

    @DisplayName("Test02")
    @Test
    public void testSample2() {
        browserManager.navigateTo("https://www.google.com/");
        WebElement e = browserManager.getDriver().findElement(By.name("q"));
        assertTrue(e.isDisplayed());
    }

    @AfterAll
    public static void closeSession() {
        browserManager.closeDriver();
    }
}
