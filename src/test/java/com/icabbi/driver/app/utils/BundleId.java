package com.icabbi.driver.app.utils;

import com.google.gson.JsonObject;
import com.icabbi.driver.app.driver.IicabbiDriver;
//import com.passenger.app.driver.IicabbiDriver;

public class BundleId {
  private static JsonObject iosBundleIds = new JsonObject();
  private static JsonObject androidBundleIds = new JsonObject();

  private static void init() {
    iosBundleIds.addProperty("passenger-app", "com.icabbi.passenger-app.dev");
    iosBundleIds.addProperty("preferences", "com.apple.Preferences");
    iosBundleIds.addProperty("mobilesafari", "com.apple.mobilesafari");
    iosBundleIds.addProperty("maps", "com.apple.Maps");
    androidBundleIds.addProperty("chrome", "com.android.chrome");
    androidBundleIds.addProperty("settings", "com.android.settings");
    androidBundleIds.addProperty("vending", "com.android.vending");
    androidBundleIds.addProperty("packageinstaller", "com.google.android.packageinstaller");
    androidBundleIds.addProperty("maps", "com.google.android.apps.maps");
  }

  public static String getBundleId(IicabbiDriver driver, String app) {
    init();
    if (driver.isIOS()) {
      return iosBundleIds.get(app).getAsString();
    }
    return androidBundleIds.get(app).getAsString();
  }
}