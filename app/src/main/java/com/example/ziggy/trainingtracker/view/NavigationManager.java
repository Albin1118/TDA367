package com.example.ziggy.trainingtracker.view;

import com.example.ziggy.trainingtracker.model.Exercise;
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
     * @param workout the workout to activate
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
     * @param exercise the exercise to be edited
     */
    void navigateExerciseEditor(Exercise exercise);

    /**
     * Navigate to the exercise detail view.
     * @param exercise the exercise to show the details of
     */
    void navigateExerciseDetailView(Exercise exercise);

    /**
     * Navigate to the workout creator.
     */
    void navigateWorkoutCreator();

    /**
     * Navigate to the workout editor.
     * @param workout the workout to be edited
     */
    void navigateWorkoutEditor(Workout workout);

    /**
     * Navigate to the workout detail view.
     * @param workout the workout to show the details of
     */
    void navigateWorkoutDetailView(Workout workout);
}
