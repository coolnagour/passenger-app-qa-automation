package com.icabbi.driver.app.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// page_url = about:blank
public class LoginTandCPageIOS {

    public LoginTandCPageIOS(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}