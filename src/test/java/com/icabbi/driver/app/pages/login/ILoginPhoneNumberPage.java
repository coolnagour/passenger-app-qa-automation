package com.icabbi.driver.app.pages.login;

public interface ILoginPhoneNumberPage {

  public boolean isDisplayed();

  public boolean isErrorMessageDisplayed();

  public boolean isValidNumberMessage(String message);

  public boolean isValidNumberMessageSub(String message);

  public boolean isContinueEnabled();

  public void enterPhoneNumber(String phoneNumber);

  public void pressContinue();

  public void selectPhoneNumberElement(boolean b);
}
