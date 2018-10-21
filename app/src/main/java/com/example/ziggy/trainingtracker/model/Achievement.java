package com.example.ziggy.trainingtracker.model;

/**
 * Class representing an achievement which the user can earn by accomplishing certain criteria
 * for app usage and workout-completion
 */
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


