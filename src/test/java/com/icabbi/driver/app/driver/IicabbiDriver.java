package com.icabbi.driver.app.driver;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public interface IicabbiDriver {

    MobileElement findElementById(String id);

    MobileElement findElementByResourceId(String id);

    MobileElement findElementByMobileId(String id);

    MobileElement findElementByText(String id);

    void updateTimeouts(int seconds);

    MobileElement findElementByXPath(String xPath);

    List<MobileElement> findElementsByXPath(String xPath);

    MobileElement findElementByAccessibilityId(String accessibilityId);

    abstract MobileElement findElementByMobileXPath(String xpath);

    MobileElement waitForElement(String id);

    void touchByCoordinate(int x, int y);

    void activateApp();

    void teminateSettingsApp();

    void closeApp();

    void log(String message);

    void quit();

    boolean waitForMobileElementPresence(MobileElement element, int timeLimitInSeconds);

    void setImplicitWait(Integer seconds);

    boolean elementIsClickable(MobileElement element);

    boolean isIOS();

    void acceptNotificationsAlert();

    String currentPageSourceXML();

    void slideButton(MobileElement button, Integer xStart, Integer xEnd);

    void longButtonPress(MobileElement button);

    void scrollDown(MobileElement firstElement, MobileElement secondElement);

    void tripleTapButton(MobileElement button);

    void takeScreenshot(String fileName) throws IOException;

    void startScreenRecording();

    void stopRecordingScreen(String s) throws IOException;

    void implicitWait();

    void sleep (int milliseconds);

    void switchToNative();

    void switchToWeb();

    boolean waitForWebElementPresence(WebElement element, int timeLimitInSeconds);

    List<MobileElement> findElementsByXpathId(String xPathId);

    MobileElement findElementByXpathId(String alertWindow);

    void prepareBrowser();
    String getAppBundleId(String app);
}