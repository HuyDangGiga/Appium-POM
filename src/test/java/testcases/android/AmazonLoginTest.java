
package testcases.android;

import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import screens.androidpageobjects.NavigationMenuAndroid;
import screens.androidpageobjects.WDIOHomePageAndroid;
import screens.androidpageobjects.WDIOSwipeAndroid;

public class AmazonLoginTest extends TestBase {
    public NavigationMenuAndroid NavMenu = new NavigationMenuAndroid();
    public WDIOHomePageAndroid HomePage = new WDIOHomePageAndroid();
    public WDIOSwipeAndroid SwipePage = new WDIOSwipeAndroid();

    @Test(priority = 1, enabled = true)
    @Description("Verify Navigation menu")
    public void verifyHomePage() throws Exception {

        System.out.println("verifyNavigationMenu: " + driver);
        NavMenu.tapHomeButton();
        HomePage.verifyTitleHome();
        HomePage.verifyTitleHomeText();

        NavMenu.tapWebViewBtn();
        NavMenu.tapLoginBtn();
        NavMenu.tapFormsBtn();
        NavMenu.tapDragBtn();
    }

    @Test(priority = 2, enabled = true)
    @Description("Verify the swipe action in horizontal and vertical")
    public void verifySwipePage() throws Exception {

        NavMenu.tapSwipeBtn();
        SwipePage.swipeHorizontal();
        SwipePage.verifySwipeHorizontalPage();
        SwipePage.swipesVerticalUp();
        SwipePage.verifySwipeVerticalPage();
    }
}