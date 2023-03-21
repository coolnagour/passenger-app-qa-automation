package com.icabbi.driver.app.pages.home;

import com.icabbi.driver.app.driver.IicabbiDriver;
import com.icabbi.driver.app.pages.login.*;
import com.icabbi.driver.app.utils.AccessibilityId;
import com.icabbi.driver.app.utils.AutomationData;
import io.appium.java_client.MobileElement;

import static org.junit.Assert.assertTrue;

public class HomePage {
  protected final IicabbiDriver driver;

  public HomePage(IicabbiDriver driver) {
    this.driver = driver;
  }

  private MobileElement menuButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.MAIN_MENU_BUTTON);
  }

  private MobileElement bookADeliveryButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.BOOK_A_DELIVERY_BUTTON);
  }

  private MobileElement pairAndPayButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.PAIR_AND_PAY_BUTTON);
  }

  private MobileElement bookingDestinationButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.ENTER_DESTINATION_BUTTON);
  }

  private MobileElement locateMeButton() {
    return driver.findElementByAccessibilityId(AccessibilityId.LOCATE_ME);
  }

  public boolean isDisplayed() {
    return driver.waitForMobileElementPresence(menuButton(), 3)
            && driver.waitForMobileElementPresence(bookADeliveryButton(), 3)
            && driver.waitForMobileElementPresence(pairAndPayButton(), 3)
            && driver.waitForMobileElementPresence(locateMeButton(), 3);
  }

  public void enterBookingDestination() {
    bookingDestinationButton().click();
  }

  public void login(String phoneNumber, String userResponse) {

    ILoginPhoneNumberPage loginPhoneNumberPage = LoginPageFactory.getPhoneNumberPage(driver);
    assertTrue(loginPhoneNumberPage.isDisplayed());
    loginPhoneNumberPage.enterPhoneNumber(phoneNumber);
    assertTrue(loginPhoneNumberPage.isContinueEnabled());
    loginPhoneNumberPage.pressContinue();

    ILoginCodePage loginCodePage = LoginPageFactory.getLoginCodePage(driver);
    driver.sleep(100);
    assertTrue(loginCodePage.isDisplayed());
    loginCodePage.enterCode(userResponse);
    assertTrue(loginCodePage.isContinueEnabled());
    loginCodePage.pressContinue();

    driver.switchToNative();

    ILoginDefaultPaymentPage defaultPaymentPage = LoginPageFactory.getLoginDefualtPaymentPage(driver);
    assertTrue(defaultPaymentPage.isDisplayed());
    defaultPaymentPage.pressSkipForNow();

    ILoginTandCPage tandCPage = LoginPageFactory.getLoginTandCPagePage(driver);
    assertTrue(tandCPage.isDisplayed());
    tandCPage.pressContinue();
  }

  public void loginWithDefaultUser() {
    HomePage homePage = new HomePage(driver);
    homePage.login(AutomationData.USER_PHONE_NUMBER, AutomationData.USER_RESPONSE);
  }

  public void openMenu() {
    menuButton().click();
  }
}