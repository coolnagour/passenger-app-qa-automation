package com.icabbi.driver.app.base;

import com.icabbi.driver.app.data.TestData;
import com.icabbi.driver.app.driver.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest implements TestRule {
    protected IicabbiDriver driver;
    public TestName testName = new TestName();

    @BeforeMethod
    public void setup(Method method) {
        try {
            driver = getDriver(new DriverInitialization() {
                   @Override
                   public <D extends AppiumDriver<E>, E extends MobileElement> void onBeforeInitialization(IcabbiDriverBase<D, E> driver) {
                   }
               }
            );

            System.out.println("Scenario: " + method.getName());
            driver.implicitWait();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void dispose(Method method) throws IOException {
        System.out.println("PAGE XML::: "+ driver.currentPageSourceXML());
        String testFinishTimestamp = String.valueOf(TestData.epochTime());
        driver.takeScreenshot(testFinishTimestamp + method.getName());
        driver.quit();
    }

    protected static IicabbiDriver getDriver(DriverInitialization initializationCallback) throws MalformedURLException {
        return getDriver("en", "GB", initializationCallback);
    }

    protected static IicabbiDriver getDriver(String language, String country, DriverInitialization initializationCallback) throws MalformedURLException {
        String platform = System.getProperty("platform");
        System.out.println("\nPlatform: " + platform + " - " + language + "/" + country);

        if ("ios".equalsIgnoreCase(platform)) {
            return new IcabbiIOSDriver(language, country, initializationCallback);
        } else {
            return new IcabbiAndroidDriver(language, country, initializationCallback);
        }
    }

    public static void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public static void printScenario(String scenario) {
        System.out.println("SCENARIO: " + scenario);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        System.out.println("Scenario: " + testName.getMethodName());
        return null;
    }
}