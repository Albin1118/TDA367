package com.example.ziggy.trainingtracker.view;

public class CategorySpinnerObject {
    private String categoryName;
    private boolean categorySelected = false;

    CategorySpinnerObject(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(boolean categorySelected) {
        this.categorySelected = categorySelected;
    }
}
