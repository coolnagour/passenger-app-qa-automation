package com.icabbi.driver.app.pages.login;

import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

public class LoginPhoneNumberPageAndroid implements ILoginPhoneNumberPage {
  protected final IicabbiDriver driver;
  private final String phoneIcon = "//*[@class='android.widget.Image']";
  private final String signupMessage = "//*[@class='android.widget.View'][@text='Enter your phone number to start booking with us']";
  private final String signupUnderMessage = "//*[@class='android.widget.View'][@text='A SMS will be sent to validate your phone number']";
  private final String continueButton = "send-code-button";
  private final String errorElement = "//*[@class='android.view.View'][@text='Phone Number is invalid.']";
  private final String enterPhoneNumber = "displayPhoneNumber";
  private final String sendCodeButton = "send-code-button";
  private final String selectedCountryCode = "input-group-addon-selected-country-code";



  public LoginPhoneNumberPageAndroid(IicabbiDriver driver) {
    this.driver = driver;
    driver.prepareBrowser();
    driver.switchToWeb();
  }

  private WebElement phoneNumberElement() {
    return driver.findElementByMobileId(enterPhoneNumber);
  }

  private WebElement continueButton() {
    return driver.findElementByMobileId(continueButton);
  }

  private WebElement errorMessage() {
    return driver.findElementByXPath(errorElement);
  }

  private WebElement signupMessage() {
    return driver.findElementByXPath(signupMessage);
  }

  private WebElement signupUnderMessage() {
    return driver.findElementByXPath(signupUnderMessage);
  }

  private WebElement enterPhoneNumber() {
    return driver.findElementByMobileId(enterPhoneNumber);
  }

  private WebElement sendCodeButton() {
    return driver.findElementByMobileId(sendCodeButton);
  }

  private WebElement inputCountryCode() {
    return driver.findElementByMobileId(selectedCountryCode);
  }

  public boolean isDisplayed() {
    return this.driver.waitForWebElementPresence(enterPhoneNumber(), 3)
            && this.driver.waitForWebElementPresence(sendCodeButton(), 3)
            && this.driver.waitForWebElementPresence(inputCountryCode(), 3);
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
    phoneNumberElement().sendKeys(phoneNumber);
  }

  public void pressContinue() {
    continueButton().click();
  }

  public void selectPhoneNumberElement(boolean clear) {
    phoneNumberElement().click();
    if (clear == true) {
      phoneNumberElement().clear();
    }
  }
}