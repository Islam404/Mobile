package pages.SamplePage;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;

/**
 * iOS's definition for the abstract class
 */
public class SampleLogicIOS extends SampleAbstract {

    public static By SAMPLE_ELEMENT = By.id("ios");

    @Override
    public void sampleClickOnSampleElement(AppiumMobileDriver appiumMobileDriver) {
        System.out.println("iOS");
    }

    @Override
    public void login() {
        System.out.println("ios");
    }
}
