package stepdefs;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.drivers_initializer.instance.MobileDriverInstance;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Hooks {

    public static AppiumMobileDriver appiumMobileDriver;


    public void embedScreenshot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) appiumMobileDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "embedScreenShot");
        } catch (WebDriverException | NullPointerException e) {
            System.out.println("Failed to take embed Screenshot");
        }
    }

    /**
     * Run once and then run again if the driver is null only
     */
    @Before(order = 1)
    public void beforeAll() {
        appiumMobileDriver = MobileDriverInstance.getAppiumMobileDriver();
        appiumMobileDriver.setup();
    }

    /**
     * Run after every scenario
     */
    @After
    public void afterAll(Scenario scenario) {
        embedScreenshot(scenario);
        appiumMobileDriver.getDriver().quit();
        appiumMobileDriver.removeDriver();
    }
}
