package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;

public class Challenge implements IChallenge {

    IExercise exercise;
    int score;

    public Challenge() {
        //Test variables
        exercise = new Exercise("Name", "unit", "desc", "inst", new ArrayList<>());
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
