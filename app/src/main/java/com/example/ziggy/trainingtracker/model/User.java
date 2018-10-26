package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Class representing the user of the application, containing data related to individual use such as
 * the custom made exercises and workouts, the completed workouts and user info.
 */
public class User implements IUser {

    //Add achievements and goals
    private List<IExercise> customExercises = new ArrayList<>();
    private List<IWorkout> customWorkouts = new ArrayList<>();
    private List<Achievement> achievements = new ArrayList<>();
    private List <ExerciseStatistic> exerciseStatistics = new ArrayList<>();

    private List<IWorkout> finishedWorkouts = new ArrayList<>();
    private Map<String, IChallenge> finishedChallenges = new HashMap<>();

    private String username;

    //Data related to human qualities of user
    @Expose
    @SerializedName("user_name")
    private String name;
    @Expose
    @SerializedName("user_weight")
    private double weight;
    @Expose
    @SerializedName("user_height")
    private int height;


    public User(String username, String name, double weight, int height) {
        this.username = username;
        this.name = name;
        this.weight = weight;
        this.height = height;
        addAchievement(new CreatedExercisesAchievement());
        addAchievement(new FinishedWorkoutsAchievement());
    }

    //TODO Tests for the statistics functionality

    //TODO probably move statistic functionality to TrainingTracker


    public void addFinishedWorkout(IWorkout finishedWorkout){
        finishedWorkouts.add(finishedWorkout);
    }

    public void addWorkoutToStatistics(IWorkout workout){
        int exercisePosition = 0;

        for (IWorkoutBlock w : workout.getBlocks()){
            for (IExercise e: w.getExercises()){
                if (previousStatisticsAvailable(e) && e.isWeightBased()){
                    // Since the amount list and exercises in WorkoutBlock are linked by index positions, this is the way to access the correct amount for now
                    addToStatisticsList(e, w.getMultiplier(), w.getAmounts().get(exercisePosition++));
                }
                // If it is weightbased, create a new ExerciseStatistic object and add the stats to it
                else if (e.isWeightBased()){
                    exerciseStatistics.add(new ExerciseStatistic(e));
                    addToStatisticsList(e, w.getMultiplier(), w.getAmounts().get(exercisePosition++));
                }
            }
        }
    }


    private void addToStatisticsList(IExercise e, int sets, int reps){
        for (ExerciseStatistic statistic : exerciseStatistics){
            if (statistic.getExercise().equals(e)){
                statistic.addStatistics(sets, reps, 22.5); //TODO temp hardcoded weight until it is implemented in workoutblock
            }
        }
    }

    private boolean previousStatisticsAvailable(IExercise e){
        for (ExerciseStatistic previousStatistic : exerciseStatistics){
            if (previousStatistic.getExercise().equals(e)){
                return true;
            }
        }

        return false;
    }


    public LinkedHashMap<Date, Double> generateStatisticsForExercise(IExercise e, int sets, int reps) {
        //TODO depending on how the user accesses the statistics, change this implementation (i.e if we only show exercises which already has statistics available, or if we show them all and let the user select)

        for (ExerciseStatistic previousStatistic : exerciseStatistics) {
            if (previousStatistic.getExercise().equals(e)) {

                try {
                    return previousStatistic.getStatisticForSpecificSetReps(sets, reps);
                }
                catch (NoSuchElementException exception){
                    exception.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public void addCustomExercise(IExercise e) {
        customExercises.add(e);
    }

    @Override
    public void removeCustomExercise(IExercise e) {
        customExercises.remove(e);
    }

    @Override
    public void addCustomWorkout(IWorkout w) {
        customWorkouts.add(w);
    }

    @Override
    public void removeCustomWorkout(IWorkout w) {
        customWorkouts.remove(w);
    }

    @Override
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    @Override
    public void addChallenge(IChallenge challenge) {
        finishedChallenges.put(challenge.getName(), challenge);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<IExercise> getCustomExercises() {
        return Collections.unmodifiableList(customExercises);
    }

    @Override
    public List<IWorkout> getCustomWorkouts() {
        return Collections.unmodifiableList(customWorkouts);
    }

    @Override
    public List<IWorkout> getFinishedWorkouts() {
        return finishedWorkouts;
    }

    @Override
    public List<IChallenge> getFinishedChallenges() {
        return new ArrayList<>(finishedChallenges.values());
    }

    /**
     * Updates the status of all the achievements before returning them.
     * @return the list all the achievements
     */
    @Override
    public List<Achievement> getAchievements() {
        for (int i = 0; i < achievements.size(); i++) {
            achievements.get(i).update(this);
        }
        return Collections.unmodifiableList(achievements);
    }

    @Override
    public void setCustomExercises(List<IExercise> customExercises) {
        this.customExercises = customExercises;
    }

    @Override
    public void setCustomWorkouts(List<IWorkout> customWorkouts) {
        this.customWorkouts = customWorkouts;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
