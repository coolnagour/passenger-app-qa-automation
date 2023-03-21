package com.icabbi.driver.app.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public interface DriverInitialization {
    <D extends AppiumDriver<E>, E extends MobileElement> void onBeforeInitialization(IcabbiDriverBase<D, E> driver);
}
