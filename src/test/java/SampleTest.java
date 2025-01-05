import actions.WebElementActions;
import driver.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;


public class SampleTest extends BaseTest {


    @DisplayName("Test01 - Verify Search Functionality in Chrome")
    @Test
    public void testSample1() {
        initializeDriver(new StandardDriverManager(), new EnumDriverProvider(DriverType.CHROME));

        WebElement searchBox;

        searchBox = driver.findElement(By.name("q"));

        WebElementActions.sendKeys(driver, searchBox, "Testing Automation Framework");
        WebElementActions.submit(driver, searchBox);
        assertTrue(driverManager.getCurrentUrl().contains("Testing"),
                "URL does not contain the expected text.");

    }

    @DisplayName("Test02 - Verify Search Functionality in Microsoft Edge")
    @Test
    public void testSample2() {
        initializeDriver(new StandardDriverManager(), new EnumDriverProvider(DriverType.EDGE));

        WebElement searchBox;

        searchBox = driver.findElement(By.name("q"));

        WebElementActions.sendKeys(driver, searchBox, "Testing Automation Framework");
        WebElementActions.submit(driver, searchBox);
        assertTrue(driverManager.getCurrentUrl().contains("Testing"),
                "URL does not contain the expected text.");
    }
}
