package com.example.ziggy.trainingtracker.model;

public class CreateExercisesAchievement extends Achievement {

    CreateExercisesAchievement(int requirement) {
        super(requirement, "Create " + requirement + " Exercises!", "Create your own exercises by clicking the \"+\" symbol in the exercises tab");
    }

    @Override
    protected int checkProgress(IUser user) {
        return user.getCustomExercises().size();
    }
}
