package com.example.ziggy.trainingtracker.model;

/**
 * Class representing an exercise based challenge, with a score
 */
public class Challenge implements IChallenge {

    private IExercise exercise;
    private int score;

    public Challenge(IExercise exercise) {
        this.exercise = exercise;
        score = 0;
    }

    /**
     * @return the challenge's name
     */
    @Override
    public String getName() {
        return exercise.getName();
    }

    /**
     * @return the challenge's unit
     */
    @Override
    public String getUnit() {
        return exercise.getUnit();
    }

    /**
     * @return the challenge's description
     */
    @Override
    public String getDescription() {
        return exercise.getDescription();
    }

    /**
     * @return the challenge's high score
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Set the new high score.
     * @param score The new score
     */
    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
