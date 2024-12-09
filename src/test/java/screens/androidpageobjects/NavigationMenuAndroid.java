package screens.androidpageobjects;

import base.ScreenBase;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

public class NavigationMenuAndroid extends ScreenBase {

    By HomeBtn = By.xpath("//android.view.View[@content-desc='Home']");
    By WebViewBtn = By.xpath("//android.view.View[@content-desc='Webview']");
    By LoginBtn = By.xpath("//android.view.View[@content-desc='Login']");
    By FormsBtn = By.xpath("//android.view.View[@content-desc='Forms']");
    By SwipeBtn = By.xpath("//android.view.View[@content-desc='Swipe']");
    By DragBtn = By.xpath("//android.view.View[@content-desc='Drag']");

    public void tapHomeButton() {
        System.out.println("tapHomeButton: " + driver);
        driver.findElement(HomeBtn).click();
    }

    @Step("Step tap webview button")
    public void tapWebViewBtn() { driver.findElement(WebViewBtn).click(); }

    @Step("Step tap login button")
    public void tapLoginBtn() { driver.findElement(LoginBtn).click(); }

    @Step("Step tap form button")
    public void tapFormsBtn() {
        driver.findElement(FormsBtn).click();
    }

    @Step("Step tap swipe button")
    public void tapSwipeBtn() {
        driver.findElement(SwipeBtn).click();
    }

    @Step("Step tap drag button")
    public void tapDragBtn() {
        driver.findElement(DragBtn).click();
    }

    @Step("Verify homepage")
    public void verifyHomePage() {
        System.out.println("verifyHomePage: ");
    }

}
