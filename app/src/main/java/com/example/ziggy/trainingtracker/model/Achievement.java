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

    protected Achievement(int requirement, String name, String info) {
        this.requirement = requirement;
        this.name = name;
        this.info = info;
    }

    /**
     * Update the state of the achievement pulling information from the user.
     * If completed, add the next achievement in line.
     * @param user Owner of the achievement to get the information from
     */
    void update(IUser user) {
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

    /**
     * Get the updated progress number using information from the user.
     * @param user Owner of the achievement to get the information from
     * @return
     */
    protected abstract int checkProgress(IUser user);

    /**
     * Check if the achievement has a successor.
     * @return
     */
    protected abstract boolean hasNextStage();

    /**
     * Get the succeeding achievement.
     * @return
     */
    protected abstract Achievement getNextAchievement();

    /**
     * @return the achievement's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the achievement's info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @return the achievement's requirement
     */
    public int getRequirement() {
        return requirement;
    }

    /**
     * @return the achievement's progress
     */
    public int getProgress() {
        return progress;
    }

    /**
     * @return the achievement's completion status
     */
    public boolean isCompleted() {
        return completed;
    }
}


