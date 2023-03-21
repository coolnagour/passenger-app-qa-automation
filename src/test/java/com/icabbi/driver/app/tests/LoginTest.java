package com.icabbi.driver.app.tests;

import com.icabbi.driver.app.base.BaseTest;
import com.icabbi.driver.app.pages.home.HomePage;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

  @Test
  public void loginWithPhoneNumberAndCodeShouldSucceed() {
    HomePage homePage = new HomePage(driver);
    homePage.loginWithDefaultUser();
    assertTrue(homePage.isDisplayed());
  }
}