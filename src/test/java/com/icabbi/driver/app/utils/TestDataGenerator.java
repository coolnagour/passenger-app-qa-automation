//package com.icabbi.driver.app.utils;
//
//import com.github.javafaker.Faker;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
////import org.apache.commons.lang.StringUtils;
//
//import java.security.MessageDigest;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalAdjusters;
//import java.util.*;
//
//public class TestDataGenerator {
//  private static final String defaultLocale = "en-CA";
//  private static final String defaultDateFormat = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'";
//
//  public static List<String> alphanumericAlphabet() {
//    String[] AlphabetWithDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
//    List<String> AlphabetWithDigitsList = new ArrayList<String>();
//    AlphabetWithDigitsList = Arrays.asList(AlphabetWithDigits);
//    return AlphabetWithDigitsList;
//  }
//
//  public static String capitalize(String str) {
//    String words[] = str.split("\\s");
//    String capitalized = "";
//
//    for (String w:words) {
//      String first = w.substring(0,1);
//      String afterfirst = w.substring(1);
//      capitalized += first.toUpperCase() + afterfirst.toLowerCase() + " ";
//    }
//    return capitalized.trim();
//  }
//
//  public static String campaignName() {
//    int lowerLimit = 97;
//    int upperLimit = 122;
//    int count = 14;
//    Random  random = new Random();
//
//    StringBuffer r = new StringBuffer(count);
//
//    for (int i = 0; i < count; i++) {
//      int nextRandom = lowerLimit + (int)(random.nextFloat() * (upperLimit - lowerLimit + 1));
//      r.append((char) nextRandom);
//    }
//
//    return r.toString().toUpperCase();
//  }
//
//  public static String companyName() {
//    // Make sure names do not have a quote in them
//    return TestDataGenerator.faker().company().name().replace("'", "");
//  }
//
//  public static String fleetName() {
//    // Make sure names do not have a quote or a dot in them
//    String[] suffixes = { "Cab", "Cabs", "Cabbie", "Taxi", "Taxis", "TaxiCab", "TaxiCabs", "Company", "Inc", "Limited", "Acme"};
//    Random rand = new Random();
//    int index = rand.nextInt(suffixes.length);
//    return TestDataGenerator.faker().funnyName().name().replace("'", "").replace(".", "") + " " + suffixes[index].toString() + " " + TestDataGenerator.randomAlphanumericString(6);
//  }
//
//  public static long dateTimeToEpoch(String dateTime) {
//    Instant instant = Instant.parse(dateTime);
//    return instant.toEpochMilli();
//  }
//
//  public static String epochTime() {
//    Instant instant = Instant.now();
//    long seconds = instant.getEpochSecond();
//
//    return String.valueOf(seconds);
//  }
//
//  public static String epochTimeWithOffset(int offsetInSeconds) {
//    Instant instant = offsetInSeconds < 0 ? Instant.now().minusSeconds(Math.abs(offsetInSeconds)) : Instant.now().plusSeconds(Math.abs(offsetInSeconds));
//    long seconds = instant.getEpochSecond();
//
//    return String.valueOf(seconds);
//  }
//
//  public static String epochTimeMilliseconds() {
//    long milliseconds = Instant.now().toEpochMilli();
//
//    return String.valueOf(milliseconds);
//  }
//
//  public static String epochTimeMillisecondsWithOffset(int offsetInSeconds) {
//    Instant instant = offsetInSeconds < 0 ? Instant.now().minusSeconds(Math.abs(offsetInSeconds)) : Instant.now().plusSeconds(Math.abs(offsetInSeconds));
//    long milliseconds = instant.toEpochMilli();
//
//    return String.valueOf(milliseconds);
//  }
//
//  public static Faker faker() {
//    return TestDataGenerator.faker(TestDataGenerator.defaultLocale);
//  }
//
//  public static Faker faker(String locale) {
//    return new Faker(new Locale(locale));
//  }
//
//  public static String futureDate(long days, String format) {
//    Instant instant = Instant.now();
//    Instant futureDate = instant.plus(days, ChronoUnit.DAYS);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//
//    return formatter.format(futureDate);
//  }
//
//  public static String futureTime(int minutes, String format) {
//    Instant instant = Instant.now();
//    Instant futureTime = instant.plus(minutes, ChronoUnit.MINUTES);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//
//    return formatter.format(futureTime);
//  }
//
//  public static String now() {
//    Instant instant = Instant.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultDateFormat)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//    return formatter.format(instant);
//  }
//
//  public static String now(String format) {
//    Instant instant = Instant.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//    return formatter.format(instant);
//  }
//
//  public static String familyName() {
//    // Make sure names do not have a quote in them
//    return TestDataGenerator.faker().name().lastName().replace("'", "");
//  }
//
//  public static String givenName() {
//    // Make sure names do not have a quote in them
//    return TestDataGenerator.faker().name().firstName().replace("'", "");
//  }
//
//  public static long number() {
//    int offset = (int) (Math.random() * 1000) + 1;
//    return System.currentTimeMillis() + offset;
//  }
//
//  public static String pastDate(int days, String format) {
//    Instant instant = Instant.now();
//    Instant pastDate = instant.minus(days, ChronoUnit.DAYS);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//
//    return formatter.format(pastDate);
//  }
//
//  public static String pastTime(int minutes, String format) {
//    Instant instant = Instant.now();
//    Instant pastTime = instant.minus(minutes, ChronoUnit.MINUTES);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//
//    return formatter.format(pastTime);
//  }
//
//  public static String phoneCellNumber() {
//    // Make sure phone numbers do not have a dash in them
//    return "+1" + TestDataGenerator.faker().phoneNumber().cellPhone().replace("-", "");
//  }
//
//  public static String phoneNumber() {
//    return TestDataGenerator.faker().numerify("+90##########").toString();
//  }
//
//  public static String phoneNumber(String countryCode, String areaCode) {
//    String phoneCountryCode = countryCode != null ? countryCode : "1";
//    String phoneAreaCode = areaCode != null ? areaCode : "514";
//    String phoneFormat = "+" + phoneCountryCode + phoneAreaCode + "#######";
//    return TestDataGenerator.faker().numerify(phoneFormat).toString();
//  }
//
//  public static String phoneNumberWithExtension() {
//    // Make sure phone numbers do not have a dash in them
//    return "+1" + TestDataGenerator.faker().phoneNumber().phoneNumber().replace("-", "");
//  }
//
//  public static String automationEmail(String firstName, String lastName) {
//    return "qa-automation-" + firstName.toLowerCase() + "." + lastName.toLowerCase() + "." + randomAlphanumericString(5) + "@icabbi.com";
//  }
//
//  public static String automationPhoneNumber() {
//    String randomAreaCode = randomAreaCode("north-america");
//    String randomNumber = randomAreaCode + "555####";
//    return TestDataGenerator.faker().numerify(randomNumber).toString();
//  }
//
//  public static String automationUserResponse(String phoneNumber) {
//    String lastPart = phoneNumber.substring(phoneNumber.length() - 3);
//    return String.join("", lastPart, lastPart);
//  }
//
//  public static String randomAreaCode(String region) {
//    JsonHelper jsonHelper = new JsonHelper();
//    String AREA_CODES = "src/test/java/com/passenger/app/config/area-codes.json";
//    JsonObject jsonData = jsonHelper.getJsonFromFile(AREA_CODES);
//    JsonArray codes = jsonHelper.getJsonKeyValue(jsonData, region);
//
//    int max = codes.size();
//    Random rand = new Random();
//    int index = rand.nextInt(max);
//    JsonElement randomCode = codes.get(index);
//    String code = randomCode.getAsJsonObject().get("code").getAsString();
//    return code;
//  }
//
//  // Takes an int as max value and returns a random int betweem 0 and max
//  public static Integer randomNumber(int max) {
//    Random rand = new Random();
//    return rand.nextInt(max);
//  }
//
//  // Takes an int as exposant value and returns a random int based on 10 ^ exp
//  public static String randomHex(int exp) {
//    Random rand = new Random();
//    int value = (int) Math.pow(10, exp);
//    return Integer.toHexString(rand.nextInt(value));
//  }
//
//  public static String randomAlphabeticString(int targetStringLength) {
//    int leftLimit = 65; // letter 'A'
//    int rightLimit = 122; // letter 'z'
//    Random random = new Random();
//    StringBuilder buffer = new StringBuilder(targetStringLength);
//    for (int i = 0; i < targetStringLength; i++) {
//      int randomLimitedInt = leftLimit + (int)
//        (random.nextFloat() * (rightLimit - leftLimit + 1));
//      buffer.append((char) randomLimitedInt);
//  }
//    String generatedString = buffer.toString();
//    return generatedString;
//  }
//
//  public static String randomAlphanumericString(int targetStringLength) {
//    int leftLimit = 48; // number '0'
//    int rightLimit = 122; // letter 'z'
//    Random random = new Random();
//
//    // Filter to leave out Unicode characters between 65 and 90 – to avoid out of range characters
//    String generatedString = random.ints(leftLimit, rightLimit + 1)
//      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//      .limit(targetStringLength)
//      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//      .toString();
//
//    return generatedString;
//  }
//
//  public static String randomNumericString(int targetStringLength) {
//    int leftLimit = 48; // number '0'
//    int rightLimit = 57; // number '9'
//    Random random = new Random();
//
//    // Filter to leave out Unicode characters between 65 and 90 – to avoid out of range characters
//    String generatedString = random.ints(leftLimit, rightLimit + 1)
//      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//      .limit(targetStringLength)
//      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//      .toString();
//
//    return generatedString;
//  }
//
//  public static String randomAllCharsString(int targetStringLength) {
//    int leftLimit = 33; // symbol !
//    int rightLimit = 126; // symbol ~
//    Random random = new Random();
//
//    // Filter to leave out Unicode characters between 65 and 90 – to avoid out of range characters
//    String generatedString = random.ints(leftLimit, rightLimit + 1)
//    // .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//    .limit(targetStringLength)
//    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//    .toString();
//
//    return generatedString;
//  }
//
//  public static String randomAllSymbolsString(int targetStringLength) {
//    int leftLimit = 33; // symbol !
//    int rightLimit = 64; // symbol @
//    Random random = new Random();
//
//    // Filter to leave out Unicode characters between 65 and 90 – to avoid out of range characters
//    String generatedString = random.ints(leftLimit, rightLimit + 1)
//    .filter(i -> (i >= 48 || i <= 58) && (i >= 65 || i <= 90))
//    .limit(targetStringLength)
//    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//    .toString();
//
//    return generatedString;
//  }
//
//  public static String uuid() {
//    return UUID.randomUUID().toString();
//  }
//
//  public static char toHexChar(int i) {
//    if ((0 <= i) && (i <= 9)) {
//        return (char) ('0' + i);
//    } else {
//        return (char) ('a' + (i - 10));
//    }
//}
//  public static String byteToHex(byte data) {
//      StringBuffer buf = new StringBuffer();
//      buf.append(toHexChar((data >>> 4) & 0x0F));
//      buf.append(toHexChar(data & 0x0F));
//      return buf.toString();
//  }
//
//  public static String bytesToHex(byte[] data) {
//    StringBuffer buf = new StringBuffer();
//    for (int i = 0; i < data.length; i++) {
//        buf.append(byteToHex(data[i]).toUpperCase());
//    }
//    return (buf.toString());
//}
//
//  public static HashMap<String, String> buildHashedTokenParamsJson(int driverId, String mIMEI, String token) {
//    HashMap<String, String> params = new HashMap<String, String>();
//    params.put("i", Integer.toString(driverId));
//    params.put("a", "d");
//
//    ArrayList<String> data = new ArrayList<String>();
//    for (Map.Entry<String, String> param : params.entrySet()) {
//      data.add(param.getKey() + "=" + param.getValue());
//    }
//
//    Collections.sort(data);
////    String joinedData = StringUtils.join(data.toArray(), ":");
//    MessageDigest digester;
//
//    try {
//      digester = MessageDigest.getInstance("SHA-1");
////      String hash = joinedData + token + mIMEI;
////      digester.update(hash.getBytes(), 0, hash.length());
////      hash = bytesToHex(digester.digest());
////      params.put("h", hash.toLowerCase());
////    } catch (Exception e) {}
//
//    return params;
//  }
//
//  public static String nextWeekDayDate(String day, Integer hour, Integer minute) {
//    if (day == null) {
//      return TestDataGenerator.now(defaultDateFormat).toString();
//    }
//
//    DayOfWeek dayOfWeek = TestDataGenerator.getDayOfWeek(day);
//    LocalDate date = LocalDate.now();
//    LocalDate nextWeekDate = date.with(TemporalAdjusters.next(dayOfWeek));
//    LocalDateTime nextWeekDateTime = nextWeekDate.atTime(hour, minute);
//
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultDateFormat)
//      .withLocale( Locale.US )
//      .withZone( ZoneId.systemDefault() );
//
//    String d = formatter.format(nextWeekDateTime);
//    System.out.println(d);
//    return d;
//  }
//
//  private static DayOfWeek getDayOfWeek(String day) {
//    switch (day) {
//      case "monday":
//        return DayOfWeek.MONDAY;
//      case "tuesday":
//        return DayOfWeek.TUESDAY;
//      case "wednesday":
//        return DayOfWeek.WEDNESDAY;
//      case "thursday":
//        return DayOfWeek.THURSDAY;
//      case "friday":
//        return DayOfWeek.FRIDAY;
//      case "saturday":
//        return DayOfWeek.SATURDAY;
//      case "sunday":
//        return DayOfWeek.SUNDAY;
//      default:
//        return DayOfWeek.SUNDAY;
//    }
//  }
//
//  public static HashMap<String, String> formatBookingDate(String bookingCreatedAt) throws ParseException {
//    SimpleDateFormat fullDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//    SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
//    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
//    Date newDate = fullDateFormatter.parse(bookingCreatedAt);
//    HashMap<String, String> bookingDates = new HashMap<String, String>();
//
//    Long dateInMs = newDate.getTime();
//    Long timeInMs = (long) (newDate.getTime() + 6000);
//
//    bookingDates.put("booking_date", dateFormatter.format(newDate));
//    bookingDates.put("booking_time", timeFormatter.format(newDate));
//    bookingDates.put("bookingdate_ms", String.valueOf(dateInMs));
//    bookingDates.put("bookingclosedate_ms", String.valueOf(timeInMs));
//
//    return bookingDates;
//  };
//}
