package com.example.ziggy.trainingtracker.model;

public class CreatedExercisesAchievement extends Achievement {
    private static final String INFO = "Create your own exercises by clicking the \"+\" symbol in the exercises tab.";
    private static final int START = 1;
    private static final int MAX = 10;

    private int stage;

    CreatedExercisesAchievement() {
        super(START, "Create " + START + " Exercise" + (START > 1 ? "s" : ""), INFO);
        stage = 1;
    }

    private CreatedExercisesAchievement(int requirement, int stage) {
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
        return new CreatedExercisesAchievement(nextRequirement(), stage + 1);
    }

    /**
     * @return the requirement for the next achievement.
     */
    private int nextRequirement() {
        switch (stage) {
            case 1:
                return 3;
            default:
                return (stage - 1) * 5;
        }
    }
}
