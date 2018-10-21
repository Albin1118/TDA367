package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private ITrainingTracker model;

    public MainViewModel() {
        model = new TrainingTracker();
        loadExercises();
        loadWorkouts();
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

    public void loadUserCustomListsFromJson(String customWorkoutListJson, String customExerciseListJson){
        Gson gson = new Gson();

        Type workoutListType = new TypeToken<ArrayList<IWorkout>>(){}.getType();
        Type exerciseListType = new TypeToken<ArrayList<IExercise>>(){}.getType();

        List <IWorkout> w = gson.fromJson(customWorkoutListJson, workoutListType);
        List <IExercise> e = gson.fromJson(customExerciseListJson, exerciseListType);

        if (w == null){
            w = new ArrayList<>();
        }

        if (e == null){
            e = new ArrayList<>();
        }

        setCustomWorkouts(w);
        setCustomExercises(e);

    }

    public ITrainingTracker getModel() {
        return model;
    }
}
