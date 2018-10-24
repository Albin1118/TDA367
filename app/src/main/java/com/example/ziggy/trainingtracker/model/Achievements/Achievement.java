package com.example.ziggy.trainingtracker.model.Achievements;

import com.example.ziggy.trainingtracker.model.IUser;

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

    public void update(IUser user) {
        if (!completed) {
            progress = checkProgress(user);
            if (progress >= requirement) {
                completed = true;
                progress = requirement;
                if (hasNextStage()) {
                    Achievement nextAchievement = getNextAchievement();
                    user.addAchievement(nextAchievement);
                    nextAchievement.update(user);
                }
            }
        }
    }

    protected abstract int checkProgress(IUser user);

    protected abstract boolean hasNextStage();

    protected abstract Achievement getNextAchievement();

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


