package com.icabbi.driver.app.pages.login;

//import com.passenger.app.driver.IicabbiDriver;
import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

public class LoginCodePageAndroid implements ILoginCodePage {
  protected final IicabbiDriver driver;
  private final String singleCodeMessage = "//*[@class='android.widget.View'][@text='Enter the code sent to']";
  private final String errorElement = "//*[@class='android.view.View'][@text='Code is invalid, check your code and try again.']";
  private final String singleFactorCodeField = "single-factor-code-text-field";
  private final String submit = "submit";

  public LoginCodePageAndroid(IicabbiDriver driver) {
    this.driver = driver;
    driver.switchToWeb();
  }

  private WebElement errorMessage() {
    return driver.findElementByXPath(errorElement);
  }

  private WebElement singleCodeMessage() {
    return driver.findElementByXPath(singleCodeMessage);
  }

  private WebElement singleFactorCodeField() { return driver.findElementByMobileId(singleFactorCodeField); }

  private WebElement continueButton() { return driver.findElementByMobileId(submit); }

  public boolean isDisplayed() {
      return driver.waitForWebElementPresence(singleFactorCodeField(), 3)
              && driver.waitForWebElementPresence(continueButton(), 3);
  }

  public boolean isErrorMessageDisplayed() {
    return driver.waitForWebElementPresence(errorMessage(), 1);
  }

  public boolean isContinueEnabled() {
    return continueButton().isEnabled();
  }

  public void enterCode(String code) {
    selectCodeElement(true);
    singleFactorCodeField().sendKeys(code);
  }

  public void pressContinue() {
    continueButton().click();
  }

  public void selectCodeElement(boolean clear) {
    singleFactorCodeField().click();
    if (clear == true) {
      singleFactorCodeField().clear();
    }
  }
}