package com.icabbi.driver.app.pages.location;

public interface IAllowLocationPage {

  public void allowOnce();

  public void allowWhileUsingTheApp();

  public void dontAllow();

  public boolean isDisplayed();
}