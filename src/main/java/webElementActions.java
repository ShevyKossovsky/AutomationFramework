import org.openqa.selenium.By;

public interface webElementActions {

    void click(By locator);

    void doubleClick(By locator);

    void submit(By locator);

    void sendKeys(By locator, String keys);

    String getElementText(By locator);

    boolean isElementPresent(By locator);

    void dragAndDrop(By sourceLocator, By targetLocator);

    void dragAndDropTo(By sourceLocator, int xOffset, int yOffset);

    void scrollToElement(By locator);

    boolean containsText(By locator, String text);


}
