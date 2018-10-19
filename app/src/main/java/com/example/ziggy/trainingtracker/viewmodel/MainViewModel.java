package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    private ITrainingTracker model;

    private List<IExercise> exercises;
    private List<IWorkout> workouts;

    private List<IExercise> allExercises;
    private List<IWorkout> allWorkouts;


    private boolean activeWorkoutStatus;

    List<WorkoutBlock>workoutBlocks= new ArrayList<>();

    public IWorkout buildWorkout;
    private List<ExerciseCategory> categories = new ArrayList<>();


    public MainViewModel() {
        model = new TrainingTracker();
        activeWorkoutStatus = false;
        createCategoryList();
    }


    public boolean checkActiveWorkoutStatus() {
        return activeWorkoutStatus;
    }

    public void setActiveWorkoutStatus(boolean activeWorkoutStatus) {
        this.activeWorkoutStatus = activeWorkoutStatus;
    }

    public List<IExercise> getExercises() {
        if (exercises == null) {
            exercises = model.getExercises();
            loadExercises();
        }
        return exercises;
    }

    public List<IWorkout> getWorkouts() {
        if (workouts == null) {
            workouts = model.getWorkouts();
            loadWorkouts();
        }
        return workouts;
    }

    private void initAllExercises() {
        allExercises = getCustomExercises();

        if (exercises == null) {
            exercises = model.getExercises();
            loadExercises();
        }

        allExercises.addAll(exercises);

    }

    private void initAllWorkouts() {
        allWorkouts = getCustomWorkouts();

        if (workouts == null) {
            workouts = model.getWorkouts();
            loadWorkouts();
        }
        allWorkouts.addAll(workouts);
    }


    public List<IExercise> getCustomExercises() {
        return model.getCustomExercises();
    }

    public List<IWorkout> getCustomWorkouts() {
        return model.getCustomWorkouts();
    }

    public List<ExerciseCategory> getCategories() { return categories; }

    public List<String> getCategoriesToString() {
        List<String> categoriesToString = new ArrayList<>();
        for(int i=0; i<getCategories().size(); i++) {
            categoriesToString.add(getCategories().get(i).name());
        }
        return categoriesToString;
    }

    private void setCustomWorkouts(List<IWorkout> w){
        model.setCustomWorkouts(w);
    }

    private void setCustomExercises(List<IExercise> e){
        model.setCustomExercises(e);
    }

    public List<IExercise> getAllExercises() {
        if (allExercises == null){
            initAllExercises();
        }
        return allExercises;
    }

    public List<IWorkout> getAllWorkouts() {
        if (allWorkouts == null) {
            initAllWorkouts();
        }
        return allWorkouts;
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



    // Methods for adding removing and editing custom Exercises
    public void addCustomExercise(IExercise exercise) {
        model.addCustomExercise(exercise);
    }

    public void removeCustomExercise(IExercise e) {
        model.removeCustomExercise(e);
    }

    public  void editCustomExercise(IExercise originalExercise, IExercise newExercise) {
        originalExercise.setName(newExercise.getName());
        originalExercise.setDescription(newExercise.getDescription());
        originalExercise.setInstructions(newExercise.getInstructions());
        originalExercise.setUnit(newExercise.getUnit());
        originalExercise.setCategories(newExercise.getCategories());
    }

    //Method for adding removing and editing custom Workouts
    public void addCustomWorkout(IWorkout w){ model.addCustomWorkout(w); }

    public void addCustomWorkout(String name, String description, List<WorkoutBlock> blocks){
        IWorkout w = new Workout(name, description, new ArrayList<>(blocks));
        model.addCustomWorkout(w);
    }

    public void removeCustomWorkout(IWorkout w) { model.removeCustomWorkout(w); }

    public void editCustomWorkout(IWorkout w, String name, String description, List<WorkoutBlock> blocks) {
        w.setName(name);
        w.setDescription(description);
        w.setBlocks(blocks);
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
     */
    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(getExercises());
        model.getWorkouts().addAll(reader.readWorkouts());
    }

    public void updateUserWeight(double weight){
        model.getUser().setWeight(weight);
    }

    public void setBuildWorkout(IWorkout buildWorkout) {
        this.buildWorkout = buildWorkout;
    }

    private void createCategoryList() {
        categories.addAll(Arrays.asList(ExerciseCategory.values()));
    }

    public ITrainingTracker getModel() {
        return model;
    }
}
