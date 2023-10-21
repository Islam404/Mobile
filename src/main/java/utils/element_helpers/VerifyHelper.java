package utils.element_helpers;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;

public abstract class VerifyHelper {

    public static void verifyElementNotExist(AppiumMobileDriver appiumMobileDriver, By locator) throws Exception {
        if (!appiumMobileDriver.getDriver().findElement(locator).isDisplayed()) {
            throw new Exception(appiumMobileDriver.getDriver().findElement(locator) + " (appiumMobileDriver.getDriver().findElement(locator) is present)");
        }
    }

    public static void verifyElementListIsEmpty(AppiumMobileDriver appiumMobileDriver, By elements) throws Exception {
        if (!appiumMobileDriver.getDriver().findElements(elements).isEmpty()) {
            throw new Exception(appiumMobileDriver.getDriver().findElement(elements) + " (Element is present)");
        }
    }


    public static boolean isElementDisplay(AppiumMobileDriver appiumMobileDriver, By locator) {
        try {
            if (appiumMobileDriver.getDriver().findElement(locator).isDisplayed()) {
                return true;
            }
        } catch (Exception exception) {
            return false;
        }
        return false;
    }
}