package com.icabbi.driver.app.pages.login;

import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

public class LoginPhoneNumberPageIOS implements ILoginPhoneNumberPage {
  protected final IicabbiDriver driver;
  private final String phoneIcon = "//XCUIElementTypeOther[@name=\"Phone icon\"]";
  private final static String signupMessage = "//XCUIElementTypeStaticText[@name=\"Enter your phone number to start booking with us\"]";
  private final static String signupUnderMessage = "//XCUIElementTypeStaticText[@name=\"A SMS will be sent to validate your phone number\"]";
  private final static String emailInputElement = "//XCUIElementTypeOther[@name=\"iCabbi Canada\"]/XCUIElementTypeOther[4]";
  private final static String errorElement = "//XCUIElementTypeStaticText[@name=\"error\"]";
  private final static String continueButton = "//XCUIElementTypeButton[@name=\"CONTINUE\"]";

  public LoginPhoneNumberPageIOS(IicabbiDriver driver) {
    this.driver = driver;
  }

  private WebElement emailElement() {
    return driver.findElementByXPath(emailInputElement);
  }

  private WebElement continueButton() {
    return driver.findElementByXPath(continueButton);
  }

  private WebElement errorMessage() {
    return driver.findElementByXPath(errorElement);
  }

  private WebElement phoneIcon() {
    return driver.findElementByXPath(phoneIcon);
  }

  private WebElement signupMessage() {
    return driver.findElementByXPath(signupMessage);
  }

  private WebElement signupUnderMessage() {
    return driver.findElementByXPath(signupUnderMessage);
  }

  public boolean isDisplayed() {
            return driver.waitForWebElementPresence(signupMessage(), 3)
          && driver.waitForWebElementPresence(signupUnderMessage(), 0)
          && driver.waitForWebElementPresence(emailElement(), 0)
          && driver.waitForWebElementPresence(continueButton(), 0);
  }

  public boolean isErrorMessageDisplayed() {
    return driver.waitForWebElementPresence(errorMessage(), 1);
  }

  public boolean isValidNumberMessage(String message) {
    return signupMessage().getText().equals(message);
  }

  public boolean isValidNumberMessageSub(String message) {
    return signupUnderMessage().getText().equals(message);
  }

  public boolean isContinueEnabled() {
    return continueButton().isEnabled();
  }

  public void enterPhoneNumber(String phoneNumber) {
    selectPhoneNumberElement(true);
    emailElement().sendKeys(phoneNumber);
  }

  public void pressContinue() {
    continueButton().click();
  }

  public void selectPhoneNumberElement(boolean clear) {
    emailElement().click();
    if (clear == true) {
      emailElement().clear();
    }
  }
}