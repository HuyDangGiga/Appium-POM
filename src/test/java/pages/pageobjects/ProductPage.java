package pages.pageobjects;

import base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

public class ProductPage extends TestBase {

    public ProductPage() {

    }

    By header2 = By.xpath("///span[.='Products']");

    @Step("Verify product page")
    public void verifyProductPage() {
        System.out.println("Verify product page");
        Assert.assertTrue(webDriver.findElement(header2).isDisplayed(), "Header is not displayed");
    }
}
