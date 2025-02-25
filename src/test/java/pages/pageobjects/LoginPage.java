package pages.pageobjects;

import base.ScreenBase;
import base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

public class LoginPage extends TestBase {

    String USERNAME = "";
    String PASSWORD = "";

    public LoginPage() {
        try {
            Properties properties = CommonUtils.read_properties();
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    By username = By.xpath("//input[@id='user-name']");
    By password = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//input[@id='login-button']");
    By errorMessage = By.xpath("");
    By header2 = By.xpath("//div[.='Swag Labs']");

    public void tapLogin(){
        System.out.println("tap LognIn On LandingPage: " + webDriver);
        webDriver.findElement(loginBtn).click();
    }

    @Step("Go to URL")
    public void goTo() {
        System.out.println("goto URL:");
        webDriver.navigate().to("https://www.saucedemo.com/");
    }

    @Step("Enter username")
    public void enterUsername() {
        webDriver.findElement(username).sendKeys(USERNAME);
    }

    @Step("Enter password")
    public void enterPassword() {
        webDriver.findElement(password).sendKeys(PASSWORD);
    }

    @Step("Verify login page")
    public void verifyLoginPage() {
        System.out.println("Verify landing page");
        Assert.assertTrue(webDriver.findElement(header2).isDisplayed(), "Header is not displayed");
    }
}
