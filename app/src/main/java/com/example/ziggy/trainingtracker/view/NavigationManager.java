package com.example.ziggy.trainingtracker.view;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.Workout;

/**
 * Controls the navigation between the views and how they are created.
 */
public interface NavigationManager {
    /**
     * Goes back to the previous page.
     */
    void goBack();

    /**
     * Navigates to the home page.
     */
    void navigateHome();

    /**
     * Navigates to the workouts tab.
     */
    void navigateWorkouts();

    /**
     * Navigates to the active workout tab.
     * A workout might not have been selected yet.
     */
    void navigateActiveWorkout();

    /**
     * Navigates to the active workout tab with the specified workout as selected workout.
     * @param workout The workout to activate
     */
    void navigateActiveWorkout(IWorkout workout);

    /**
     * Navigates to the exercises tab.
     */
    void navigateExercises();

    /**
     * Navigates to the more tab.
     */
    void navigateMore();

    /**
     * Navigates to the exercise creator.
     */
    void navigateExerciseCreator();

    /**
     * Navigates to the exercise editor.
     * @param exercise The exercise to be edited
     */
    void navigateExerciseEditor(IExercise exercise);

    /**
     * Navigates to the exercise detail view showing the specified exercise.
     * @param exercise The exercise to show the details of
     */
    void navigateExerciseDetailView(IExercise exercise);

    /**
     * Navigates to the workout creator.
     */
    void navigateWorkoutCreator();

    /**
     * Navigates to the workout editor.
     * @param workout The workout to be edited
     */
    void navigateWorkoutEditor(IWorkout workout);

    /**
     * Navigates to the workout block creator.
     */
    void navigateWorkoutBlockCreator();

    /**
     * Navigates to the workout detail view showing the specified workout.
     * @param workout The workout to show the details of
     */
    void navigateWorkoutDetailView(IWorkout workout);

    void navigateActiveChallenge(IChallenge challenge);

    /**
     * Navigates to the user info page
     */
    void navigateUserInfo();

    /**
     * Navigates to the statistics.
     */
    void navigateStatistics();

    /**
     * Navigates to the achievements.
     */
    void navigateAchievements();

    /**
     * Sets the state of the navigation bar to the menuItem with the specified id.
     * @param id Id of the menuItem to set as selected
     */
    void setNavBarState(int id);

    /**
     * Hides the navigation bar.
     */
    void hideNavigationBar();

    /**
     * Un-hides the navigation bar.
     */
    void showNavigationBar();
}
