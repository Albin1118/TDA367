package com.example.ziggy.trainingtracker.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;

public class CreateExerciseFragment extends Fragment {


    private Button createExerciseButton;
    private TextView testTextView;
    private EditText exerciseNameEditText;
    private EditText exerciseDescriptionEditText;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_create_exercise, container, false);

        createExerciseButton = (Button) view.findViewById(R.id.createExerciseButton);
        testTextView = (TextView) view.findViewById(R.id.testTextView);
        exerciseNameEditText = (EditText) view.findViewById(R.id.exerciseNameEditText);
        exerciseDescriptionEditText = (EditText) view.findViewById(R.id.exerciseDescriptionEditText);


        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) view.findViewById(R.id.exerciseNameEditText);
                EditText description = (EditText) view.findViewById(R.id.exerciseDescriptionEditText);
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        return view;
    }
}
