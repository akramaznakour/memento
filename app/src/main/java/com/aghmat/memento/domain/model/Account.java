package com.aghmat.memento.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(tableName = "account")
public class Account implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private Long id;

    @SerializedName("birth_date")
    private LocalDate birthDate;

    @SerializedName("life_expectancy")
    private int lifeExpectancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}
