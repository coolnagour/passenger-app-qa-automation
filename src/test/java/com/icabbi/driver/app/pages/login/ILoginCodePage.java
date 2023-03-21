package com.icabbi.driver.app.pages.login;

public interface ILoginCodePage {

  public boolean isDisplayed();

  public boolean isErrorMessageDisplayed();

  public boolean isContinueEnabled();

  public void enterCode(String code);

  public void pressContinue();

  public void selectCodeElement(boolean clear);

//  public void skipPayment(String code);
}
