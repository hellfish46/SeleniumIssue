package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static org.testng.Assert.fail;

public class BaseTest {
    private boolean IS_REMOTE = true;
    @BeforeMethod
    public void setUpBrowser() {
        WebDriver mainBrowser = createWebDriver();
        WebDriverRunner.setWebDriver(mainBrowser);
        ScreenShooter.captureSuccessfulTests = true;
        Configuration.browserSize = "1900x1080";
        Configuration.timeout = 20000;
        WebDriver driver = new Augmenter().augment(mainBrowser);
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
    }

    private WebDriver createWebDriver() {
        try {
            if (IS_REMOTE) {
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getChromeOptions());
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } else {
                SelenideConfig selenideConfig = new SelenideConfig();
                selenideConfig.browserCapabilities(getChromeOptions());
                SelenideDriver driver = new SelenideDriver(selenideConfig);
                driver.open();
                return driver.getWebDriver();
            }
        } catch (MalformedURLException e) {
            fail(e.getMessage());
        }
        return null;
    }

    public static ChromeOptions getChromeOptions() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.WARNING);

        System.out.println("Opening chrome driver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("--dns-prefetch-disable", "--no-sandbox", "--ignore-gpu-blacklist");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        prefs.put("profile.default_content_setting_values.geolocation", 1);
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);

        options.setCapability("enableVNC", true);
        options.setCapability("screenResolution", "1920x1080x24");
        options.setCapability("goog:loggingPrefs", logPrefs);
        //options.setCapability("enableVideo", true);
        return options;
    }
}
