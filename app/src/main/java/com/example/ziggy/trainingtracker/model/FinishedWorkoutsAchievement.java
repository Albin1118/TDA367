package com.example.ziggy.trainingtracker.model;

public class FinishedWorkoutsAchievement extends Achievement {
    private static final String INFO = "Go to the workout tab, select a workout and click on start workout.";
    private static final int START = 1;

    private int stage;

    FinishedWorkoutsAchievement() {
        super(START, "Finish " + START + " Workout" + (START > 1 ? "s" : ""), INFO);
        stage = 1;
    }

    private FinishedWorkoutsAchievement(int requirement, int stage) {
        super(requirement, "Finish " + requirement + " Workouts", INFO);
        this.stage = stage;
    }

    @Override
    protected int checkProgress(IUser user) {
        return user.getFinishedWorkouts().size();
    }

    @Override
    protected boolean hasNextStage() {
        return true;
    }

    @Override
    protected Achievement getNextAchievement() {
        return new FinishedWorkoutsAchievement(nextRequirement(), stage + 1);
    }

    private int nextRequirement() {
        switch (stage) {
            case 1:
                return 5;
            case 2:
                return 10;
            case 3:
                return 25;
            case 4:
                return 50;
            case 5:
                return 75;
            default:
                return (stage - 4) * 50;
        }
    }
}
