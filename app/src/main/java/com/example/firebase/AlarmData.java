package com.example.firebase;

// AlarmData.java
public class AlarmData {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String message;

    public AlarmData(int year, int month, int day, int hour, int minute, String message) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.message = message;
    }

    // Getter methods
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getMessage() {
        return message;
    }
}
