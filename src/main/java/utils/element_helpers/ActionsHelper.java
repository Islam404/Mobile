package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.drivers_initializer.drivers.BaseAndroidDriver;
import core.drivers_initializer.drivers.BaseIOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static utils.element_helpers.VerifyHelper.isElementDisplay;

public abstract class ActionsHelper {

    public static boolean isClickable(AppiumMobileDriver appiumMobileDriver, By locator, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumMobileDriver.getDriver(), Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(appiumMobileDriver.getDriver().findElement(locator)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clickWhileVisible(AppiumMobileDriver appiumMobileDriver, By locator) {
        boolean displayed = true;
        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isElementDisplay(appiumMobileDriver, locator)) {
                displayed = true;
                appiumMobileDriver.getDriver().findElement(locator).click();
            } else {
                displayed = false;
                break;
            }
        } while (displayed);
    }

    public static void moveToElementAndClick(AppiumMobileDriver appiumMobileDriver, By locator) {
        try {
            Actions actions = new Actions(appiumMobileDriver.getDriver());
            actions.moveToElement(appiumMobileDriver.getDriver().findElement(locator)).click().perform();
        } catch (Exception e) {
            Logger.info("can't perform move and click element action" + e);
            throw new RuntimeException(e);
        }
    }

    public static void enter(AppiumMobileDriver driver) {
        Actions actions = new Actions(driver.getDriver());
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public static void hideKeyboard(AppiumMobileDriver driver) {
        if (driver instanceof BaseAndroidDriver) {
            driver.getAndroidDriver().hideKeyboard();
        } else {
            if (driver instanceof BaseIOSDriver) {
                driver.getDriver().findElement(By.name("Done")).click();
            }
        }
    }


    public static void doTap(AppiumMobileDriver driver, Point point, int duration) {
        PointerInput FINGER = new PointerInput(TOUCH, "finger");
        Sequence tap = new Sequence(FINGER, 1).addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY())).addAction(FINGER.createPointerDown(LEFT.asArg())).addAction(new Pause(FINGER, ofMillis(duration))).addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.getDriver().perform(Arrays.asList(tap));
    }

    public static String getAttributeValue(AppiumMobileDriver appiumMobileDriver, String attribute, By locator) {
        String value = appiumMobileDriver.getDriver().findElement(locator).getAttribute(attribute);
        return value;
    }

    public void setContext(AppiumMobileDriver driver, ContextEnum contextEnum) {

        if (driver instanceof BaseAndroidDriver) {
            if (contextEnum.equals(ContextEnum.NATIVE_APP)) {
                driver.getAndroidDriver().context(contextEnum.toString());
            } else {
                Set<String> contextNames = driver.getAndroidDriver().getContextHandles();
                driver.getAndroidDriver().context((String) contextNames.toArray()[1]);
            }
        } else {
            if (driver instanceof BaseIOSDriver) {
                if (contextEnum.equals(ContextEnum.NATIVE_APP)) {
                    driver.getIosDriver().context(contextEnum.toString());
                } else {
                    Set<String> contextNames = driver.getIosDriver().getContextHandles();
                    driver.getIosDriver().context((String) contextNames.toArray()[1]);
                }
            }
        }

    }

    private enum ContextEnum {
        NATIVE_APP("NATIVE_APP"), WEBVIEW("WEBVIEW");
        private final String contextEnum;

        ContextEnum(String context) {
            this.contextEnum = context;
        }
    }
}
