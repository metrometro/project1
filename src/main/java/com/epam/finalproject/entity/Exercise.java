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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) object;
        if (exercise.exerciseId == exerciseId && (exercise.exerciseType != null && exercise.exerciseType.equals(exerciseType))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + exerciseId;
        result = prime * result + ((exerciseType == null) ? 0 : exerciseType.hashCode());
        result *= prime;
        return result;
    }
}