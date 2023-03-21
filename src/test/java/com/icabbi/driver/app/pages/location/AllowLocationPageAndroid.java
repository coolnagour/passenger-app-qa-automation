package com.icabbi.driver.app.pages.location;

//import com.passenger.app.driver.IicabbiDriver;
import com.icabbi.driver.app.driver.IicabbiDriver;
//import com.passenger.app.utils.AccessibilityId;

import com.icabbi.driver.app.utils.AccessibilityId;
import io.appium.java_client.MobileElement;

public class AllowLocationPageAndroid implements IAllowLocationPage {
  protected final IicabbiDriver driver;

  public AllowLocationPageAndroid(IicabbiDriver driver) {
    this.driver = driver;
  }

  private MobileElement alertWindow() {
    return driver.findElementByAccessibilityId(AccessibilityId.ALLOW_LOCATION_SCREEN);
  }

  private MobileElement allowButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.ALLOW_LOCATION_SCREEN_ALLOW_BUTTON);
  }

  private MobileElement denyButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.ALLOW_LOCATION_SCREEN_DENY_BUTTON);
  }

  public boolean isDisplayed() {
    return driver.waitForMobileElementPresence(alertWindow(), 2)
    && driver.waitForMobileElementPresence(allowButton(), 0)
    && driver.waitForMobileElementPresence(denyButton(), 0);
  }

  @Override
  public void allowOnce() {
    allowButton().click();
  }

  @Override
  public void allowWhileUsingTheApp() {
    allowOnce();
  }

  @Override
  public void dontAllow() {
    denyButton().click();
  }
}