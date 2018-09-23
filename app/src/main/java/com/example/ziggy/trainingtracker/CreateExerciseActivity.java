package com.example.ziggy.trainingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateExerciseActivity extends AppCompatActivity {

    public ArrayList<Exercise> customExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        final Button createExerciseButton = (Button) findViewById(R.id.createExerciseButton);
        final TextView testTextView = (TextView) findViewById(R.id.testTextView);
        final EditText exerciseNameEditText = (EditText) findViewById(R.id.exerciseNameEditText);
        final EditText exerciseDescriptionEditText = (EditText) findViewById(R.id.exerciseDescriptionEditText);

        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.exerciseNameEditText);
                EditText description = (EditText) findViewById(R.id.exerciseDescriptionEditText);
                createExercise(name.getText().toString(), description.getText().toString());

                testTextView.setText(customExercises.get(0).getName());
                finish();
            }
        });

    }

    public void createExercise(String name, String description) {
        customExercises.add(new Exercise(name, description));
    }

}
