package core.drivers_initializer.enums;

import core.drivers_initializer.drivers.AppiumMobileDriver;
import core.drivers_initializer.drivers.BaseAndroidDriver;
import core.drivers_initializer.drivers.BaseIOSDriver;
import core.interfaces.AppiumMobileDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public enum AppiumMobilePlatforms implements AppiumMobileDriverProvider<AppiumMobileDriver> {
    IOS_DRIVER() {
        @Override
        public AppiumMobileDriver<IOSDriver> getAppiumMobileDriver() {
            return new BaseIOSDriver();
        }
    },
    ANDROID_DRIVER() {
        @Override
        public AppiumMobileDriver<AndroidDriver> getAppiumMobileDriver() {
            return new BaseAndroidDriver();
        }
    }
}
