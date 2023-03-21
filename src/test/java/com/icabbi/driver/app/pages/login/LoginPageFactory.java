package com.icabbi.driver.app.pages.login;

//import com.passenger.app.driver.IicabbiDriver;

//import com.icabbi.driver.app.driver.IicabbiDriver;

import com.icabbi.driver.app.driver.IicabbiDriver;

public class LoginPageFactory {

  public static ILoginPhoneNumberPage getPhoneNumberPage(IicabbiDriver driver) {
    if (driver.isIOS()) {
      return new LoginPhoneNumberPageIOS(driver);
    } else {
      return new LoginPhoneNumberPageAndroid(driver);
    }
  }

  public static ILoginCodePage getLoginCodePage(IicabbiDriver driver) {
    if (driver.isIOS()) {
      return new LoginCodePageIOS(driver);
    } else {
      return new LoginCodePageAndroid(driver);
    }
  }

  public static ILoginDefaultPaymentPage getLoginDefualtPaymentPage(IicabbiDriver driver) {
    if (driver.isIOS()) {
//      return new LoginDefualtPaymentPageIOS(driver);
      return new LoginDefualtPaymentPageAndroid(driver);
    } else {
      return new LoginDefualtPaymentPageAndroid(driver);
    }
  }

  public static ILoginTandCPage getLoginTandCPagePage(IicabbiDriver driver) {
    if (driver.isIOS()) {
      return new LoginTandCPageAndroid(driver);
    } else {
      return new LoginTandCPageAndroid(driver);
    }
  }
}