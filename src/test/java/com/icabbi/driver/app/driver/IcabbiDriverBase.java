package com.icabbi.driver.app.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public abstract class IcabbiDriverBase<D extends AppiumDriver<E>, E extends MobileElement> implements IicabbiDriver {
    protected D driver;
    static final String IOS_DRIVER = "com.icabbi.driver.app.driver.IcabbiIOSDriver";

    public IcabbiDriverBase(String language, String country, DriverInitialization initializationCallback) throws MalformedURLException {
        AppiumServiceBuilder builder;
        builder = new AppiumServiceBuilder().withArgument(() -> "--base-path", "/wd/hub");
        builder.usingAnyFreePort();

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        String appFileName = System.getProperty("app");
        File app = null;
        if (appFileName != null) {
            app = new File(appFileName);
            if (!app.exists())
                app = null;
        }

        if (app == null) {
            File f = new File("src");
            app = new File(f, getDefaultAppFile());
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        configureCapabilities(capabilities);
        if (language != null && country != null) {
            capabilities.setCapability(MobileCapabilityType.LANGUAGE, language);
            capabilities.setCapability(MobileCapabilityType.LOCALE, country);
        }

        if (initializationCallback != null)
            initializationCallback.onBeforeInitialization(this);

        this.driver = createDriver(service.getUrl(), capabilities);

    }

    protected abstract D createDriver(URL url, DesiredCapabilities capabilities);

    protected abstract String getDefaultAppFile();

    protected abstract void configureCapabilities(DesiredCapabilities capabilities);

    @Override
    public boolean isIOS() {
        String driverClassName = this.getClass().getName();
        return driverClassName.equals(IOS_DRIVER);
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void activateApp() {
        this.driver.activateApp("com.icabbi.driver.app");
    }

    @Override
    public void teminateSettingsApp() {
        this.driver.terminateApp("com.android.settings");
    }

    @Override
    public void closeApp() {
        this.driver.closeApp();
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public void takeScreenshot(String fileName) throws IOException {
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("target/" + fileName + "-screenshot.jpg"));
    }

    @Override
    public String currentPageSourceXML() {
        System.out.println("In currentPageSourceXML");
        System.out.println(this.driver.getPageSource());
        return this.driver.getPageSource();
    }

    @Override
    public MobileElement findElementById(String id) {
        try {
            return this.driver.findElementById(id);
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement findElementByResourceId(String id) {
        try {
            return this.driver.findElement(By.id(id));
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement findElementByMobileId(String id) {
        try {
            return this.driver.findElement(MobileBy.id(id));
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement findElementByText(String id) {
        try {
            return this.
                    driver.findElement(By.xpath("//android.widget.TextView[@text='"+id+"']"));
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement findElementByMobileXPath(String xpath) {
        try {
            return this.driver.findElement(MobileBy.xpath(xpath));
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement waitForElement(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

            return this.driver.findElement(By.id(id));
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateTimeouts(int seconds) {
        this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void switchToContext(String contextName) {
        try {
            this.log("Context:" + contextName);
            this.driver.context(contextName);
        } catch (Exception e) {
            this.log("Switch Context Error:" + e.getMessage());
        }
    }

    @Override
    public void switchToNative() {
        log("Switching to Native Context");
        int times = 0;
        do {
            Set<String> contextNames = this.driver.getContextHandles();
            for (String contextName : contextNames) {
                if (contextName.contains("NATIVE_APP")) {
                    this.switchToContext(contextName);
                    return;
                }
            }
            times++;
            this.sleep(2000);
        } while (times < 15);
    }

    @Override
    public MobileElement findElementByXPath(String xPath) {
        try {
            return driver.findElementByXPath(xPath);
        } catch (Exception e) {
            this.log("Find Element By XPath Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public MobileElement findElementByAccessibilityId(String id) {
        try {
            return this.driver.findElementByAccessibilityId(id);
        } catch (Exception e) {
            this.log("Find Element By Id Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<MobileElement> findElementsByXPath(String xPath) {
        try {
            return (List<MobileElement>) driver.findElementsByXPath(xPath);
        } catch (Exception e) {
            this.log("Find Elements By XPath Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean waitForMobileElementPresence(MobileElement element, int timeLimitInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            this.log(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean waitForWebElementPresence(WebElement element, int timeLimitInSeconds) {
        try{
            System.out.println("In waitForWeb... Try wait time :: " + timeLimitInSeconds);
            WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch  (Exception e) {
            this.log(e.getMessage());
            return false;
        }
    }

    @Override
    public void setImplicitWait(Integer seconds) { driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS); }


    @Override
    public boolean elementIsClickable(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            this.log(e.getMessage());
            return false;
        }
    }

    @Override
    public void touchByCoordinate(int x, int y) {
        TouchAction touchAction = new TouchAction(driver);

        PointOption pressPoint = new PointOption();
        pressPoint.withCoordinates(x, y);

        touchAction.tap(pressPoint);
        touchAction.perform();
    }

    @Override
    public void slideButton(MobileElement button, Integer xStart, Integer xEnd) {
        int y = button.getCenter().getY();

        TouchAction touchAction = new TouchAction(driver);

        PointOption startPoint = new PointOption();
        startPoint.withCoordinates(xStart, y);

        PointOption endPoint = new PointOption();
        endPoint.withCoordinates(xEnd, y);
        touchAction.press(startPoint).moveTo(endPoint).waitAction(waitOptions(ofSeconds(1))).release().perform();
    }

    @Override
    public void longButtonPress(MobileElement button) {
        TouchAction touchAction = new TouchAction(driver);
        LongPressOptions longPressOptions = new LongPressOptions();
        longPressOptions.withDuration(Duration.ofSeconds(4)).withElement(ElementOption.element(button));
        touchAction.longPress(longPressOptions).release().perform();

    }

    @Override
    public void scrollDown(MobileElement firstElement, MobileElement secondElement) {
        int yStart = firstElement.getCenter().getY();
        int yEnd = secondElement.getCenter().getY();
        int x = firstElement.getCenter().getX();

        TouchAction touchAction = new TouchAction(driver);

        PointOption startPoint = new PointOption();
        startPoint.withCoordinates(x, yStart);

        PointOption endPoint = new PointOption();
        endPoint.withCoordinates(x, yEnd);

        touchAction.press(startPoint).waitAction(waitOptions(ofSeconds(1))).moveTo(endPoint).perform();
    }

    @Override
    public void tripleTapButton(MobileElement button) {
        TouchAction touchAction = new TouchAction(driver);
        TapOptions tapOptions = new TapOptions();
        tapOptions.withTapsCount(3).withElement(ElementOption.element(button));
        touchAction.tap(tapOptions).waitAction(waitOptions(ofMillis(100))).perform();

    }

    public abstract boolean isRunning(String appBundleId);

    public abstract boolean isRunningInBackground(String appBundleId);

    public abstract boolean isRunningInForeground(String appBundleId);
}