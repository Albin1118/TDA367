package com.example.ziggy.trainingtracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.ziggy.trainingtracker.model.Challenge;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IUser;
import com.example.ziggy.trainingtracker.model.IWorkout;

import com.example.ziggy.trainingtracker.model.User;
import com.example.ziggy.trainingtracker.service.JacksonSerializationService;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadLinesFromFileService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.service.SharedPreferencesService;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainViewModel extends AndroidViewModel {
    private ITrainingTracker model;

    public MainViewModel(Application app) {
        super(app);
        model = new TrainingTracker();
        loadExercises();
        loadWorkouts();
        loadChallenges();
        loadData();
    }

    /**
     * Saves a list to be saved to the SharedPreferences directory
     * @param list the list to be saved
     */
    private void saveExerciseData(List<IExercise> list){
        JacksonSerializationService serializationService = new JacksonSerializationService(getApplication().getApplicationContext());

        serializationService.saveExerciseDataToSharedPreferences(list);
    }

    /**
     * Saves a list to be saved to the SharedPreferences directory
     * @param list the list to be saved
     */
    private void saveWorkoutData(List<IWorkout> list){
        JacksonSerializationService serializationService = new JacksonSerializationService(getApplication().getApplicationContext());

        serializationService.saveWorkoutDataToSharedPreferences(list);
    }


    /**
     * Loads Exercises from SharedPreferences and sets them in the model
     */
    private void loadExerciseDataFromSharedPreferences() {
        JacksonSerializationService s = new JacksonSerializationService(getApplication().getApplicationContext());

        ArrayList<IExercise> iExerciseArrayList = new ArrayList<>();

        try {
            iExerciseArrayList = s.loadUserExerciseList();

            if (!iExerciseArrayList.isEmpty()) {
                model.setExercises(iExerciseArrayList);
            }
        }

        catch (NullPointerException e){
            e.printStackTrace();
            saveExerciseData(model.getExercises());
        }

    }

    /**
     * Loads Exercises from SharedPreferences and sets them in the model
     */
    private void loadWorkoutDataFromSharedPreferences() {
        JacksonSerializationService s = new JacksonSerializationService(getApplication().getApplicationContext());

        ArrayList<IWorkout> iWorkoutArrayList = new ArrayList<>();

        try {
            iWorkoutArrayList = s.loadUserWorkoutList();

            if (!iWorkoutArrayList.isEmpty()) {
                model.setWorkouts(iWorkoutArrayList);
            }
        }

        catch (NullPointerException e){
            e.printStackTrace();
            saveExerciseData(model.getExercises());
        }

    }


    /**
     * Loads Workouts from SharedPreferences and sets them in the model
     */

    /*
    private void loadWorkoutDataFromSharedPreferences(){
        //SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        //model.setWorkouts(s.loadUserWorkoutList());
        JacksonSerializationService s = new JacksonSerializationService(getApplication().getApplicationContext());
        model.setWorkouts(s.loadUserWorkoutList());
        loadChallenges();
    }


    private void loadUserDataFromSharedPreferences(){
        SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        //model.setUser(s.loadUserData());
    }
    */

    /**
     * Loads Exercises and Workouts from SharedPreferences
     */
    private void loadData(){
        //loadUserDataFromSharedPreferences();
        loadExerciseDataFromSharedPreferences();
        loadWorkoutDataFromSharedPreferences();
    }

    /**
     * Saves Exercises and Workouts from SharedPreferences
     */
    public void saveData(){
        System.out.println("Saving model data to SharedPreferences....");
        saveExerciseData(model.getExercises());
        saveWorkoutData(model.getWorkouts());
        //saveUserData(model.getUser());
        System.out.println("Saved all available model data");
    }


    /**
     * Loads a list of base exercises from an xml file into the TrainingTracker.
     */
    private void loadExercises() {
        System.out.println("Loading exercises...");
        ReadExercisesFromXMLService reader = new ReadExercisesFromXMLService();
        model.loadBaseExercises(reader.readExercises());
    }

    /**
     * Loads a list of base workouts from an xml file into the TrainingTracker.
     * Exercises need to have been loaded first.
     */
    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(model.getExercises());
        model.loadBaseWorkouts(reader.readWorkouts());
    }

    /**
     * Creates a challenge for each exercise whose name is specified in the challenges.txt file and loads them as a list into the TrainingTracker.
     * Exercises need to have been loaded first.
     */
    private void loadChallenges() {
        List<IChallenge> challenges = new ArrayList<>();
        // Create an exercise map with the exercises' names as keys to easier reach exercises by name
        Map<String, IExercise> exerciseMap = new HashMap<>();
        for (IExercise e : model.getExercises()) {
            exerciseMap.put(e.getName().toLowerCase(), e);
        }
        ReadLinesFromFileService reader = new ReadLinesFromFileService("res/raw/challenges.txt", "UTF-8");
        for (String line : reader.readLines()) {
            if (exerciseMap.containsKey(line.toLowerCase()))
                challenges.add(new Challenge(exerciseMap.get(line)));
        }

        model.loadBaseChallenges(challenges);
    }

    public ITrainingTracker getModel() {
        return model;
    }
}
