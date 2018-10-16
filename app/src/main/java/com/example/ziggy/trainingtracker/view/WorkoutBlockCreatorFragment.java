package com.example.ziggy.trainingtracker.view;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
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
    private Button previewButton;
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
        previewButton = view.findViewById(R.id.previewButton);
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

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the current WorkoutBlock
                WorkoutBlock w = buildWorkoutBlock();

                //Create the dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Preview");

                //Create a ListView to be displayed in the dialog
                final ListView workoutBlockListView = new ListView(getContext());

                List<WorkoutBlock>blockList = new ArrayList<>();
                blockList.add(w);
                WorkoutBlockListAdapter adapter = new WorkoutBlockListAdapter(getContext(), blockList);

                workoutBlockListView.setAdapter(adapter);

                builder.setView(workoutBlockListView);

                // Set up the button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkoutBlock workoutBlock = buildWorkoutBlock();

                parentActivity.viewModel.buildWorkout.addBlock(workoutBlock);
                parentActivity.popBackStack();
            }
        });


    }

    private WorkoutBlock buildWorkoutBlock(){

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

        return workoutBlock;
    }
}
