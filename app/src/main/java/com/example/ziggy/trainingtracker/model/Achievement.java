package com.example.ziggy.trainingtracker.model;

/**
 * Class representing an achievement which the user can earn by accomplishing certain criteria
 * for app usage and workout-completion
 */
public abstract class Achievement {

    private String name;
    private String info;
    private int requirement;
    private int progress;
    private boolean completed = false;

    Achievement(int requirement, String name, String info) {
        this.requirement = requirement;
        this.name = name;
        this.info = info;
    }

    void update(IUser user) {
        progress = checkProgress(user);
        completed = progress >= requirement;
    }

    protected abstract int checkProgress(IUser user);

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getRequirement() {
        return requirement;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isCompleted() {
        return completed;
    }
}


