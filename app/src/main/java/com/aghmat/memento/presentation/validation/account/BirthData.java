package com.aghmat.memento.presentation.validation.account;

public class BirthData {

    private final String year;

    private final String month;

    private final String day;

    private final String lifeExpectancy;

    public BirthData(String year, String month, String day, String lifeExpectancy) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getLifeExpectancy() {
        return lifeExpectancy;
    }
}
