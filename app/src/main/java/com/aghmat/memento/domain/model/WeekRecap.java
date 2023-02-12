package com.aghmat.memento.domain.model;

import com.aghmat.memento.domain.enums.WeekType;

import java.time.LocalDate;

public class WeekRecap {

    private int weekNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private WeekType weekType;

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public WeekType getWeekType() {
        return weekType;
    }

    public void setWeekType(WeekType weekType) {
        this.weekType = weekType;
    }
}
