package com.example.ziggy.trainingtracker.model;

/**
 * Class representing an achievement which the user can earn by accomplishing certain criteria
 * for app usage and workout-completion
 */
public abstract class Achievement {

    private String name;
    private String description;
    private boolean completed;

    abstract void update(IUser user);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}


