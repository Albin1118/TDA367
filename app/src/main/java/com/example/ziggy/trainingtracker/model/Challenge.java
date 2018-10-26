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

    @Override
    public String getName() {
        return exercise.getName();
    }

    @Override
    public String getUnit() {
        return exercise.getUnit();
    }

    @Override
    public String getDescription() {
        return exercise.getDescription();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
