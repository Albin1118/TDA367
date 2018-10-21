package com.example.ziggy.trainingtracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.ziggy.trainingtracker.model.Challenge;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadLinesFromFileService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.service.SharedPreferencesService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        //loadData();
    }


    private void saveExerciseData(List<IExercise> list){
        SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        s.saveExerciseDataToSharedPreferences(list);
    }

    private void saveWorkoutData(List<IWorkout> list){
        SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        s.saveWorkoutDataToSharedPreferences(list);
    }

    private void loadExerciseDataFromSharedPreferences(){
        SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        model.setExercises(s.loadUserExerciseList());
    }

    private void loadWorkoutDataFromSharedPreferences(){
        SharedPreferencesService s = new SharedPreferencesService(getApplication().getApplicationContext());
        model.setWorkouts(s.loadUserWorkoutList());
        loadChallenges();
    }

    private void loadData(){
        loadExerciseDataFromSharedPreferences();
        loadWorkoutDataFromSharedPreferences();
    }

    public void saveData(){
        saveExerciseData(model.getExercises());
        saveWorkoutData(model.getWorkouts());
    }


    /**
     * Loads a list of base exercises from an xml file into the TrainingTracker.
     */
    private void loadExercises() {
        System.out.println("Loading exercises...");
        ReadExercisesFromXMLService reader = new ReadExercisesFromXMLService();
        model.getExercises().addAll(reader.readExercises());
    }

    /**
     * Loads a list of base workouts from an xml file into the TrainingTracker.
     * Exercises need to have been loaded first.
     */
    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(model.getExercises());
        model.getWorkouts().addAll(reader.readWorkouts());
    }

    private void loadChallenges() {
        List<IChallenge> challenges = new ArrayList<>();
        Map<String, IExercise> exerciseMap = new LinkedHashMap<>();
        for (IExercise e : model.getExercises()) {
            exerciseMap.put(e.getName(), e);
        }
        ReadLinesFromFileService reader = new ReadLinesFromFileService("res/raw/challenges.txt", "UTF-8");
        for (String line : reader.readLines()) {
            if (exerciseMap.containsKey(line))
                challenges.add(new Challenge(exerciseMap.get(line)));
        }

        model.getChallenges().addAll(challenges);
    }

    public List<IExercise> getCustomExercises() {
        return model.getCustomExercises();
    }

    public List<IWorkout> getCustomWorkouts() {
        return model.getCustomWorkouts();
    }

    private void setCustomWorkouts(List<IWorkout> w){
        model.setCustomWorkouts(w);
    }

    private void setCustomExercises(List<IExercise> e){
        model.setCustomExercises(e);
    }

    public ITrainingTracker getModel() {
        return model;
    }
}
