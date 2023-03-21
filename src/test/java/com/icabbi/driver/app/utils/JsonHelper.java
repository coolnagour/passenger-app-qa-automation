package com.icabbi.driver.app.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;

public class JsonHelper {

  public JsonHelper() {

  }

  public JsonObject getJsonFromFile(String filePath) {
    File newFile = new File(filePath);
    String file = newFile.getAbsolutePath();

    try {
      FileReader reader = new FileReader(file);
      return JsonParser.parseReader(reader).getAsJsonObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public JsonObject getJsonKeyObject(JsonObject jsonData, String rootKey, String key) {
    JsonObject rootData = (JsonObject) jsonData.get(rootKey);
    if (key == "") {
      return rootData;
    }
    return this.getRecursiveObject(rootData, key);
  }

  public String getJsonKeyValue(JsonObject jsonData, String rootKey, String key) {
    JsonObject rootData = (JsonObject) jsonData.get(rootKey);
    if (key == "") {
      return rootData.toString();
    }
    return this.getRecursiveString(rootData, key);
  }

  public JsonArray getJsonKeyValue(JsonObject jsonData, String key) {
    JsonArray data = (JsonArray) jsonData.get(key);
    if (data.isJsonArray()) {
      return data;
    }
    return new JsonArray();
  }

  public JsonArray getJsonKeyValues(JsonObject jsonData, String rootKey, String key) {
    JsonObject rootData = (JsonObject) jsonData.get(rootKey);
    if (rootData.isJsonArray()) {
      return rootData.getAsJsonArray();
    }
    return new JsonArray();
  }

  public String getJsonKeyValueFromString(String jsonData, String rootKey, String key) {
    try {
      JsonObject data = JsonParser.parseString(jsonData).getAsJsonObject();

      return this.getJsonKeyValue(data, rootKey, key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String getRecursiveString(JsonObject obj, String key) {
    if (obj.has(key)) {
      return obj.get(key).toString();
    } else {
      Object[] keys = obj.keySet().toArray();
      JsonObject rootKey = (JsonObject) obj.get(keys[0].toString());
      return this.getRecursiveString(rootKey, key);
    }
  }

  protected JsonObject getRecursiveObject(JsonObject obj, String key) {
    if (obj.has(key)) {
      return (JsonObject) obj.get(key);
    } else {
      Object[] keys = obj.keySet().toArray();
      JsonObject rootKey = (JsonObject) obj.get(keys[0].toString());
      return this.getRecursiveObject(rootKey, key);
    }
  }
}
