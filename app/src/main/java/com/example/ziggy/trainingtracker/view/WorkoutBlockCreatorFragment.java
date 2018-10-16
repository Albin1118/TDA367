package com.example.ziggy.trainingtracker.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockCreatorFragment extends Fragment {

    private List<Exercise> exercises;

    private Button decrementSetButton;
    private TextView setsDisplay;
    private Button incrementSetButton;
    private ListView selectExerciseListView;
    private Button addWorkoutBlockButton;

    private View view;
    private MainActivity parentActivity;
    private ArrayAdapter<Exercise> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_workout_block_creator, container, false);
        exercises = parentActivity.viewModel.getExercises();

        initViews();
        initListeners();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, exercises);
        selectExerciseListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        selectExerciseListView.setAdapter(adapter);


        return view;
    }

    private void initViews() {
        decrementSetButton = view.findViewById(R.id.decrementSetButton);
        setsDisplay = view.findViewById(R.id.setsdisplay);
        incrementSetButton = view.findViewById(R.id.incrementSetButton);
        selectExerciseListView = view.findViewById(R.id.selectExerciseListView);
        addWorkoutBlockButton = view.findViewById(R.id.addWorkoutBlockButton);
    }

    private void initListeners() {

        decrementSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                if(sets > 1){
                    sets--;
                }
                setsDisplay.setText(String.valueOf(sets));
            }
        });

        incrementSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                sets++;
                setsDisplay.setText(String.valueOf(sets));
            }
        });

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int [] checkedItemPositions = selectExerciseListView.getCheckedItemPositions();
                //List<Exercise> checkedExercises = new ArrayList<>();
                WorkoutBlock workoutBlock = new WorkoutBlock();

                int sets = Integer.parseInt(setsDisplay.getText().toString());
                workoutBlock.setMultiplier(sets);

                SparseBooleanArray checkedItems = selectExerciseListView.getCheckedItemPositions();

                if (checkedItems != null) {
                    for (int i=0; i<checkedItems.size(); i++) {
                        if (checkedItems.valueAt(i)) {
                            //Exercise checkedExercise = (Exercise) selectExerciseListView.getAdapter().getItem(i);
                            int position = checkedItems.keyAt(i);
                            workoutBlock.addExercise(exercises.get(position), 1);
                        }
                    }
                }

                parentActivity.viewModel.buildWorkout.addBlock(workoutBlock);
                parentActivity.popBackStack();
            }
        });
    }
}
