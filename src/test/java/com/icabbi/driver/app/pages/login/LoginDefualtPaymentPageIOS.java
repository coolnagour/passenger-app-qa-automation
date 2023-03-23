package com.icabbi.driver.app.pages.login;

import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebElement;

public class LoginDefualtPaymentPageIOS implements ILoginDefaultPaymentPage{
    protected final IicabbiDriver driver;

    private final String skipButton = "SKIP FOR NOW";
    public LoginDefualtPaymentPageIOS(IicabbiDriver driver) {
        this.driver = driver;
    }

    private WebElement skipButton() {
        return driver.findElementByText(skipButton);
    }
    @Override
    public boolean isDisplayed() {
        return driver.waitForWebElementPresence(skipButton(), 10);
    }

    @Override
    public void pressSkipForNow() {
        skipButton().click();
    }
}