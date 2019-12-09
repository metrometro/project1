package com.epam.finalproject.entity;

public class Diet extends Entity {

    private int dietId;
    private String dietType;

    public Diet() {
    }

    public Diet(int dietId, String dietType) {
        this.dietId = dietId;
        this.dietType = dietType;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public int getDietId() {
        return dietId;
    }

    public String getDietType() {
        return dietType;
    }
}