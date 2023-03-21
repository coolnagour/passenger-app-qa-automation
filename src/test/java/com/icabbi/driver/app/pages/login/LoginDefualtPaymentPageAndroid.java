package com.icabbi.driver.app.pages.login;

import com.icabbi.driver.app.driver.IicabbiDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = about:blank
public class LoginDefualtPaymentPageAndroid implements ILoginDefaultPaymentPage{
    protected final IicabbiDriver driver;

    private final String skipButton = "SKIP FOR NOW";
    public LoginDefualtPaymentPageAndroid(IicabbiDriver driver) {
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