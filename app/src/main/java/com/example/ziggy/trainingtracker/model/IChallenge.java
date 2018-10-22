package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;

public interface IChallenge {

    String getName();
    String getUnit();
    String getDescription();
    int getScore();
    void setScore(int score);
}
