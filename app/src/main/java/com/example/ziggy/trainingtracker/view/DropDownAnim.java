package com.example.ziggy.trainingtracker.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class DropDownAnim extends Animation {
    private final View view;
    private final int startingHeight;
    private final int targetHeight;

    DropDownAnim(View view, int targetHeight) {
        this.view = view;
        this.startingHeight = view.getHeight();
        this.targetHeight = targetHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        newHeight = (int) ((targetHeight - startingHeight) * interpolatedTime + startingHeight);
        if (newHeight == 0) {
            view.setVisibility(View.GONE);
        } else {
            view.getLayoutParams().height = newHeight;
            view.setVisibility(View.VISIBLE);
        }
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
