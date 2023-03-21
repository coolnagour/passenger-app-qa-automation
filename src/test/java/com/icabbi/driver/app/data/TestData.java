package com.icabbi.driver.app.data;

import org.joda.time.DateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class TestData {

    public static long epochTime() {
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();

        return seconds;
    }

    public static long epochTimePlusSeconds(int seconds) {
        Instant instant = Instant.now().plusSeconds(seconds);

        return instant.getEpochSecond();
    }

    public static long epochTimePlusMinutes(int minutes) {
        Instant instant = Instant.now().plus(minutes, ChronoUnit.MINUTES);

        return instant.getEpochSecond();
    }

    public static long epochTimeMinusMinutes(int minutes) {
        Instant instant = Instant.now().minus(minutes, ChronoUnit.MINUTES);

        return instant.getEpochSecond();
    }

    public static String futureTime(int minutes, String format) {
        Instant instant = Instant.now();
        Instant futureTime = instant.plus(minutes, ChronoUnit.MINUTES);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                .withLocale( Locale.US )
                .withZone( ZoneId.systemDefault() );

        return formatter.format(futureTime);
    }

    public static String currentTime(String format) {
        Instant instant = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                .withLocale( Locale.US )
                .withZone( ZoneId.systemDefault() );

        return formatter.format(instant);
    }

    public static String epochTimeFormatted(Long epochTime, String format) {
        Instant instant = Instant.ofEpochSecond(epochTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                .withLocale( Locale.US )
                .withZone( ZoneId.systemDefault() );

        return formatter.format(instant);
    }

    public static Double getRandomDoubleInRange(Double min, Double max) {
        Double randomDouble = (Math.random() * (max - min)) + min;
        return Math.round(randomDouble * 100.0)  / 100.0;
    }

    public static int getRandomIntInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String lastMonthName() {
        DateTime date = DateTime.now().minusMonths(1);
        return date.toString("MMMM");
    }

    public static String currentMonthName() {
        DateTime date = DateTime.now();
        return date.toString("MMMM");
    }

    public static Integer daysInPreviousMonth(){
        return java.time.LocalDate.now().minusMonths(1).lengthOfMonth();
    }

    public static Integer daysInCurrentMonth(){
        return java.time.LocalDate.now().lengthOfMonth();
    }

    public static String previousYear() {
        DateTime date = DateTime.now().minusYears(1);
        return date.toString("YYYY");
    }

    public static String currentYear() {
        DateTime date = DateTime.now();
        return date.toString("YYYY");
    }

    public static String randomNumericString(int targetStringLength) {
        int leftLimit = 48; // number "0"
        int rightLimit = 57; // number "9"
        Random random = new Random();

        // Filter to leave out Unicode characters between 65 and 90 – to avoid out of range characters
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                //.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}