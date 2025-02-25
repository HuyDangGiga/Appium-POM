package base;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.AppiumDriver;
import utils.CommonUtils;
import utils.AppConfigTags;
import utils.Constants;
import utils.LogUtils;
import com.github.automatedowl.tools.AllureEnvironmentWriter;

import static java.time.Duration.ofSeconds;


public class TestBase {

    public static AppiumDriver driver;
    public static WebDriver webDriver;
    protected static boolean isMobileWebTest;
    public AppiumDriverLocalService service;

    @BeforeSuite
    public void setUp() throws MalformedURLException {

        System.out.println("Setup step");
        beforeAll();

        CommonUtils utils = new CommonUtils();
        isMobileWebTest = utils.determineTestType();
        if (isMobileWebTest) { // Determine if it's a mobile web test or not
//            utils.setupMobileWeb(AppConfigTags.ANDROID, AppConfigTags.DEVICE_NAME,Constants.ANDROID_URI, AppConfigTags.BROWSER_NAME, AppConfigTags.AUTOMATION_NAME);
//            service = AppiumDriverLocalService.buildDefaultService();
//            service.start();
            UiAutomator2Options options = new UiAutomator2Options()
                    .setAvdLaunchTimeout(ofSeconds(300))
                    .setAvdReadyTimeout(ofSeconds(100))
                    .withBrowserName(MobileBrowserType.CHROME)
                    .setUdid("emulator-5554")
                    .setAutomationName(AppConfigTags.AUTOMATION_NAME) ;
//            webDriver = new AndroidDriver(service.getUrl(), options);
            webDriver = new AndroidDriver(new URL(Constants.REMOTE_URL+":"+Constants.REMOTE_PORT), options);
            //This time out is set because test can be run on slow Android SDK emulator
            PageFactory.initElements(new AppiumFieldDecorator(webDriver, ofSeconds(5)), this);
//            webDriver = utils.webDriver;
        } else {
            utils.setup(AppConfigTags.ANDROID, AppConfigTags.DEVICE_NAME, Constants.ANDROID_URI, AppConfigTags.AUTOMATION_NAME);
            driver = utils.androidDriver;
        }


    }

    public static void beforeAll() {
        LogUtils.info("================ BEFORE ALL ================");
        setAllureEnvironmentInformation();

        try {
             {
                FileUtils.deleteDirectory(new File("target/allure-results"));
                LogUtils.info("Deleted directory target/allure-results");
                FileUtils.deleteDirectory(new File("ExportData"));
                LogUtils.info("Deleted directory ExportData");
            }
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", Constants.URL).
                        put("Target Execution", Constants.TARGET).
                        put("Global Timeout", String.valueOf(Constants.WAIT_DEFAULT)).
                        put("Page Load Timeout", String.valueOf(Constants.WAIT_PAGE_LOADED)).
                        put("Remote URL", Constants.REMOTE_URL).
                        put("Remote Port", Constants.REMOTE_PORT).
                        build());

        System.out.println("Allure Reports is installed.");
    }

    @AfterSuite
	public void tearDown() {

        System.out.println("TearDown step");
        if (driver != null) {
            driver.quit();
        }
        if (webDriver != null) {
            webDriver.quit();
        }
	}
}
