
package testcases.android;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import screens.androidpageobjects.NavigationMenuAndroid;
import screens.androidpageobjects.WDIOHomePageAndroid;
import screens.androidpageobjects.WDIOSwipeAndroid;

public class LoginTest extends TestBase {
    public NavigationMenuAndroid NavMenu = new NavigationMenuAndroid();
    public WDIOHomePageAndroid HomePage = new WDIOHomePageAndroid();
    public WDIOSwipeAndroid SwipePage = new WDIOSwipeAndroid();

    @Test(priority = 1, enabled = true)
    @Issue("APP-002")
    @Link("TMS-101")
    @Description("Verify Navigation menu")
    public void verifyHomePage() throws Exception {

        System.out.println("verify Navigation Menu: " + driver);
        NavMenu.tapHomeButton();
        HomePage.verifyTitleHome();
        HomePage.verifyTitleHomeText();

        NavMenu.tapWebViewBtn();
        NavMenu.tapLoginBtn();
        NavMenu.tapFormsBtn();
        NavMenu.tapDragBtn();
    }

    @Test(priority = 2, enabled = true)
    @Issue("APP-002")
    @Description("Verify the swipe action in horizontal and vertical")
    public void verifySwipePage() throws Exception {

        NavMenu.tapSwipeBtn();
        SwipePage.swipeHorizontal();
        SwipePage.verifySwipeHorizontalPage();
        SwipePage.swipesVerticalUp();
        SwipePage.verifySwipeVerticalPage();
    }
}