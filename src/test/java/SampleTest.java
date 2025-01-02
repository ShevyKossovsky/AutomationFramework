import actions.WebElementActions;
import driver.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class SampleTest extends BaseTest {

    public SampleTest() {
        super(new StandardDriverManager());
    }

    @DisplayName("Test01 - Verify Search Functionality")
    @Test
    public void testSample1() {

        WebElement searchBox;

        searchBox = driver.findElement(By.name("q"));

        WebElementActions.sendKeys(driver, searchBox, "Testing Automation Framework");
        WebElementActions.submit(driver, searchBox);
        assertTrue(driverManager.getCurrentUrl().contains("Testing"),
                "URL does not contain the expected text.");

    }
}
