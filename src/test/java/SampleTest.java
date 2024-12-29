import browser.*;
import junitLifeCycle.LoggerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(LoggerExtension.class)
public class SampleTest {
    RegularBrowserManager browserManager = new RegularBrowserManager();

    @Test
    public void testSample() {
        browserManager.setDriver(new JsonBrowserProvider("./settings.json"));
        browserManager.navigateTo("https://www.google.com/");
        browserManager.setWindowSize(100, 100);

        browserManager.closeDriver();
    }


}
