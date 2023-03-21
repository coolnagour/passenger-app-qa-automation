//package com.icabbi.driver.app.utils;
//
//import com.google.gson.JsonObject;
//import org.json.simple.parser.JSONParser;
//import org.openqa.selenium.html5.Location;
//
//import java.io.File;
//import java.io.FileReader;
//import java.time.Instant;
//
//public class BaseHelper {
//  static final JSONParser PARSER = new JSONParser();
//  static final String LOCATION_FILE_PATH = "src/test/java/com/passenger/app/config/locations.json";
//  static final String ADDRESSES_FILE_PATH = "src/test/java/com/passenger/app/config/addresses.json";
//
//  public BaseHelper() {
//
//  }
//
//  public JsonObject buildBookingPayload(String fleetId, String passengerId) {
//    JsonObject bookingPayload = this.getBookingData("montreal");
//    bookingPayload.addProperty("fleetId", fleetId);
//    bookingPayload.addProperty("passengerId", passengerId);
//
//    return bookingPayload;
//  }
//
//  public JsonObject buildPassengerPayload(String fleetId) {
//    JsonObject driverPayload = new JsonObject();
//    String givenName = TestDataGenerator.givenName();
//    String familyName = TestDataGenerator.familyName();
//    String email = givenName.toLowerCase() + "." + familyName.toLowerCase() + "@mailme.com";
//    driverPayload.addProperty("givenName", givenName);
//    driverPayload.addProperty("familyName", familyName);
//    driverPayload.addProperty("phone", TestDataGenerator.phoneNumber());
//    driverPayload.addProperty("email", email);
//    driverPayload.addProperty("fleetId", fleetId);
//
//    return driverPayload;
//  }
//
//  public long epochTimeMilliseconds() {
//    long milliseconds = Instant.now().toEpochMilli();
//
//    return milliseconds;
//  }
//
//  private JsonObject getBookingData(String key) {
//    File authFile = new File(ADDRESSES_FILE_PATH);
//    String file = authFile.getAbsolutePath();
//
//    try {
//      FileReader reader = new FileReader(file);
//      JSONParser parser = new JSONParser();
//      JsonObject bookingData = (JsonObject) parser.parse(reader);
//      JsonObject keyData = (JsonObject) bookingData.get(key);
//      return keyData;
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  public Location buildLocation(String locationName) {
//    JsonObject locations = this.getLocation(locationName);
//    Double latitude = locations.get("latitude").getAsDouble();
//    Double longitude = locations.get("longitude").getAsDouble();
//    Double altitude = locations.get("altitude").getAsDouble();
//
//    return new Location(latitude, longitude, altitude);
//  }
//
//  public JsonObject getLocation(String locationName) {
//    JsonHelper jsonHelper = new JsonHelper();
//    JsonObject data = jsonHelper.getJsonFromFile(LOCATION_FILE_PATH);
//    return jsonHelper.getJsonKeyObject(data, locationName, "");
//  }
//
//  protected JsonObject getTokenData(String filePath, String rootKey, String key) {
//    JsonHelper jsonHelper = new JsonHelper();
//    JsonObject data = jsonHelper.getJsonFromFile(filePath);
//    return jsonHelper.getJsonKeyObject(data, rootKey, key);
//  }
//}