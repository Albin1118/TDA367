package com.example.ziggy.trainingtracker.service;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class SharedPreferencesService{

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CUSTOM_EXERCISE_DATA = "customExerciseData";
    public static final String CUSTOM_WORKOUT_DATA =  "customWorkoutData";
    public static final String USER_DATA = "userData";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Method that saves a json version of the list supplied to shared preferences
     * @param list the list to convert to json
     * @param directory the directory in shared preferences to write to
     */

    public void saveDataToSharedPreferences(List<Object> list, String directory){
        // Init sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Convert list to json
        String listJsonString = listToJsonString(list);

        // Add the Json string to the shared prefs dir
        editor.putString(directory, listJsonString);

        //Save changes
        editor.apply();


    }

    /**
     *
     * @param directory The directory to read from in shared preferences
     * @return Json string of what is found at the supplied directory, returns NULL if nothing is found
     */

    public String loadDataFromSharedPreferences(String directory){
        Gson gson = new Gson();

        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(directory, null);

        return jsonString;
    }

    /**
     *
     * @param list The list to convert to a json String
     * @return Json string of the list
     */

    private String listToJsonString(List<Object> list){
        // Create gson object for json functionality
        Gson gson = new Gson();

        // Convert list to json
        String jsonString = gson.toJson(list);

        return jsonString;
    }

}
