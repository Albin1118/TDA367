package com.example.ziggy.trainingtracker.service;


import android.content.Context;
import android.content.SharedPreferences;



import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkout;

import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.Exercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesService {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String CUSTOM_EXERCISE_DATA = "customExerciseData";
    private static final String CUSTOM_WORKOUT_DATA = "customWorkoutData";

    public static final String USER_DATA = "userData";

    private Context context;

    private SharedPreferences sharedPreferences;


    public SharedPreferencesService(Context context) {
        this.context = context;
        initSharedPreferences();
    }

    private void initSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    }

    /**
     * Method that saves a json version of the exercise list supplied to shared preferences
     *
     * @param list the list to convert to json
     */

    public void saveExerciseDataToSharedPreferences(List<IExercise> list) {
        // Init sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Convert list to json
        String listJsonString = exerciseListToJsonString(list);

        // Add the Json string to the shared prefs dir
        editor.putString(CUSTOM_EXERCISE_DATA, listJsonString);

        //Save changes
        editor.apply();
    }

    /**
     * Method that saves a json version of the workout list supplied to shared preferences
     *
     * @param list the list to convert to json
     */
    public void saveWorkoutDataToSharedPreferences(List<IWorkout> list) {
        // Init sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Convert list to json
        String listJsonString = workoutListToJsonString(list);

        // Add the Json string to the shared prefs dir
        editor.putString(CUSTOM_WORKOUT_DATA, listJsonString);

        //Save changes
        editor.apply();
    }

    /**
     * @return Json string of what is found at the supplied directory, returns NULL if nothing is found
     */

    public String loadExerciseDataFromSharedPreferences() {
        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(CUSTOM_EXERCISE_DATA, null);

        return jsonString;
    }

    /**
     * @return Json string of what is found at the supplied directory, returns NULL if nothing is found
     */

    public String loadWorkoutDataFromSharedPreferences() {
        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(CUSTOM_WORKOUT_DATA, null);

        return jsonString;
    }


    /**
     * @param list The list to convert to a json String
     *
     * @return Json string of the list
     */

    private String workoutListToJsonString(List<IWorkout> list) {
        // Create gson object for json functionality
        Gson gson = new Gson();

        // Convert list to json
        String jsonString = gson.toJson(list);

        return jsonString;
    }

    private String exerciseListToJsonString(List<IExercise> list) {
        // Create gson object for json functionality
        Gson gson = new Gson();

        // Convert list to json
        String jsonString = gson.toJson(list);

        return jsonString;


    }

    // Keeping for now

      /*
    public void saveExerciseDataToSharedPreferences(List<Exercise> list, String directory){
        // Init sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Convert list to json
        String listJsonString = listToJsonString(list);

        // Add the Json string to the shared prefs dir
        editor.putString(directory, listJsonString);

        //Save changes
        editor.apply();
    }
    */

        /*

    /**
     *
     * @param directory The directory to read from in shared preferences
     * @return Json string of what is found at the supplied directory, returns NULL if nothing is found
     */

    /*
    public String loadDataFromSharedPreferences(String directory){
        Gson gson = new Gson();

        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(directory, null);

        return jsonString;
    }
    */

}
