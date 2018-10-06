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
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;

/**
 * Fragment representing a view where the user can create custom exercises
 */
public class ExerciseCreatorFragment extends Fragment {


    private Button createExerciseButton;
    private TextView testTextView;
    private EditText exerciseNameEditText;
    private EditText exerciseUnitEditText;
    private EditText exerciseDescriptionEditText;
    private EditText exerciseInstructionsEditText;

    private MainActivity parentActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity =  ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_exercise_creator, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        testTextView = view.findViewById(R.id.testTextView);
        exerciseNameEditText = view.findViewById(R.id.exerciseNameEditText);
        exerciseUnitEditText = view.findViewById(R.id.exerciseUnitEditText);
        exerciseDescriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
        exerciseInstructionsEditText = view.findViewById(R.id.exerciseInstructionsEditText);
    }

    private void initListeners() {
        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExercise();
                parentActivity.setFragmentContainerContent(new ExerciseTabFragment());
                Toast.makeText(getContext(), "New exercise created!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        parentActivity.viewModel.addCustomExercise(name, description, instructions, unit);
    }
}
