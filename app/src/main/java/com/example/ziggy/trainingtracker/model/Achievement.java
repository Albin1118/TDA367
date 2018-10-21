package com.example.ziggy.trainingtracker.model;

public class Achievement implements IAchievement{

    String achievementName;
    String achievementDescription;
    boolean achievementCompleted;


    public String getAchievementName() {
        return achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public boolean isAchievementCompleted() {
        return achievementCompleted;
    }
}


