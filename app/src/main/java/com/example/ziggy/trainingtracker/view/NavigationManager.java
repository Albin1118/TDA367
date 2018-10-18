package com.example.ziggy.trainingtracker.view;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.Workout;

public interface NavigationManager {
    /**
     * Go back to the previous page.
     */
    void goBack();

    /**
     * Navigate to the home page.
     */
    void navigateHome();

    /**
     * Navigate to the workouts tab.
     */
    void navigateWorkouts();

    /**
     * Navigate to the active workout tab without a selected workout.
     */
    void navigatePreActiveWorkout();

    /**
     * Navigate to the active workout tab with a selected workout.
     * @param workout The workout to activate
     */
    void navigateActiveWorkout(Workout workout);

    /**
     * Navigate to the exercises tab.
     */
    void navigateExercises();

    /**
     * Navigate to the more tab.
     */
    void navigateMore();

    /**
     * Navigate to the exercise creator.
     */
    void navigateExerciseCreator();

    /**
     * Navigate to the exercise editor.
     * @param exercise The exercise to be edited
     */
    void navigateExerciseEditor(IExercise exercise);

    /**
     * Navigate to the exercise detail view.
     * @param exercise The exercise to show the details of
     */
    void navigateExerciseDetailView(IExercise exercise);

    /**
     * Navigate to the workout creator.
     */
    void navigateWorkoutCreator();

    /**
     * Navigate to the workout editor.
     * @param workout The workout to be edited
     */
    void navigateWorkoutEditor(Workout workout);

    /**
     * Navigate to the workout block creator.
     */
    void navigateWorkoutBlockCreator();

    /**
     * Navigate to the workout detail view.
     * @param workout The workout to show the details of
     */
    void navigateWorkoutDetailView(Workout workout);

    /**
     * Navigate to the statistics.
     */
    void navigateStatistics();

    /**
     * Set the state of the navigation bar.
     * @param id Id of the menuItem to set checked
     */
    void setNavBarState(int id);

    /**
     * Hide the navigation bar.
     */
    void hideNavigationBar();

    /**
     * Un-hide the navigation bar.
     */
    void showNavigationBar();
}
