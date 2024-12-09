package base;
import java.net.MalformedURLException;

import org.apache.logging.log4j.core.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.AppiumDriver;
import screens.androidpageobjects.LandingPageAndroid;
import utils.CommonUtils;
import utils.AppConfigTags;
import utils.Constants;


public class TestBase {

    public static AppiumDriver driver;
    public LandingPageAndroid LandingPage;

    @BeforeSuite
    public void setUp() throws MalformedURLException {

        System.out.println("Setup step");
        CommonUtils utils = new CommonUtils();
        utils.setup(AppConfigTags.ANDROID, AppConfigTags.DEVICE_NAME, Constants.ANDROID_URI, AppConfigTags.AUTOMATION_NAME);
        driver = utils.AndroidDriver;

    }

	@AfterSuite
	public void tearDown() {

        System.out.println("TearDown step");
		driver.quit();

	}

}
