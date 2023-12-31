package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.Arrays;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static utils.element_helpers.VerifyHelper.isElementDisplay;

public abstract class ScrollHelper {
    public static void scrollDownFromElement(AppiumMobileDriver appiumMobileDriver, By locator) {
        Point location = appiumMobileDriver.getDriver().findElement(locator).getLocation();
        preformSwipe(appiumMobileDriver, new Point(location.x, location.y), new Point(location.x, (location.y - 150)), 1);
    }

    public static void preformSwipe(AppiumMobileDriver driver, Point start, Point end, int duration) {
        PointerInput FINGER = new PointerInput(TOUCH, "finger");
        Sequence swipe = new Sequence(FINGER, 1).addAction(FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY())).addAction(FINGER.createPointerDown(LEFT.asArg())).addAction(FINGER.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY())).addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.getDriver().perform(Arrays.asList(swipe));
    }

    public static void touchActionScrolling(AppiumMobileDriver appiumMobileDriver, int width, int startY, int endY) {
        preformSwipe(appiumMobileDriver, new Point(width, startY), new Point(width, endY), 1);
    }

    public static void scrollTo(AppiumMobileDriver appiumMobileDriver, String direction, int times) {
        if (direction.equals("down")) {
            Dimension dimensions = appiumMobileDriver.getDriver().manage().window().getSize();
            int width = dimensions.getWidth() / 2;

            for (int i = 0; i < times; i++) {
                int startY = (int) (dimensions.getHeight() * 0.7);
                int endY = (int) (dimensions.getHeight() * 0.5);
                touchActionScrolling(appiumMobileDriver, width, startY, endY);

            }
        }
        if (direction.equals("up")) {
            Dimension dim = appiumMobileDriver.getDriver().manage().window().getSize();
            int width = dim.getWidth() / 2;
            for (int i = 0; i < times; i++) {
                int startY = (int) (dim.getHeight() * 0.3);
                int endY = (int) (dim.getHeight() * 0.7);
                touchActionScrolling(appiumMobileDriver, width, startY - 25, endY + 100);
            }
        }
        if (direction.equals("left")) {
            Dimension dim = appiumMobileDriver.getDriver().manage().window().getSize();
            int height = dim.getWidth() / 2;
            for (int i = 0; i < times; i++) {
                int startX = dim.getWidth() / 2;
                int endX = dim.getWidth() / 3;
                preformSwipe(appiumMobileDriver, new Point(startX, height), new Point(endX, height), 1);
            }
        }

        if (direction.equals("right")) {
            Dimension dim = appiumMobileDriver.getDriver().manage().window().getSize();
            int height = dim.getWidth() / 2;
            for (int i = 0; i < times; i++) {
                int startX = dim.getWidth() / 4;
                int endX = dim.getWidth() / 2;
                preformSwipe(appiumMobileDriver, new Point(startX, height), new Point(endX, height), 1);
            }
        }
    }

    public static void scrollFromElement(AppiumMobileDriver appiumMobileDriver, By locator, String direction) {
        Point location = appiumMobileDriver.getDriver().findElement(locator).getLocation();
        if (direction == "down") {
            preformSwipe(appiumMobileDriver, location, new Point(location.x, (location.y - 150)), 1);
        }
        if (direction == "up") {
            preformSwipe(appiumMobileDriver, location, new Point(location.x, (location.y + 150)), 1);
        }
        if (direction == "left") {
            preformSwipe(appiumMobileDriver, location, new Point(location.x - 100, (location.y)), 1);
        }
        if (direction == "right") {
            preformSwipe(appiumMobileDriver, location, new Point(location.x + 100, (location.y)), 1);
        }
    }

    public static boolean scrollToElement(AppiumMobileDriver appiumMobileDriver, By locator, int times) {
        boolean flag = false;
        int i = 0;
        do {
            if (isElementDisplay(appiumMobileDriver, locator)) {
                flag = true;
                break;
            } else {
                if (i < times) {
                    scrollTo(appiumMobileDriver, "down", 2);
                    i++;
                } else {
                    break;
                }
            }
        } while (!flag);
        return flag;
    }
}
