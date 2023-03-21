package com.icabbi.driver.app.driver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.icabbi.driver.app.utils.BundleId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.appmanagement.ApplicationState;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IcabbiAndroidDriver extends IcabbiDriverBase<AndroidDriver<AndroidElement>, AndroidElement> {

    public IcabbiAndroidDriver(String language, String country, DriverInitialization initializationCallback) throws MalformedURLException {
        super(language, country, initializationCallback);
    }

    @Override
    protected AndroidDriver<AndroidElement> createDriver(URL url, DesiredCapabilities capabilities) {
        return new AndroidDriver<AndroidElement>(url, capabilities);
    }

    @Override
    protected String getDefaultAppFile() {
//        return "apps/android/app-qa.apk";
        return "apps/android/ic2-ca-single-std-dev-release.apk";
    }

    @Override
    protected void configureCapabilities(DesiredCapabilities capabilities) {

                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.icabbi.passenger_App");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.icabbi.passengerapp.presentation.launcher.LauncherActivity");


        capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "src/apps/chromedriver/chromedriver");

        capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "src/apps/chromedriver/chromedriver");


        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability("androidInstallTimeout", 100000L);
        capabilities.setCapability("adbExecTimeout", 200000L);
        capabilities.setCapability("newCommandTimeout", 100000L);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("gpsEnabled", true);

        capabilities.setCapability("appium:setWebContentsDebuggingEnabled", true);
    }

    @Override
    public void startScreenRecording() {
        driver.startRecordingScreen(new AndroidStartScreenRecordingOptions().withVideoSize("1280x720"));
    }

    @Override
    public void stopRecordingScreen(String fileName) throws IOException {
        String base64String = driver.stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath="target/"+fileName+".mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, data);
    }

    @Override
    public List<MobileElement> findElementsByXPath(String xPath) {
        return null;
    }

    @Override
    public void acceptNotificationsAlert() {
    }

    @Override
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    @Override
    public void sleep(int milliseconds) {
        try {
            log("Sleeping for :: "+milliseconds+" milliseconde");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void switchToNative() {
//
//    }

    @Override
    public void switchToWeb() {
        log("Switching to Web Context");
        int times = 0;
        do {
            log("In do time :: "+times);
            Set<String> contextNames = this.driver.getContextHandles();
            for (String contextName : contextNames) {

                log("In WEBVIEW context name :: "+contextName);
                if (contextName.contains("WEBVIEW")) {

                    log("In if context name :: "+contextName);
                    this.switchToContext(contextName);
                    return;
                }
            }
            times++;
            this.sleep(2000);
        } while (times < 15);

        log("End Switching to Web Context");
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
    public List<MobileElement> findElementsByXpathId(String xPathId) {
        return null;
    }

    @Override
    public MobileElement findElementByXpathId(String alertWindow) {
        return null;
    }

    @Override
    public void prepareBrowser() {
        sleep(500);
        String chromeBundleId = getAppBundleId("chrome");
        updateTimeouts(0);
        if (isRunning(chromeBundleId)) {
            MobileElement chromeSetup = findElementByXPath("//*[@class='android.widget.TextView'][@text='Welcome to Chrome']");
            if (chromeSetup != null) {
                findElementByXPath("//*[@class='android.widget.Button'][@text='ACCEPT & CONTINUE']").click();
                findElementByXPath("//*[@class='android.widget.Button'][@text='NO THANKS']").click();
                MobileElement refuse = findElementByXPath("//*[@class='android.widget.Button'][@text='NO THANKS']");
                if (waitForMobileElementPresence(refuse, 1)) {
                    refuse.click();
                }
            } else {
                log("No Chrome Setup Needed");
            }
            log("Chrome Setup Done");
        }
        updateTimeouts(30);
    }

    @Override
    public String getAppBundleId(String app) {
        return BundleId.getBundleId(this, app);
    }

    @Override
    public boolean isRunning(String appBundleId) {
        return isRunningInBackground(appBundleId) || isRunningInForeground(appBundleId);
    }

    @Override
    public boolean isRunningInBackground(String appBundleId) {
        ApplicationState appState = this.driver.queryAppState(appBundleId);
        return appState.equals(ApplicationState.RUNNING_IN_BACKGROUND);
    }

    @Override
    public boolean isRunningInForeground(String appBundleId) {
        ApplicationState appState = this.driver.queryAppState(appBundleId);
        return appState.equals(ApplicationState.RUNNING_IN_FOREGROUND);
    }
}