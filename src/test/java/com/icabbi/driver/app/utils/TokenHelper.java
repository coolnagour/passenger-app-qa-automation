//package com.icabbi.driver.app.utils;
//
//import com.google.gson.JsonObject;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class TokenHelper extends BaseHelper {
//  String tokenFilePath = "src/test/java/com/passenger/app/config/auth-token-data.json";
//
//  public TokenHelper() {
//
//  }
//
//  public String getToken(String type, String rootKey, String key) {
//    JsonObject params = this.getTokenData(tokenFilePath, rootKey, key);
//    JsonObject data = new JsonObject();
//    try {
//      data.addProperty("type", type);
//      data.add("params", params);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    ProcessBuilder pb = new ProcessBuilder("node", "src/test/java/com/passenger/app/utils/oauth.js", data.toString());
//    pb.directory(new File("."));
//
//    try {
//      Process pr = pb.start();
//      BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//      String line;
//      StringBuilder builder = new StringBuilder();
//      while ((line = in.readLine()) != null) {
//        builder.append(line);
//      }
//      in.close();
//      String tokens = builder.toString();
//      return this.getAccessToken(tokens);
//    } catch (IOException  e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  private String getAccessToken(String tokens) {
//    try{
//      JSONParser parser = new JSONParser();
//      JSONObject tokenObject = (JSONObject) parser.parse(tokens);
//      Object accessTokenKey = (Object) tokenObject.get("access_token");
//      return "Bearer " + accessTokenKey.toString();
//    } catch (ParseException  e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//}
