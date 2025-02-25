package screens.androidpageobjects;

import base.ScreenBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WDIOSwipeAndroid extends ScreenBase {
    By carl_item_4 = By.xpath("//android.widget.TextView[@text='EXTENDABLE']");
    By WDIO_logo = By.xpath("//android.widget.ImageView[@content-desc='WebdriverIO logo']");
    By WDIO_txt = By.xpath("//android.widget.TextView[@text='You found me!!!']");


    @Step("Get found me text")
    public String getFoundMeText() {
        return driver.findElement(WDIO_txt).getText();
    }

    @Step("Verify ending of swiping vertical")
    public void verifySwipeVerticalPage() {
        System.out.println("verify display of end page");
        Assert.assertEquals(getFoundMeText(), "You found me!!!");
    }

    @Step("Verify ending of swiping horizontal")
    public void verifySwipeHorizontalPage() {
        System.out.println("verify display of end horizontal ");
        Assert.assertTrue(driver.findElement(carl_item_4).isDisplayed(), "Element is not displayed");
    }


    @Step("Step swiping horizontal")
    public void swipeHorizontal() {
        //Swipe left to right, X - X smaller, Y unchanged
        //base on the element0 of the carousel
        // Define start and end points for the swipe
        performScrollHorizontal(carl_item_4);
    }

    @Step("Step swiping vertical")
    public void swipeVertical() {
        // using to scroll until see the element
        performScroll(WDIO_logo);
    }

    @Step("Steps swiping vertical multiple time")
    public void swipesVerticalUp() {
        // Define start and end points for the swipe
        System.out.println("Start swiping horizontal");

        performScroll(4,4, 2);
        performScroll(4,6, 2);
        performScroll(4,6, 2);
    }

    public void performSingleTap(WebElement element) {
        //perform single tap action

    }


}