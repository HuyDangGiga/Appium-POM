package screens.androidpageobjects;

import base.ScreenBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import io.qameta.allure.Step;

public class WDIOHomePageAndroid extends ScreenBase {
    By title_home = By.xpath("//android.widget.TextView[@text='WEBDRIVER']");

    @Step("verify title home")
    public void verifyTitleHome() {
        System.out.println("verify display of title");
        Assert.assertTrue(driver.findElement(title_home).isDisplayed(),"The element is not displayed");
    }

    @Step("verify title text")
    public void verifyTitleHomeText() {
        System.out.println("verify text of title ");
        Assert.assertEquals(driver.findElement(title_home).getText(),"WEBDRIVER","The element is not displayed");
    }

}