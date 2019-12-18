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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Diet diet = (Diet) object;
        if (diet.dietId == dietId && (diet.dietType != null && diet.dietType.equals(dietType))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dietId;
        result = prime * result + ((dietType == null) ? 0 : dietType.hashCode());
        result *= prime;
        return result;
    }
}