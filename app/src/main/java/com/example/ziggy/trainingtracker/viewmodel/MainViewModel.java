package com.example.ziggy.trainingtracker.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import com.example.ziggy.trainingtracker.service.InterfaceAdapter;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.service.SharedPreferencesService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ITrainingTracker model;

    public MainViewModel(Application app) {
        super(app);
        model = new TrainingTracker();
        loadExercises();
        loadWorkouts();

    }


    private void saveExerciseData(List<IExercise> list){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(getApplication().getApplicationContext());
        sharedPreferencesService.saveExerciseDataToSharedPreferences(list);
    }

    private void saveWorkoutData(List<IWorkout> list){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(getApplication().getApplicationContext());
        sharedPreferencesService.saveWorkoutDataToSharedPreferences(list);
    }

    private String loadExerciseData(){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(getApplication().getApplicationContext());
        String jsonData = sharedPreferencesService.loadExerciseDataFromSharedPreferences();
        System.out.println(jsonData);

        return jsonData;
    }

    private String loadWorkoutData(){
        SharedPreferencesService sharedPreferencesService = new SharedPreferencesService(getApplication().getApplicationContext());
        String jsonData = sharedPreferencesService.loadWorkoutDataFromSharedPreferences();
        System.out.println(jsonData);

        return jsonData;
    }

    private void loadUserExerciseList(){
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(IExercise.class, new InterfaceAdapter<IExercise>())
                .create();

        Type exerciseListType = new TypeToken<ArrayList<IExercise>>(){}.getType();

        ArrayList <IExercise> exerciseList = gson.fromJson(loadExerciseData(), exerciseListType);

        if (exerciseList == null){
            exerciseList = new ArrayList<>();
        }

        //setCustomExercises(convertToIExercise(exerciseList));
    }


    private void loadUserWorkoutList(){
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(IWorkout.class, new InterfaceAdapter<IWorkout>())
                .setPrettyPrinting()
                .create();

        //Gson gson = new Gson();

        Type workoutListType = new TypeToken<ArrayList<IWorkout>>(){}.getType();

        ArrayList <IWorkout> workoutList = gson.fromJson(loadWorkoutData(), workoutListType);

        if (workoutList == null){
            workoutList = new ArrayList<>();
        }

        //setCustomWorkouts(convertToIWorkout(workoutList));
    }

    /*
    private List<IWorkout> convertToIWorkout(List<Workout> w){
        ArrayList<IWorkout> iWorkouts = new ArrayList<>(w);

        return iWorkouts;
    }

    private List<IExercise> convertToIExercise(List<Exercise> e){
        ArrayList<IExercise> iExercises = new ArrayList<>(e);

        return iExercises;
    }

    */

    public void loadData(){
        loadUserExerciseList();
        loadUserWorkoutList();
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
