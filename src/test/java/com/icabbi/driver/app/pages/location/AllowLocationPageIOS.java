package com.icabbi.driver.app.pages.location;

import java.util.List;

//import com.passenger.app.driver.IicabbiDriver;
//import com.icabbi.driver.app.driver.IicabbiDriver;
import com.icabbi.driver.app.driver.IicabbiDriver;
//import com.passenger.app.utils.AccessibilityId;

import com.icabbi.driver.app.utils.AccessibilityId;
import io.appium.java_client.MobileElement;

public class AllowLocationPageIOS implements IAllowLocationPage {
  protected final IicabbiDriver driver;

  public AllowLocationPageIOS(IicabbiDriver driver) {
    this.driver = driver;
  }

  private List<MobileElement> alertButtons() {
    return driver.findElementsByXpathId(AccessibilityId.ALERT_WINDOW_BUTTONS);
  }

  private MobileElement alertWindow() {
    return driver.findElementByXpathId(AccessibilityId.ALERT_WINDOW);
  }

  private MobileElement allowOnceButton() {
    return alertButtons().get(0);
  }

  private MobileElement allowWhileUsingTheAppButton() {
    return alertButtons().get(1);
  }

  private MobileElement dontAllowAppButton() {
    return alertButtons().get(2);
  }

  @Override
  public boolean isDisplayed() {
    return driver.waitForMobileElementPresence(alertWindow(), 2)
        && driver.waitForMobileElementPresence(allowWhileUsingTheAppButton(), 0)
        && driver.waitForMobileElementPresence(allowOnceButton(), 0)
        && driver.waitForMobileElementPresence(dontAllowAppButton(), 0);
  }

  @Override
  public void allowOnce() {
    allowOnceButton().click();
  }

  @Override
  public void allowWhileUsingTheApp() {
    allowWhileUsingTheAppButton().click();
  }

  @Override
  public void dontAllow() {
    dontAllowAppButton().click();
  }
}