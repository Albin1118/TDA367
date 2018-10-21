package com.example.ziggy.trainingtracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.service.SharedPreferencesService;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ITrainingTracker model;

    public MainViewModel(Application app) {
        super(app);
        model = new TrainingTracker();
        loadExercises();
        loadWorkouts();
 //       loadData();

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
