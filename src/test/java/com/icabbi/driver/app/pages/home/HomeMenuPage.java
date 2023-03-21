package com.icabbi.driver.app.pages.home;


import com.icabbi.driver.app.driver.IicabbiDriver;
import com.icabbi.driver.app.utils.AccessibilityId;
import io.appium.java_client.MobileElement;

public class HomeMenuPage {
  protected final IicabbiDriver driver;

  public HomeMenuPage(IicabbiDriver driver) {
    this.driver = driver;
  }

//  private MobileElement homeMenuProfileButton() {
//    return false;
////    return driver.findElementByAccessibilityId(AccessibilityId.MAIN_MENU_PROFILE_BUTTON);
//  }

  public boolean isBuildVersionTextDisplayed() {
    MobileElement element = driver.findElementById(AccessibilityId.APP_VERSION);
    String pattern = "v\\d\\.\\d\\.\\d \\(\\d+\\)";
    return element.isDisplayed() && element.getText().matches(pattern);
  }

//  public boolean isDisplayed() {
//    return driver.waitForMobileElementPresence(homeMenuProfileButton(), 3);
//  }

  public void openProfile() {
//    homeMenuProfileButton().click();
  }
}