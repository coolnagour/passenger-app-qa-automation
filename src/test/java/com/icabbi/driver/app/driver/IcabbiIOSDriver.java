package com.icabbi.driver.app.driver;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStopScreenRecordingOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.screenrecording.ScreenRecordingUploadOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IcabbiIOSDriver extends IcabbiDriverBase<IOSDriver<IOSElement>, IOSElement> {

    public IcabbiIOSDriver(String language, String country, DriverInitialization initializationCallback) throws MalformedURLException {
        super(language, country, initializationCallback);
    }

    @Override
    protected IOSDriver<IOSElement> createDriver(URL url, DesiredCapabilities capabilities) {
        return new IOSDriver<>(url, capabilities);
    }

    @Override
    protected String getDefaultAppFile() {
        return "apps/ios/iCabbiDriver.app";
    }

    @Override
    protected void configureCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.4");
        capabilities.setCapability("wdaStartupRetries", 4);
        capabilities.setCapability("iosInstallPause", 15000);
        capabilities.setCapability("wdaStartupRetryInterval", 5000);
        capabilities.setCapability("showIOSLog", false);
        capabilities.setCapability("showXcodeLog", false);
    }

    @Override
    public boolean isRunning(String appBundleId) {
        return false;
    }

    @Override
    public boolean isRunningInBackground(String appBundleId) {
        return false;
    }

    @Override
    public boolean isRunningInForeground(String appBundleId) {
        return false;
    }

    @Override
    public void startScreenRecording() {
        driver.startRecordingScreen(new IOSStartScreenRecordingOptions().withFps(25).withVideoType("mpeg4"));
    }

    @Override
    public void stopRecordingScreen(String fileName) throws IOException {
        String base64String = driver.stopRecordingScreen(new IOSStopScreenRecordingOptions().withUploadOptions(ScreenRecordingUploadOptions.uploadOptions()));
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath="target/"+fileName+".mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, data);
    }

    @Override
    public void acceptNotificationsAlert() {
        driver.switchTo().alert().accept();
    }

    @Override
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Override
    public void sleep(int milliseconds) {

    }

    @Override
    public void switchToWeb() {

    }

    @Override
    public boolean waitForWebElementPresence(WebElement element, int timeLimitInSeconds) {
        return false;
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

    }

    @Override
    public String getAppBundleId(String app) {
        return null;
    }
}