package com.icabbi.driver.app.pages.login;

import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

// page_url = about:blank
public class LoginTandCPageAndroid implements ILoginTandCPage{
    protected final IicabbiDriver driver;

    private final String continueButton = "CONTINUE";
    public LoginTandCPageAndroid(IicabbiDriver driver) {
        this.driver = driver;
    }

    private WebElement continueButton() {
        return driver.findElementByText(continueButton);
    }
    @Override
    public boolean isDisplayed() {
        System.out.println("in TandC is displaved");
        return driver.waitForWebElementPresence(continueButton(), 10);
    }

    @Override
    public void pressContinue() {
        continueButton().click();
    }
}