package com.example.ziggy.trainingtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExerciseActivity extends AppCompatActivity {

    CreateExerciseActivity createExerciseActivity = new CreateExerciseActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Button addExerciseButton = (Button) findViewById(R.id.addExerciseButton);
        Button exerciseBackButton = (Button) findViewById(R.id.exerciseBackButton);

        exerciseBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, createExerciseActivity.getClass()));
            }
        });

    }
}
