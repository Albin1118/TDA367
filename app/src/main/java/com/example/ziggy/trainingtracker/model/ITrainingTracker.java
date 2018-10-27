package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface ITrainingTracker {

    /**
     * Adds an Exercise to the user's list of custom Exercises.
     * @param name exercise name
     * @param unit exercise unit
     * @param description exercise description
     * @param instructions exercise instructions
     * @param categories exercise categories
     * @param weightBased true if exercise is weight based
     */
    void addCustomExercise(String name, String unit, String description, String instructions, List<ExerciseCategory> categories, boolean weightBased);

    /**
     * Removes an Exercise from the user's list of custom Exercises.
     * @param e Exercise to be removed
     */
    void removeCustomExercise(IExercise e);


    /**
     * Adds a Workout to the user's list of custom Workouts.
     * @param w Workout to be added
     */
    void addCustomWorkout(IWorkout w);


    /**
     * Removes a Workout from the user's list of custom Workouts.
     * @param w Workout to be removed
     */
    void removeCustomWorkout(IWorkout w);

    /**
     * Loads a set of base exercises that can't be edited by the user.
     * @param baseExercises the list of exercises to add to the base exercises
     */
    void loadBaseExercises(List<IExercise> baseExercises);

    /**
     * Loads a set of base workouts that can't be edited by the user.
     * @param baseWorkouts the list of workouts to add to the base workouts
     */
    void loadBaseWorkouts(List<IWorkout> baseWorkouts);

    /**
     * Loads a set of base challenges.
     * @param baseChallenges the list of challenges to add to the base challenges
     */
    void loadBaseChallenges(List<IChallenge> baseChallenges);

    /**
     * Checks if specified exercise is custom made by the user.
     * @param e Exercise to be checked
     * @return True if custom made
     */
    boolean checkIfCustom(IExercise e);

    /**
     * Checks if specified workout is custom made by the user.
     * @param w Workout to be checked
     * @return True if custom made
     */
    boolean checkIfCustom(IWorkout w);

    /**
     * Finish the workout adding it to the users list of finished workouts.
     * @param workout The finished workout
     */
    void finishWorkout(IWorkout workout);

    /**
     * Finish a challenge with the specified score.
     * If the new score is higher than the previous, set new score and add the challenge to users finished challenges (without duplicating).
     * @param challenge The finished challenge
     * @param newScore The new score
     */
    void finishChallenge(IChallenge challenge, int newScore);

    /**
     * @return an unmodifiable list of the base exercises + the users custom exercises
     */
    List<IExercise> getExercises();

    /**
     * @return an unmodifiable list of the base workouts + the users custom workouts
     */
    List<IWorkout> getWorkouts();

    /**
     * @return a list of all the users finished challenges + all of the base challenges that are unfinished
     */
    List<IChallenge> getChallenges();

    /**
     * @return an unmodifiable list of the achievements
     */
    List<Achievement> getAchievements();

    /**
     * @return the active user
     */
    IUser getUser();
}
