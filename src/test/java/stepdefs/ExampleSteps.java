package stepdefs;

import core.drivers_initializer.drivers.BaseAndroidDriver;
import core.drivers_initializer.drivers.BaseIOSDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.SamplePage.SampleAbstract;
import pages.SamplePage.SampleLogicAndroid;
import pages.SamplePage.SampleLogicIOS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static stepdefs.Hooks.appiumMobileDriver;


public class ExampleSteps {

    private SampleAbstract page;


    /**
     * Constructor for the step definition to initialize the pages needed android or ios based on the config platform
     */
    public ExampleSteps() {
        if (appiumMobileDriver instanceof BaseAndroidDriver) {
            page = new SampleLogicAndroid();
        } else if (appiumMobileDriver instanceof BaseIOSDriver) {
            page = new SampleLogicIOS();
        }
    }

    @Given("The user click on example button")
    public void theUserClickOnExampleButton() {
        page.login();
        page.ali();
    }

    @Then("Event field should be {string}")
    public void eventExampleShouldBeRecorded(String expectedResult) {
        assertEquals(expectedResult , "eventActual");
    }
}
