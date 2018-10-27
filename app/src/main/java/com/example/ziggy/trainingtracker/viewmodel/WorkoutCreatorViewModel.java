package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutCreatorViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout buildWorkout;
    private IWorkout editableWorkout = null;
    private boolean editMode;

    private List<IExercise> blockExercises = new ArrayList<>();
    private List<Integer> blockAmounts = new ArrayList<>();
    private List<Double> blockWeights = new ArrayList<>();

    /**
     * Init for creating mode.
     * @param model The model to work on
     */
    public void init(ITrainingTracker model) {
        this.model = model;
        editMode = false;
        buildWorkout = new Workout("", "", new ArrayList<>());
    }

    /**
     * Init for editing mode.
     * @param model The model to work on
     * @param editableWorkout The workout to edit
     */
    public void init(ITrainingTracker model, IWorkout editableWorkout) {
        this.model = model;
        this.editableWorkout = editableWorkout;
        editMode = true;

        String name = editableWorkout.getName();
        String description = editableWorkout.getDescription();
        List<IWorkoutBlock> blocks = new ArrayList<>(editableWorkout.getBlocks());
        buildWorkout = new Workout(name, description, blocks);
    }

    /**
     * Adds the built workout to the list of custom workouts.
     */
    public void createWorkout() {
        model.addCustomWorkout(buildWorkout);
    }

    /**
     * Saves the editable workout by getting the values from the built workout.
     */
    public void saveWorkout() {
        editableWorkout.setName(buildWorkout.getName());
        editableWorkout.setDescription(buildWorkout.getDescription());
        editableWorkout.setBlocks(buildWorkout.getBlocks());
    }

    /**
     * Stores the exercise coupled with its amount (e.g. amount of reps) in the workout block builder lists.
     * @param exercise The exercise to store
     * @param amount The amount associated with the exercise (e.g. 10 reps)
     */
    public void addExercise(IExercise exercise, int amount) {
        this.blockExercises.add(exercise);
        this.blockAmounts.add(amount);
    }


    public void addExercise(IExercise exercise, int amount, double weight) {
        this.blockExercises.add(exercise);
        this.blockAmounts.add(amount);
        this.blockWeights.add(weight);
    }

    /**
     * Removes an exercise and its amount from the workout block builder lists.
     * @param exercise
     */
    public void removeExercise(IExercise exercise) {
        int index = this.blockExercises.indexOf(exercise);
        this.blockExercises.remove(index);
        this.blockAmounts.remove(index);
    }

    public boolean workoutBlockIsEmpty() {
        return this.blockExercises.isEmpty();
    }

    public void addWorkoutBlock(int sets) {
        buildWorkout.addBlock(sets, blockExercises, blockAmounts);
    }

    public void removeLatestBlock(){
        int index = buildWorkout.getNumberOfBlocks() - 1;
        buildWorkout.removeBlock(index);
    }

    public IWorkoutBlock getLatestBlock(){
        int index = buildWorkout.getNumberOfBlocks() - 1;
        IWorkoutBlock block = buildWorkout.getBlocks().get(index);
        return block;
    }

    public void resetWorkoutBlock() {
        blockExercises = new ArrayList<>();
        blockAmounts = new ArrayList<>();
    }

    /**
     * Checks if editing an existing workout or creating a new one.
     * @return True if in edit mode, false if in create mode
     */
    public boolean isEditMode() {
        return editMode;
    }

    /**
     * Returns the workout used to keep the state of the workout being dynamically built.
     * @return The workout being built
     */
    public IWorkout getBuildWorkout() {
        return buildWorkout;
    }

    public String getBuildWorkoutName(){
        return buildWorkout.getName();
    }

    public String getBuildWorkoutDescription(){
        return buildWorkout.getDescription();
    }

    public List<IWorkoutBlock> getBuildWorkoutBlocks(){
        return buildWorkout.getBlocks();
    }

    public void setBuildWorkoutName(String name){
        buildWorkout.setName(name);
    }

    public void setBuildWorkoutDescription(String description){
        buildWorkout.setDescription(description);
    }

    /**
     * Returns all exercises from the model.
     * @return A list of all the exercises
     */
    public List<IExercise> getExercises() {
        return model.getExercises();
    }

    public IExercise getExercise(int index){
        return model.getExercises().get(index);
    }
}
