package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing an exercise, with a name, unit, description...
 */
public class Exercise implements IExercise {

    @SerializedName("exercise_name")
    private String name;
    @SerializedName("exercise_unit")
    private String unit;
    @SerializedName("exercise_description")
    private String description;
    @SerializedName("exercise_instructions")
    private String instructions;
    @SerializedName("exercise_has_weight")
    private boolean weightBased;
    @SerializedName("exercise_category")
    private List<ExerciseCategory> categories;


    public Exercise(String name, String unit, String description, String instructions, List<ExerciseCategory> categories, boolean weightBased) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.unit = unit;
        this.categories = categories;
        this.weightBased = weightBased;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return the instructions
     */
    @Override
    public String getInstructions() {
        return instructions;
    }

    /**
     * @return the unit (e.g. reps, meters, seconds)
     */
    @Override
    public String getUnit() {
        return unit;
    }

    /**
     * @return the exercise's muscle group categories
     */
    @Override
    public List<ExerciseCategory> getCategories() {
        return categories;
    }

    /**
     * @return true if the exercise is weight based
     */
    @Override
    public boolean isWeightBased() { return weightBased; }

    /**
     * @return the categories in string format
     */
    @Override
    public String getCategoriesString(){
        String categoriesString = "";

        for (int i = 0; i < categories.size(); i++){
            if( i == 0 ){
                categoriesString += categories.get(i).name();
            }else{
                categoriesString += " , " + categories.get(i).name();
            }
        }

        return categoriesString;
    }

    /**
     * @param name Name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description Description to set
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param instructions Instructions to set
     */
    @Override
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * @param unit Unit to set (e.g. reps, meters, seconds)
     */
    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @param categories Muscle group categories to set
     */
    @Override
    public void setCategories(List<ExerciseCategory> categories) {
        this.categories = categories;
    }

    /**
     * @param weightBased Set if the exercise is weight based
     */
    @Override
    public void setWeightBased(boolean weightBased) { this.weightBased = weightBased; }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return weightBased == exercise.weightBased &&
                Objects.equals(name, exercise.name) &&
                Objects.equals(unit, exercise.unit) &&
                Objects.equals(description, exercise.description) &&
                Objects.equals(instructions, exercise.instructions) &&
                Objects.equals(categories, exercise.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, description, instructions, weightBased, categories);
    }
}
