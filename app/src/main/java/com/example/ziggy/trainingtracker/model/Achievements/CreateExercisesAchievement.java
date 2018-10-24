package com.example.ziggy.trainingtracker.model.Achievements;

import com.example.ziggy.trainingtracker.model.IUser;

public class CreateExercisesAchievement extends Achievement {
    private static final String INFO = "Create your own exercises by clicking the \"+\" symbol in the exercises tab";
    private static final int START = 1;
    private static final int MAX = 10;

    private int stage;

    CreateExercisesAchievement() {
        super(START, "Create " + START + " Exercise" + (START > 1 ? "s" : ""), INFO);
        stage = 1;
    }

    private CreateExercisesAchievement(int requirement, int stage) {
        super(requirement, "Create " + requirement + " Exercises", INFO);
        this.stage = stage;
    }

    @Override
    protected int checkProgress(IUser user) {
        return user.getCustomExercises().size();
    }

    @Override
    protected boolean hasNextStage() {
        return nextRequirement() <= MAX;
    }

    @Override
    protected Achievement getNextAchievement() {
        return new CreateExercisesAchievement(nextRequirement(), stage + 1);
    }

    private int nextRequirement() {
        switch (stage + 1) {
            case 2:
                return 3;
            case 3:
                return 5;
            default:
                return stage*5 - 5;
        }
    }
}
