package com.icabbi.driver.app.pages.login;

//import com.passenger.app.driver.IicabbiDriver;
import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

public class LoginCodePageIOS implements ILoginCodePage {
  protected final IicabbiDriver driver;
  private final String lockIcon = "//XCUIElementTypeOther[@name=\"SMS Code Icon\"]";
  private final static String singleCodeMessage = "//XCUIElementTypeStaticText[@name=\"Enter the code sent to\"]";
  private final static String singleCodeElement = "//XCUIElementTypeOther[@name=\"iCabbi Canada\"]/XCUIElementTypeOther[4]/XCUIElementTypeTextField";
  private final static String errorElement = "//XCUIElementTypeStaticText[@name=\"error\"]";
  private final static String validateButton = "//XCUIElementTypeButton[@name=\"VALIDATE\"]";
  private final static String resendCodeButton = "//XCUIElementTypeButton[@name=\"RESEND CODE\"]";

  public LoginCodePageIOS(IicabbiDriver driver) {
    this.driver = driver;
  }

  private WebElement singleCodeLockIcon() {
    return driver.findElementByXPath(lockIcon);
  }

  private WebElement singleCodeMessage() {
    return driver.findElementByXPath(singleCodeMessage);
  }

  private WebElement singleCodeElement() {
    return driver.findElementByXPath(singleCodeElement);
  }

  private WebElement validateButton() {
    return driver.findElementByXPath(validateButton);
  }

  private WebElement errorMessage() {
    return driver.findElementByXPath(errorElement);
  }

  private WebElement resendCodeButton() {
    return driver.findElementByXPath(resendCodeButton);
  }

  public boolean isDisplayed() {
    return driver.waitForWebElementPresence(singleCodeElement(), 3)
          && driver.waitForWebElementPresence(validateButton(), 0)
          && driver.waitForWebElementPresence(resendCodeButton(), 0);
  }

  public boolean isValidSingleCodeMessage(String message) {
    return singleCodeMessage().getText().equals(message);
  }

  public boolean isErrorMessageDisplayed() {
    return driver.waitForWebElementPresence(errorMessage(), 1);
  }

  public boolean isContinueEnabled() {
    return validateButton().isEnabled();
  }

  public void enterCode(String code) {
    selectCodeElement(true);
    singleCodeElement().sendKeys(code);
  }

  public void pressContinue() {
    validateButton().click();
  }

  public void selectCodeElement(boolean clear) {
    singleCodeElement().click();
    if (clear == true) {
      singleCodeElement().clear();
    }
  }
//
//    @Override
//    public void skipPayment(String code) {
//
//    }
}