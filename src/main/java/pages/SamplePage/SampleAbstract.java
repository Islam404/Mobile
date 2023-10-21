package pages.SamplePage;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import pages.CommonPO;

/**
 * Sample abstract class for every function of the page extending the Page Object class for the page
 */
public abstract class SampleAbstract extends CommonPO {


    public abstract void sampleClickOnSampleElement(AppiumMobileDriver appiumMobileDriver);

    public abstract void login();

    public void ali(){

    }
}
