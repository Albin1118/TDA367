package com.example.ziggy.trainingtracker.view;

class CategorySpinnerObject {
    private String categoryName;
    private boolean categorySelected = false;

    CategorySpinnerObject(String categoryName) {
        this.categoryName = categoryName;
    }

    String getCategoryName() {
        return categoryName;
    }

    boolean isCategorySelected() {
        return categorySelected;
    }

    void setCategorySelected(boolean categorySelected) {
        this.categorySelected = categorySelected;
    }
}
