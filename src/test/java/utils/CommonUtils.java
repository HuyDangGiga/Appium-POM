package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

public class CommonUtils {

    // Declare AppiumDriver and AndroidDriver
    public AppiumDriver AppiumDriver;
    public AndroidDriver AndroidDriver;


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
        AndroidDriver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

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
    private static UiAutomator2Options uiAutomator2OptionsChrome(String deviceName, String automationName) {
        return new UiAutomator2Options()
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName(deviceName)
                .setAutomationName(automationName)
                .withBrowserName("chrome")
                .setAutoGrantPermissions(true)
                .setNoReset(false);
    }

    // Creates UiAutomator2Options for app testing
    private static UiAutomator2Options uiAutomator2OptionsApp(String deviceName, String uri, String automationName) {
        return new UiAutomator2Options()
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName(deviceName) // Device name
                .setAutomationName(automationName) // Automation engine name
                .setApp(System.getProperty("user.dir") + uri) // App path
                .setAutoGrantPermissions(true) // Automatically grant permissions
                .setFullReset(true);
    }
}