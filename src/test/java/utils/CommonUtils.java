package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;

public class CommonUtils implements ITestListener, IInvokedMethodListener {

    // Declare AppiumDriver and AndroidDriver
    public AndroidDriver androidDriver;
    public static AppiumDriver driver;
    public WebDriver webDriver;
    private AppiumDriverLocalService service;

    protected static final ThreadLocal<ITestResult> currentResults = new ThreadLocal<>();

    /**
     * Sets up the Appium driver.
     *
     * @param platformName  The platform name (e.g., "Android").
     * @param deviceName    The device name.
     * @param uri           The application URI.
     * @param automationName The automation name (e.g., "UiAutomator2").
     * @throws MalformedURLException If the URL is malformed.
     */
    public void setup(String platformName, String deviceName, String uri, String automationName) throws MalformedURLException {
        // Removed commented-out code related to file path

        // Create UiAutomator2Options
        UiAutomator2Options options = uiAutomator2OptionsApp(deviceName, uri, automationName);

        // Initialize AndroidDriver
        androidDriver = new AndroidDriver(
                new URL(Constants.REMOTE_URL+":"+Constants.REMOTE_PORT), options
        );
    }

    public void setupMobileWeb(String platformName, String deviceName,String uri, String browser, String automationName) throws MalformedURLException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .withBrowserName(MobileBrowserType.BROWSER)
                .setDeviceName("Android Emulator");
        webDriver = new AndroidDriver(service.getUrl(), options);
        //This time out is set because test can be run on slow Android SDK emulator
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(5)), this);

    }

    /**
     * Reads properties from the config.properties file.
     *
     * @return The Properties object containing the key-value pairs from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public static Properties read_properties() throws IOException {

        File file = new File("src/test/resources/config.properties");
        Properties prop = new Properties();

        // Use try-with-resources for automatic resource management
        try (InputStreamReader is = new InputStreamReader(Files.newInputStream(file.toPath()))) {
            prop.load(is);
        }
        return prop;
    }

    public static void main(String... args) throws IOException {
        CommonUtils.read_properties();
    }

    // Not used in the current code, might be for future Chrome testing
    private ChromeOptions uiAutomator2OptionsChrome(String deviceName,String uri, String browser, String automationName) throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("browserName", browser);
//        capabilities.setCapability("automationName", automationName);
//        capabilities.setCapability("noReset", true); // Optional: Prevents app reset on each session

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        chromeOptions.setExperimentalOption("excludeSwitches", singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }

    // Creates UiAutomator2Options for app testing
    private static UiAutomator2Options uiAutomator2OptionsApp(String deviceName, String uri, String automationName) {
        return new UiAutomator2Options()
                .setAvdLaunchTimeout(ofSeconds(300))
                .setAvdReadyTimeout(ofSeconds(100))
                .setUdid("emulator-5554")
                .setDeviceName(deviceName) // Device name
                .setAutomationName(automationName) // Automation engine name
                .setApp(System.getProperty("user.dir") + uri) // App path
                .setAutoGrantPermissions(true) // Automatically grant permissions
                .setFullReset(true);
    }

    public boolean determineTestType() {
        Properties properties = null;
        try {
            properties = CommonUtils.read_properties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Boolean.parseBoolean(properties.getProperty("isMobileWebTest", "false"));
    }


}