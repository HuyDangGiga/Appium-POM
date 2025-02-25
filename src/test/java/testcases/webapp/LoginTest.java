
package testcases.webapp;

import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import pages.pageobjects.LoginPage;
import pages.pageobjects.ProductPage;

public class LoginTest extends TestBase {
    public LoginPage login = new LoginPage();
    public ProductPage product = new ProductPage();

    @Test(priority = 1, enabled = true)
    @Issue("APP-003")
    @Link("TMS-201")
    @Description("Verify login page")
    public void verifyLoginPage() throws Exception {
        System.out.println("verify Navigation Menu: " + webDriver);
        login.goTo();
        login.verifyLoginPage();
        login.enterUsername();
        login.enterPassword();
        login.tapLogin();
        product.verifyProductPage();
    }
}