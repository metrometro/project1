package com.epam.finalproject.entity;

public class Exercise extends Entity {

    private int exerciseId;
    private String exerciseType;

    public Exercise() {}

    public Exercise(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }
}