package com.icabbi.driver.app.pages.location;

//import com.passenger.app.driver.IicabbiDriver;

import com.icabbi.driver.app.driver.IicabbiDriver;

public class AllowLocationPageFactory {

  public static IAllowLocationPage getDriverPage(IicabbiDriver driver) {
    if (driver.isIOS()) {
      return new AllowLocationPageIOS(driver);
    } else {
      return new AllowLocationPageAndroid(driver);
    }
  }
}