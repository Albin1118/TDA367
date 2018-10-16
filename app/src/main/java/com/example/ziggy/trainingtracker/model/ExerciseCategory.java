package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;

public class ExerciseCategory {

    enum Categories {
        ARMS, CHEST, ABS, BACK, LEGS
    }

    private ArrayList<String> allCategoriesToString = new ArrayList<>();
    private ArrayList<Enum> allCategories = new ArrayList<>();

    public ExerciseCategory() {
        createCategories();
    }

    private void createCategories() {
        createCategory(Categories.ARMS, "Arms");
        createCategory(Categories.CHEST, "Chest");
        createCategory(Categories.ABS, "Abs");
        createCategory(Categories.BACK, "Back");
        createCategory(Categories.LEGS, "Legs");

    }

    private void createCategory(Enum category, String categoryName) {
        allCategories.add(category);
        allCategoriesToString.add(categoryName);
    }

    public ArrayList<String> getAllCategoriesToString() {
        return allCategoriesToString;
    }
}
