package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;

public class Challenge implements IChallenge {

    IExercise exercise;
    int score;

    public Challenge() {
        //Test parameters
        exercise = new Exercise("Name", "unit", "desc", "inst", new ArrayList<>());
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
