package browser;

public interface BrowserSessionService {
    void setDriver(BrowserType browserType);

    void closeDriver();

    boolean isBrowserActive();

    void navigateTo(String url);

    void restartDriver();

    String getCurrentBrowser();
}
