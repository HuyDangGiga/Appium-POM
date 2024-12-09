package screens.androidpageobjects;

import base.ScreenBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

public class TouchInteractionPageAndroid extends ScreenBase {

    By singleTapButton =By.id("com.example.touchinteractions:id/singleTap");
    By doubleTapButton = By.id("com.example.touchinteractions:id/doubleTap");
    By tapStatus = By.id("com.example.touchinteractions:id/textView");
    By dragDropStatus = By.id("com.example.touchinteractions:id/statusText");
    By dragDropButton = By.id("com.example.touchinteractions:id/dragDropButton");
    By scrollButton = By.id("com.example.touchinteractions:id/scrollButton");
    By sourceDrag = By.id("com.example.touchinteractions:id/moveText");
    By targetDrop = By.id("com.example.touchinteractions:id/target");
    By lastElement = By.id("com.example.touchinteractions:id/textView10");

    public static String actualValue ;
    public static String expectedValue ;

    public void performSingleTap() {

        WebElement element = driver.findElement(singleTapButton);
        Point sourceLocation = element.getLocation();
        Dimension sourceSize = element.getSize();
        int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
        int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }

    public void performDoubleTap() {
        WebElement element = driver.findElement(doubleTapButton);
        Point sourceLocation = element.getLocation();
        Dimension sourceSize = element.getSize();
        int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
        int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence doubleTap = new Sequence(finger, 0);
        doubleTap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerX, centerY));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(50)));
        doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        doubleTap.addAction(new Pause(finger, Duration.ofMillis(100)));
        doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(doubleTap));
    }

    public void performDragAndDrop() {
        WebElement source = driver.findElement(sourceDrag);
        WebElement target = driver.findElement(targetDrop);
        DragAndDrop(source, target, 30);
    }

    public void performDragDownAndDrop() {
        WebElement source = driver.findElement(sourceDrag);
        DragDownAndDrop(source, 390);
    }

    public void performScrollToElement() {
            performScroll(lastElement);
    }

    public String getInteractionText() {
        return driver.findElement(tapStatus).getText();
    }

    public String getDragDropText() {
        return driver.findElement(dragDropStatus).getText();
    }

    public void openDragDropScreen() {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(dragDropButton)).perform();
    }

    public void openScrollScreen() {
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(scrollButton)).perform();
    }
    public void BackLandingScreen() {
        driver.navigate().back();
    }

    public void VerifyLastListElementDisplayed(boolean expected) throws InterruptedException {
        try {
            if(expected)
                Assert.assertTrue(driver.findElement(lastElement).isDisplayed(), "Element is not displayed");
            else
                Assert.assertFalse(driver.findElement(lastElement).isDisplayed(),"Element is displayed");
        } catch (NoSuchElementException ignored) {}
    }

    @Step("Verify text")
    public void verifyDefaultText() {
        Assert.assertEquals(getInteractionText(), "Last Interaction");
    }

    @Step("Verify single tap notification")
    public void verifySingleTapInteraction() {
        Assert.assertEquals(getInteractionText(), "Single Tap Success!");
    }

    @Step("Verify double tap notification")
    public void verifyDoubleTapInteraction() {
        Assert.assertEquals(getInteractionText(), "Button double-tapped!");
    }

    @Step("Verify drag and drop open")
    public void verifyDragDropOpen() {
        Assert.assertEquals(getDragDropText(), "Move the text into the white box!");
    }

    @Step("Verify drag and drop notification")
    public void verifyDragDropSuccessfully() {
        Assert.assertEquals(getDragDropText(), "Move successful!");
    }

}