package com.example.ziggy.trainingtracker.model;

public class Challenge implements IChallenge {

    IExercise exercise;
    int score;

    public Challenge(IExercise exercise) {
        this.exercise = exercise;
        score = 0;
    }

    public String getName() {
        return exercise.getName();
    }

    public String getUnit() {
        return exercise.getUnit();
    }

    public int getScore() {
        return score;
    }
}
