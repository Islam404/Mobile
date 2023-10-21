package pages.SamplePage;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import org.openqa.selenium.By;

import static utils.element_helpers.WaitHelper.waitVisibility;

/**
 * Android's definition for the abstract class
 */
public class SampleLogicAndroid extends SampleAbstract {

    public static By SAMPLE_ELEMENT = By.id("android");

    @Override
    public void sampleClickOnSampleElement(AppiumMobileDriver appiumMobileDriver) {
        waitVisibility(appiumMobileDriver, SAMPLE_ELEMENT);
        System.out.println("Android");
    }

    @Override
    public void login() {
        System.out.println("Android");
    }
}
