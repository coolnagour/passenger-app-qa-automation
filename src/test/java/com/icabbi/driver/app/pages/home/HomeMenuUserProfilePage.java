package com.icabbi.driver.app.pages.home;

//import com.passenger.app.driver.IicabbiDriver;
//import com.passenger.app.utils.AccessibilityId;
import com.icabbi.driver.app.driver.IicabbiDriver;
import com.icabbi.driver.app.utils.AccessibilityId;
import io.appium.java_client.MobileElement;

public class HomeMenuUserProfilePage {
  protected final IicabbiDriver driver;

  public HomeMenuUserProfilePage(IicabbiDriver driver) {
    this.driver = driver;
  }

  public MobileElement homeMenuUserLogoutButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.MAIN_MENU_USER_PROFILE_LOGOUT_BUTTON);
  }

  public boolean isLogoutButtonCorrectLanguage(String buttonText) {
    return homeMenuUserLogoutButton().getText().equals(buttonText);
  }

  public boolean isDisplayed() {
    return driver.waitForMobileElementPresence(homeMenuUserLogoutButton(), 1);
  }

  public void logout() {
    homeMenuUserLogoutButton().click();
  }
}