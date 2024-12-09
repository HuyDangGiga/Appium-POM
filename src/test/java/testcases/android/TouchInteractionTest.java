
package testcases.android;

import base.ScreenBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import screens.androidpageobjects.*;

public class TouchInteractionTest extends ScreenBase {
    public TouchInteractionPageAndroid TouchPage = new TouchInteractionPageAndroid();


    @Test(priority = 1, enabled = false)
    @Issue("APP-001")
    @Link("TMS-001")
    @Description("Verify Touch Interaction App - Single Tap")
    public void verifyTouchInteraction() throws Exception {
        TouchPage.verifyDefaultText();
        TouchPage.performSingleTap();
        TouchPage.verifySingleTapInteraction();
    }

    @Test(priority = 2, enabled = false)
    @Description("Verify Touch Interaction App - Double Tap")
    public void verifyDoubleTapInteraction() throws Exception {
        TouchPage.performDoubleTap();
        TouchPage.verifyDoubleTapInteraction();
    }

    @Test(priority = 3, enabled = false)
    @Description("Verify Touch Interaction App - Drag Drop")
    public void verifyDragDropInteraction() throws Exception {
        TouchPage.openDragDropScreen();
        TouchPage.verifyDragDropOpen();
        TouchPage.performDragDownAndDrop();
        TouchPage.verifyDragDropSuccessfully();
        TouchPage.BackLandingScreen();
    }

    @Test(priority = 4, enabled = false)
    @Description("Verify Touch Interaction App - Scroll")
    public void verifyScrollInteraction() throws Exception {
        TouchPage.openScrollScreen();
        TouchPage.VerifyLastListElementDisplayed(false);
        TouchPage.performScrollToElement();
        TouchPage.VerifyLastListElementDisplayed(true);
    }

}