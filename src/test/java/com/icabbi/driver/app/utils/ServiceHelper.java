//package com.icabbi.driver.app.utils;
//
//import com.google.api.client.http.*;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.http.json.JsonHttpContent;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//
//import java.io.IOException;
//
//public class ServiceHelper extends BaseHelper {
//  static final String BOOKING_URL = "https://api.booking.icabbi.dev/v1/bookings";
//  static final String DRIVERS_URL = "https://api.fleet.icabbi.dev/v1/drivers";
//  static final String DRIVER_LOCATION_URL = "https://api.driver.icabbi.dev/v1/locations";
//  static final String DRIVER_STATUS_URL = "https://api.driver.icabbi.dev/v1/status";
//  static final String FLEET_URL = "https://api.fleet.icabbi.dev/v1/fleets";
//  static final String IAM_URL = "https://api.iam.icabbi.dev/v1/users";
//  static final String PASSENGER_URL = "https://api.booking.icabbi.dev/v1/passengers";
//  static final String STATUS_AVAILABLE = "available";
//  HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
//  Gson gson = new Gson();
//
//  public ServiceHelper() {
//    super();
//  }
//
//  public Booking createBooking(String fleetId, String passengerId) {
//    JsonObject bookingPayload = this.buildBookingPayload(fleetId, passengerId);
//
//    TokenHelper tokenHelper = new TokenHelper();
//    String token = tokenHelper.getToken("email", "booking", "superAdmin");
//
//    final JsonHttpContent content = new JsonHttpContent(new JacksonFactory(), bookingPayload);
//
//    try {
//      HttpRequest request;
//      request = this.requestFactory.buildPostRequest(new GenericUrl(BOOKING_URL), (JsonHttpContent) content);
//      HttpHeaders headers = request.getHeaders();
//      headers.setAuthorization(token);
//      String newBookingResponse = request.execute().parseAsString();
//
//      Booking booking = gson.fromJson(newBookingResponse, Booking.class);
//      JsonHelper jsonHelper = new JsonHelper();
//      booking.departureAddress = jsonHelper.getJsonKeyValue(bookingPayload, "departureAddress", "address");
//      booking.destinationAddress = jsonHelper.getJsonKeyValue(bookingPayload, "destinationAddress", "address");
//      return booking;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  public Passenger createPassenger(String fleetId) {
//    JsonObject passengerPayload = this.buildPassengerPayload(fleetId);
//
//    TokenHelper tokenHelper = new TokenHelper();
//    String token = tokenHelper.getToken("email", "booking", "superAdmin");
//
//    final JsonHttpContent content = new JsonHttpContent(new JacksonFactory(), passengerPayload);
//
//    try {
//      HttpRequest request;
//      request = this.requestFactory.buildPostRequest(new GenericUrl(PASSENGER_URL), (HttpContent) content);
//      HttpHeaders headers = request.getHeaders();
//      headers.setAuthorization(token);
//      String newPassengerResponse = request.execute().parseAsString();
//
//      Passenger passenger = gson.fromJson(newPassengerResponse, Passenger.class);
//      passenger.givenName = passengerPayload.get("givenName").toString();
//      passenger.familyName = passengerPayload.get("familyName").toString();
//      passenger.email = passengerPayload.get("email").toString();
//      return passenger;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  public Passenger getRamdomPassenger() {
//    String passengers = this.getPassengers();
//
//    try {
//      JSONArray passengersJson = (JSONArray) PARSER.parse(passengers);
//      Integer index = TestDataGenerator.randomNumber(passengersJson.size());
//      JSONObject passengerObject = (JSONObject) passengersJson.get(index);
//      String id = passengerObject.get("givenName").toString();
//      String givenName = passengerObject.get("givenName").toString();
//      String familyName = passengerObject.get("familyName").toString();
//      String email = passengerObject.get("email").toString();
//      return new Passenger(id, givenName, familyName, email);
//
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  public String getDrivers() {
//    TokenHelper tokenHelper = new TokenHelper();
//    String token = tokenHelper.getToken("email", "fleet", "superAdmin");
//
//    try {
//      HttpRequest request;
//      GenericUrl genericUrl = new GenericUrl(DRIVERS_URL);
//      genericUrl.put("page", 0);
//      genericUrl.put("maxPerPage", 500);
//      genericUrl.put("sort", "-createdAt");
//      genericUrl.put("dynamicSearch", "qa-automation+");
//      request = this.requestFactory.buildGetRequest(genericUrl);
//      HttpHeaders headers = request.getHeaders();
//      headers.setAuthorization(token);
//      return request.execute().parseAsString();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  public String getFleets() {
//    TokenHelper tokenHelper = new TokenHelper();
//    String token = tokenHelper.getToken("email", "fleet", "superAdmin");
//
//    try {
//      HttpRequest request;
//      GenericUrl genericUrl = new GenericUrl(FLEET_URL);
//      genericUrl.put("page", 0);
//      genericUrl.put("maxPerPage", 500);
//      genericUrl.put("sort", "-createdAt");
//      genericUrl.put("name", "Automation");
//      request = this.requestFactory.buildGetRequest(genericUrl);
//      HttpHeaders headers = request.getHeaders();
//      headers.setAuthorization(token);
//      return request.execute().parseAsString();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  public String getPassengers() {
//    TokenHelper tokenHelper = new TokenHelper();
//    String token = tokenHelper.getToken("email", "booking", "superAdmin");
//
//    try {
//      HttpRequest request;
//      GenericUrl genericUrl = new GenericUrl(PASSENGER_URL);
//      genericUrl.put("page", 0);
//      genericUrl.put("maxPerPage", 500);
//      request = this.requestFactory.buildGetRequest(genericUrl);
//      HttpHeaders headers = request.getHeaders();
//      headers.setAuthorization(token);
//      return request.execute().parseAsString();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//}
