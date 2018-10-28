package com.example.ziggy.trainingtracker.service;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkout;

import com.example.ziggy.trainingtracker.model.Workout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class JacksonSerializationService {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String CUSTOM_EXERCISE_DATA = "customExerciseData";
    private static final String CUSTOM_WORKOUT_DATA = "customWorkoutData";
    private static final String USER_DATA = "userData";

    private Context context;

    private SharedPreferences sharedPreferences;


    public JacksonSerializationService(Context context) {
        this.context = context;
        initSharedPreferences();
    }

    private void initSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    }

    /**
     * @param list The exercise list to convert to a json String
     *
     * @return Json string of the list
     */

    private String exerciseListToJsonString(List<IExercise> list) {
        String jsonResultString = "";

        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonResultString = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonResultString;
        }

        return jsonResultString;
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

        System.out.println("SAVED EXERCISE DATA : " + listJsonString);

        //Save changes
        editor.apply();
    }

    /**
     * @return Json string of what is found at the supplied directory, returns NULL if nothing is found
     */
    private String loadExerciseDataFromSharedPreferences() throws IOException{
        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(CUSTOM_EXERCISE_DATA, null);
        System.out.println("EXERCISE DATA STRING IN SHARED PREFS : " + jsonString);

        if (jsonString.equals("[]")){
            throw new IOException("There is no IExercise JSON data to deserialize");
        }

        return jsonString;
    }

    /**
     * Loads the Exercise List in Json format from SharedPreferences and creates a List of IExercises
     * @return An ArrayList<IExercise> with the saved Exercises
     */
    public ArrayList <IExercise> loadUserExerciseList() throws NullPointerException{
        ObjectMapper mapper = new ObjectMapper();

        ArrayList <IExercise> exerciseList = null;

        try {
            exerciseList = mapper.readValue(loadExerciseDataFromSharedPreferences(), new TypeReference<ArrayList<Exercise>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exerciseList == null){
            throw new NullPointerException("Could not deserialize list");
        }

        return exerciseList;
    }

    /**
     * @param list The workout list to convert to a json String
     *
     * @return Json string of the list
     */

    private String workoutListToJsonString(List<IWorkout> list) {
        String jsonResultString = "";

        ObjectMapper mapper = new ObjectMapper();

        try {
            jsonResultString = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonResultString;
        }

        return jsonResultString;
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
    private String loadWorkoutDataFromSharedPreferences() throws IOException{
        // Retrieve json string from shared prefs, return null if string not found
        String jsonString = sharedPreferences.getString(CUSTOM_WORKOUT_DATA, null);
        System.out.println("WORKOUT DATA STRING IN SHARED PREFS : " + jsonString);

        if (jsonString.equals("[]")){
            throw new IOException("There is no IExercise JSON data to deserialize");
        }

        return jsonString;
    }

    /**
     * Loads the Exercise List in Json format from SharedPreferences and creates a List of IWorkouts
     * @return An ArrayList<IWorkout> with the saved Exercises
     */
    public ArrayList <IWorkout> loadUserWorkoutList() throws NullPointerException{
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(IWorkout.class, new IWorkoutDeserializer());
        mapper.registerModule(module);
        System.out.println("Registered module");

        ArrayList <IWorkout> workoutList = null;

        try {
            workoutList = mapper.readValue(loadWorkoutDataFromSharedPreferences(), new TypeReference<ArrayList<Workout>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (workoutList == null){
            throw new NullPointerException("Could not deserialize list");
        }


        return workoutList;
    }

}
