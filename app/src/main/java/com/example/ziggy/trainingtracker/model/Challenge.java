package com.example.ziggy.trainingtracker.model;

/**
 * Class representing an exercise based challenge, with a score
 */
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

    public void setScore(int score) {
        this.score = score;
    }
}
