package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;

import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.service.UserDataPersistenceService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    private List<Exercise> exercises;
    private List<Workout> workouts;

    private List<Exercise> allExercises;
    private List<Workout> allWorkouts;

    private boolean activeWorkoutStatus;

    List<WorkoutBlock>workoutBlocks= new ArrayList<>();

    public Workout buildWorkout;
    private List<ExerciseCategory> categories = new ArrayList<>();


    public MainViewModel() {
        trainingTracker = new TrainingTracker();
        activeWorkoutStatus = false;
        createCategoryList();
    }


    public boolean checkActiveWorkoutStatus() {
        return activeWorkoutStatus;
    }

    public void setActiveWorkoutStatus(boolean activeWorkoutStatus) {
        this.activeWorkoutStatus = activeWorkoutStatus;
    }

    public List<Exercise> getExercises() {
        if (exercises == null) {
            exercises = trainingTracker.getExercises();
            loadExercises();
        }
        return exercises;
    }

    public List<Workout> getWorkouts() {
        if (workouts == null) {
            workouts = trainingTracker.getWorkouts();
            loadWorkouts();
        }
        return workouts;
    }

    private void initAllExercises() {
        allExercises = getCustomExercises();

        if (exercises == null) {
            exercises = trainingTracker.getExercises();
            loadExercises();
        }

        allExercises.addAll(exercises);

    }

    private void initAllWorkouts() {
        allWorkouts = getCustomWorkouts();

        if (workouts == null) {
            workouts = trainingTracker.getWorkouts();
            loadWorkouts();
        }
        allWorkouts.addAll(workouts);
    }


    public List<Exercise> getCustomExercises() {
        return trainingTracker.getCustomExercises();
    }

    public List<Workout> getCustomWorkouts() {
        return trainingTracker.getCustomWorkouts();
    }

    public List<ExerciseCategory> getCategories() { return categories; }

    public ArrayList<String> getCategoriesToString() {
        ArrayList<String> categoriesToString = new ArrayList<>();
        for(int i=0; i<getCategories().size(); i++) {
            categoriesToString.add(getCategories().get(i).name());
        }
        return categoriesToString;
    }

    private void setCustomWorkouts(List<Workout> w){
        trainingTracker.setCustomWorkouts(w);
    }

    private void setCustomExercises(List<Exercise> e){
        trainingTracker.setCustomExercises(e);
    }

    public List<Exercise> getAllExercises() {
        if (allExercises == null){
            initAllExercises();
        }
        return allExercises;
    }

    public List<Workout> getAllWorkouts() {
        if (allWorkouts == null) {
            initAllWorkouts();
        }
        return allWorkouts;
    }

    public void loadUserCustomListsFromJson(String customWorkoutListJson, String customExerciseListJson){
        Gson gson = new Gson();

        Type workoutListType = new TypeToken<ArrayList<Workout>>(){}.getType();
        Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();

        List <Workout> w = gson.fromJson(customWorkoutListJson, workoutListType);
        List <Exercise> e = gson.fromJson(customExerciseListJson, exerciseListType);

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
    public void addCustomExercise(String name, String description, String instructions, String unit,List<ExerciseCategory> categories) {
        Exercise e = new Exercise(name, description, instructions, unit, categories);
        trainingTracker.addCustomExercise(e);
    }

    public void removeCustomExercise(Exercise e) {
        trainingTracker.removeCustomExercise(e);
    }

    public  void editCustomExercise(Exercise e, String name, String description, String instructions, String unit) {
        e.setName(name);
        e.setDescription(description);
        e.setInstructions(instructions);
        e.setUnit(unit);
    }

    //Method for adding removing and editing custom Workouts
    public void addCustomWorkout(Workout w){ trainingTracker.addCustomWorkout(w); }

    public void addCustomWorkout(String name, String description, List<WorkoutBlock> blocks){
        Workout w = new Workout(name, description, new ArrayList<>(blocks));
        trainingTracker.addCustomWorkout(w);
    }

    public void removeCustomWorkout(Workout w) { trainingTracker.removeCustomWorkout(w); }

    public void editCustomWorkout(Workout w, String name, String description, List<WorkoutBlock> blocks) {
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
        trainingTracker.getExercises().addAll(reader.readExercises());
    }

    /**
     * Loads a list of base workouts from an xml file into the TrainingTracker.
     */
    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(getExercises());
        trainingTracker.getWorkouts().addAll(reader.readWorkouts());
    }

    public void updateUserWeight(double weight){
        trainingTracker.getUser().setWeight(weight);
    }

    public void setBuildWorkout(Workout buildWorkout) {
        this.buildWorkout = buildWorkout;
    }

    private void createCategoryList() {
        categories.add(ExerciseCategory.ARMS );
        categories.add(ExerciseCategory.LEGS );
        categories.add(ExerciseCategory.BACK );
        categories.add(ExerciseCategory.CHEST);
        categories.add(ExerciseCategory.ABS  );
    }
}
