package com.example.ziggy.trainingtracker.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.SQLOutput;
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

    /**
     * Add a finished workout to the list of finished workouts.
     * @param finishedWorkout The workout to add
     */
    public void addFinishedWorkout(IWorkout finishedWorkout){
        finishedWorkouts.add(finishedWorkout);
    }

    /**
     * The IWorkout sent will be used to create a statistics object which will be stored in the exerciseStatistics list
     * @param workout The workout to add to statistics
     */
    public void addWorkoutToStatistics(IWorkout workout){

        for (IWorkoutBlock w : workout.getBlocks()){
            for (IExercise e: w.getExercises()){

                if (previousStatisticsAvailable(e) && e.isWeightBased()) {
                    // Since the amount list and exercises in WorkoutBlock are linked by index positions, this is the way to access the correct amount for now

                    System.out.println("PREVIOUS STATISTICS AVAILABLE");
                    addToStatisticsList(
                            e,
                            w.getMultiplier(),
                            w.getAmounts().get(0));
                }
                // If it is weightbased, create a new ExerciseStatistic object and add the stats to it

                else if (e.isWeightBased()){
                    System.out.println("ADDING NEW EXERCISE : " + e.getName() + " SETS : " + w.getMultiplier() + " REPS : " + w.getAmounts().get(0) + " TO STATISTICS");
                    exerciseStatistics.add(new ExerciseStatistic(e));

                    addToStatisticsList(e,
                            w.getMultiplier(),
                            w.getAmounts().get(0));
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

    /**
     * @param e The exercise to get statistics for
     * @param sets The set count to get statistics for
     * @param reps The rep count to get statistics for
     * @return A list of dates and weight statistics for the supplied exercise, sets and reps
     */

    @Nullable
    public LinkedHashMap<Date, Double> generateStatisticsForExercise(IExercise e, int sets, int reps) throws NullPointerException {
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
        throw new NullPointerException("No statistics available");
    }

    public List <IExercise> getExercisesWithStatisticsAvailable(){
        ArrayList <IExercise> result = new ArrayList<>();
        for (ExerciseStatistic es : exerciseStatistics){
            result.add(es.getExercise());
        }
        return result;
    }

    /**
     * Add a new exercise to the list of custom exercises.
     * @param e The exercise to add
     */
    @Override
    public void addCustomExercise(IExercise e) {
        customExercises.add(e);
    }

    /**
     * Remove a custom exercise.
     * @param e The exercise to remove
     */
    @Override
    public void removeCustomExercise(IExercise e) {
        customExercises.remove(e);
    }

    /**
     * Add a new workout to the list of custom workouts.
     * @param w The workout to add
     */
    @Override
    public void addCustomWorkout(IWorkout w) {
        customWorkouts.add(w);
    }

    /**
     * Remove a custom workout.
     * @param w The workout to remove
     */
    @Override
    public void removeCustomWorkout(IWorkout w) {
        customWorkouts.remove(w);
    }

    /**
     * Add a new achievement in progress
     * @param achievement The new achievement
     */
    @Override
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    /**
     * Add a finished challenge to the list of finished challenges.
     * @param challenge The challenge to add
     */
    @Override
    public void addChallenge(IChallenge challenge) {
        finishedChallenges.put(challenge.getName(), challenge);
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the user's weight
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * @return the user's height
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @return the custom exercises
     */
    @Override
    public List<IExercise> getCustomExercises() {
        return Collections.unmodifiableList(customExercises);
    }

    /**
     * @return the custom workouts
     */
    @Override
    public List<IWorkout> getCustomWorkouts() {
        return Collections.unmodifiableList(customWorkouts);
    }

    /**
     * @return the finished workouts
     */
    @Override
    public List<IWorkout> getFinishedWorkouts() {
        return finishedWorkouts;
    }

    /**
     * @return the finished challenges
     */
    @Override
    public List<ExerciseStatistic> getExerciseStatistics() {
        return exerciseStatistics;
    }

    @Override
    public List<IChallenge> getFinishedChallenges() {
        return new ArrayList<>(finishedChallenges.values());
    }

    /**
     * Updates the status of all the achievements before returning them.
     * @return all the achievements
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

    /**
     * @param weight The user's new weight
     */
    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
