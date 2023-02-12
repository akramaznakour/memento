package com.aghmat.memento.domain.model;

import com.aghmat.memento.domain.enums.AgeType;

public class YearRecap {

    private int Age;
    private int startYear;
    private int endYear;
    private AgeType yearType;

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public AgeType getYearType() {
        return yearType;
    }

    public void setYearType(AgeType yearType) {
        this.yearType = yearType;
    }
}
