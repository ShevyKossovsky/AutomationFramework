package actions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static java.awt.SystemColor.text;

/**
 * This class contains various static utility methods to perform actions on web elements.
 * These methods are used to interact with elements on the web page in a variety of ways,
 * including clicking, double-clicking, sending keys, dragging and dropping, scrolling, and more.
 */
public class WebElementActions {

    /**
     * Clicks on the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to be clicked.
     */
    public static void click(WebDriver driver, WebElement element) {
        element.click();
    }

    /**
     * Performs a double click on the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to be double-clicked.
     */
    public static void doubleClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }

    /**
     * Submits the form containing the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to be submitted.
     */
    public static void submit(WebDriver driver, WebElement element) {
        element.submit();
    }

    /**
     * Sends the specified keys to the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to send keys to.
     * @param keys    The keys to send to the element.
     */
    public static void sendKeys(WebDriver driver, WebElement element, String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    /**
     * Retrieves the text of the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to retrieve text from.
     * @return The text of the web element.
     */
    public static String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

    /**
     * Retrieves the value of the given web element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to retrieve the value from.
     * @return The value of the web element.
     */
    public static String getElementValue(WebDriver driver, WebElement element) {
        return element.getAttribute("value");
    }

    /**
     * Checks if the given web element is displayed in the viewport.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to check.
     * @return true if the element is in the viewport, false otherwise.
     */
    public static boolean isElementInViewport(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (boolean) js.executeScript(
                "return (arguments[0].offsetTop >= window.pageYOffset && arguments[0].offsetTop <= window.pageYOffset + window.innerHeight);",
                element
        );
    }

    /**
     * Checks if the given web element is displayed.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to check.
     * @return true if the element is displayed, false otherwise.
     */
    public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Performs a drag-and-drop action from the source element to the target element.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     * @param source The source WebElement to drag.
     * @param target The target WebElement to drop onto.
     */
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).build().perform();
    }

    /**
     * Performs a drag-and-drop action from the source element to a specified location
     * using the given offsets.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param source  The source WebElement to drag.
     * @param xOffset The horizontal offset to move the element.
     * @param yOffset The vertical offset to move the element.
     */
    public static void dragAndDropTo(WebDriver driver, WebElement source, int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(source, xOffset, yOffset).build().perform();
    }

    /**
     * Scrolls the web page to the specified element.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to scroll to.
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Checks if the text of the given web element contains the specified text.
     *
     * @param driver  The WebDriver instance used for interacting with the web page.
     * @param element The WebElement to check.
     * @param text    The text to check for in the element's text.
     * @return true if the element's text contains the specified text, false otherwise.
     */
    public static boolean containsText(WebDriver driver, WebElement element, String text) {
        return element.getText().contains(text);
    }

    /**
     * Finds an element that contains the specified text, regardless of its tag.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     * @param text   The text to search for within the element.
     * @return The WebElement containing the specified text, or null if not found.
     */
    public WebElement findElementByText(WebDriver driver, String text) {
        try {
            return driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        } catch (NoSuchElementException e) {
            return null; // Return null if no matching element is found
        }
    }
}

