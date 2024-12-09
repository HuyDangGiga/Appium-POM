package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ScreenBase extends TestBase {

    public ScreenBase() {

    }

    public void hideKeyboard() {

    }

    public static boolean waitForPresence(WebElement element){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  element.isDisplayed();
        }
    }

    public void GetLocationValueOfElement(WebElement element) {
        int offset = 30;
        int leftX = element.getLocation().getX();
        System.out.println("starting location leftX: " + leftX);

        int rightX =  element.getSize().getWidth(); //max element width
        System.out.println("starting location rightX: " + rightX);

        int middleX = (rightX + leftX) / 2;
        System.out.println("starting location middleX: " + middleX);

        int upperY = element.getLocation().getY();
        System.out.println("starting location upperY: " + upperY);

        int lowerY =  element.getSize().getHeight() + upperY;//max element height
        System.out.println("starting location lowerY: " + lowerY);

        int middleY = (upperY + lowerY) / 2;
        System.out.println("starting location middleY: " + middleY);

        System.out.println("Element location top: " + leftX + "-"+upperY);
        System.out.println("Start swiping right to left:");
        System.out.println("Start swiping location is: " + (rightX-offset) + "-"+(middleY));
        System.out.println("End swiping location is: " + (middleX-offset) + "-"+middleY);


    }

    public int GetStartXOfElement(WebElement element) {
        //        System.out.println("starting location leftX: " + leftX);
        return element.getLocation().getX();
    }

    public int GetStartYOfElement(WebElement element) {
        //        System.out.println("starting location upperY: " + upperY);
        return element.getLocation().getY();
    }

    public int GetEndXOfElement(WebElement element) {
        //        System.out.println("starting location rightX: " + rightX);
        return element.getSize().getWidth();
    }

    public int GetEndYOfElement(WebElement element) {
        //        System.out.println("starting location upperY: " + EndY);
        return element.getSize().getHeight() + GetStartYOfElement(element);
    }

    public int GetMidXofElement(WebElement element) {
        int leftX = GetStartXOfElement(element);
        int rightX =  element.getSize().getWidth(); //max element width
        //        System.out.println("starting location middleX: " + middleX);
        return (rightX + leftX) / 2;
    }

    public int GetMidYofElement(WebElement element) {
        int upperY = element.getLocation().getY();
        int lowerY =  element.getSize().getHeight() + upperY;//max element height
        //        System.out.println("starting location middleY: " + middleY);
        return (upperY + lowerY) / 2;
    }

    public void SwipeRightToLeftFromElement(WebElement element) {
        int offset = 30;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 4);
        scroll.addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (GetEndXOfElement(element)-offset), GetMidYofElement(element)));
        System.out.println("starting location: " + (GetEndXOfElement(element)-offset) + " - " + GetMidYofElement(element));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), (GetEndXOfElement(element)-offset), GetMidYofElement(element)));
        System.out.println("ending location: " + (GetMidXofElement(element)-offset) + " - " + GetMidYofElement(element));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // Perform the swipe action
        driver.perform(List.of(scroll));
    }

    public void DragAndDrop(WebElement source, WebElement target, int offset){
        Point sourceLocation = source.getLocation();
        Dimension sourceSize = source.getSize();
        int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
        int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

        Point targetLocation = target.getLocation();
        Dimension targetSize = target.getSize();
        int centerX2 = targetLocation.getX() + targetSize.getWidth() / 2;
        int centerY2 = targetLocation.getY() + targetSize.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        System.out.println("Move from:" + centerX + "-" + centerY);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerX, centerY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX2, centerY2 + offset));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(dragNDrop));
        System.out.println("Move to:" + centerX2 + "-" + centerY2 + offset);
    }

    public void DragDownAndDrop(WebElement source, int offset){
        Point sourceLocation = source.getLocation();
        Dimension sourceSize = source.getSize();
        int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
        int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        System.out.println("Move from:" + centerX + "-" + centerY);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerX, centerY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, offset));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(dragNDrop));
        System.out.println("Move to:" + centerX + "-" + offset);
    }

    protected void performScroll(By by) {
        boolean isElementDisplayed = false;
        while (!isElementDisplayed) {
            try {
                isElementDisplayed = driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException e) {
                System.out.println("Element is not displayed!");
            }
            if (!isElementDisplayed) {
                int startX = driver.manage().window().getSize().getWidth() / 2;
                int startY = driver.manage().window().getSize().getHeight() / 2;
                int endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
                System.out.println("Start scrolling from:" + startX + "-" + startY);
                performScroll(startX, startY, endY);
                System.out.println("End scrolling to:" + startX + "-" + endY);
            }
        }
    }

    protected void performScroll(int sX,int sY,int eY)  {
        if(sX< 9 || sY < 9 || eY <9) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            int startX = driver.manage().window().getSize().getWidth() * sX / 8;// 4/8;
            int startY = driver.manage().window().getSize().getHeight() * sY / 8;// 3/8;
            int endY = driver.manage().window().getSize().getHeight() * eY / 8;  // 6/8;
            Sequence scroll = new Sequence(finger, 0);
            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(scroll));
        } else {
            System.out.println("XY Coordinates are not invalid! must lower than 8 ");
        }
    }

    protected void performScrollHorizontal(By targetLookup) {
        boolean isElementDisplayed = false;
        while (!isElementDisplayed) {
            try {
                isElementDisplayed = driver.findElement(targetLookup).isDisplayed();
            } catch (NoSuchElementException e) {
                System.out.println("Element is not displayed!");
            }
            if (!isElementDisplayed) {
                int startX = driver.manage().window().getSize().getWidth() * 6/8;
                int endX = driver.manage().window().getSize().getWidth() * 3/8;
                int startY = driver.manage().window().getSize().getHeight() * 5/8;
                System.out.println("Start scrolling from:" + startX + "-" + startY);
                performScrollHorizontal(startX,startY,endX);
                System.out.println("End scrolling to:" + endX + "-" + startY);
            }
        }
    }

    protected void performScrollHorizontal(int startX, int startY, int endX) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, startY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }
}
