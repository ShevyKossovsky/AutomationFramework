import driver.*;
import extensions.LoggerExtension;
import extensions.ScreenshotExtension;
import extensions.DriverStoreManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import waiting.WaitingManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({LoggerExtension.class, ScreenshotExtension.class})
public class SampleTest {
    private static RegularDriverManager driverManager;

    @BeforeAll
    public static void setUp() {
        driverManager = new RegularDriverManager();
        // Initialize the driver and add to DriverStoreManager
        driverManager.setDriver(new JsonDriverProvider("./settings.json"));
        DriverStoreManager.addDriverToDriversMap("chromeDriver", driverManager.getDriver());
        DriverStoreManager.setCurrentDriver(driverManager.getDriver());
        driverManager.navigateTo("https://www.google.com/");

    }

    @DisplayName("Test01")
    @Test
    public void testSample1() {
        driverManager.maximizeWindow();
        WebElement searchBox = driverManager.getDriver().findElement(By.name("q"));
        searchBox.sendKeys("Testing Automation Framework");
        searchBox.submit();

        WaitingManager waitingManager = new WaitingManager(driverManager.getDriver());
        waitingManager.waitForPageToLoad(5);
        assertTrue(driverManager.getCurrentUrl().contains("Testing"));
    }

    @DisplayName("Test02")
    @Test
    public void testSample2() {
        WebElement e = driverManager.getDriver().findElement(By.name("q"));
        assertFalse(e.isDisplayed());
    }

    @AfterAll
    public static void closeSession() {
        driverManager.quitDriver();
        DriverStoreManager.removeCurrentDriver();

    }
}
