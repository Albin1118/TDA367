package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment representing a view where the user can create custom workouts
 */
public class WorkoutCreatorFragment extends Fragment {

    private EditText workoutNameEditText;
    private Button workoutDescriptionButton;
    private ConstraintLayout workoutDescriptionLayout;
    private EditText workoutDescriptionEditText;
    private Button addWorkoutBlockButton;
    private ListView workoutBlocksListView;
    private Button createWorkoutButton;

    private Button saveEditedWorkoutButton;
    private Button cancelEditedWorkoutButton;


    private ArrayAdapter<WorkoutBlock> adapter;
    private MainActivity parentActivity;
    private View view;
    private View header;
    private View footer;
    private boolean descriptionClosed = true;


    private Workout editableWorkout = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = (MainActivity)getActivity();
        view  = inflater.inflate(R.layout.fragment_workout_creator, container, false);
        header = inflater.inflate(R.layout.workout_block_list_header, null);
        footer = inflater.inflate(R.layout.workout_block_list_footer, null);
        initViews();
        initListeners();


        if(editableWorkout != null) {
            Workout w = new Workout(editableWorkout.getName(), editableWorkout.getDescription(), editableWorkout.getBlocks());

            parentActivity.viewModel.setBuildWorkout(w);
            editMode();
            adapter = new WorkoutBlockListAdapter(getContext(), editableWorkout.getBlocks());
        } else {
            adapter = new WorkoutBlockListAdapter(getContext(), parentActivity.viewModel.getBuildWorkout().getBlocks());
            workoutNameEditText.setText(parentActivity.viewModel.getBuildWorkout().getName());
            workoutDescriptionEditText.setText(parentActivity.viewModel.getBuildWorkout().getDescription());
        }
        workoutBlocksListView.setAdapter(adapter);

        return view;
    }

    private void initViews() {
        workoutNameEditText = header.findViewById(R.id.workoutNameEditText);
        workoutDescriptionButton = header.findViewById(R.id.workoutDescriptionButton);
        workoutDescriptionLayout = header.findViewById(R.id.workoutDescriptionLayout);
        workoutDescriptionEditText = header.findViewById(R.id.workoutDescriptionEditText);
        addWorkoutBlockButton = footer.findViewById(R.id.addWorkoutBlockButton);
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        createWorkoutButton = view.findViewById(R.id.createWorkoutButton);
        saveEditedWorkoutButton = view.findViewById(R.id.saveEditedWorkoutButton);
        cancelEditedWorkoutButton = view.findViewById(R.id.cancelEditedWorkoutButton);
        workoutBlocksListView.addHeaderView(header);
        workoutBlocksListView.addFooterView(footer);
    }

    private void initListeners() {
        workoutDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropDownAnim a;
                if (descriptionClosed) {
                    a = new DropDownAnim(workoutDescriptionLayout, workoutDescriptionEditText.getLayoutParams().height + 40);
                } else {
                    a = new DropDownAnim(workoutDescriptionLayout, 0);
                } descriptionClosed = !descriptionClosed;
                a.setDuration(300);
                workoutDescriptionLayout.startAnimation(a);
            }
        });

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutBlockCreatorFragment fragment = new WorkoutBlockCreatorFragment();
                parentActivity.viewModel.getBuildWorkout().setName(workoutNameEditText.getText().toString());
                parentActivity.viewModel.getBuildWorkout().setDescription(workoutDescriptionEditText.getText().toString());
                fragment.setEditableWorkout(editableWorkout);
                parentActivity.setFragmentContainerContent(fragment);
            }
        });

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = view.findViewById(R.id.workoutNameEditText);
                EditText descriptionEditText = view.findViewById(R.id.workoutDescriptionEditText);

                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                parentActivity.viewModel.addCustomWorkout(name, description, parentActivity.viewModel.getBuildWorkout().getBlocks());

                parentActivity.setFragmentContainerContent(new WorkoutTabFragment());
                Toast.makeText(getContext(), "New workout created!", Toast.LENGTH_SHORT).show();
            }
        });
        saveEditedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = workoutNameEditText.getText().toString();
                String description = workoutDescriptionEditText.getText().toString();

                parentActivity.viewModel.editCustomWorkout(editableWorkout, name, description, parentActivity.viewModel.getBuildWorkout().getBlocks());
                WorkoutDetailViewFragment fragment = new WorkoutDetailViewFragment();
                fragment.setWorkout(editableWorkout);
                parentActivity.setFragmentContainerContent(fragment);
            }
        });

        cancelEditedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutDetailViewFragment fragment = new WorkoutDetailViewFragment();
                fragment.setWorkout(editableWorkout);
                parentActivity.setFragmentContainerContent(fragment);
            }
        });

    }

    public void editMode() {
        saveEditedWorkoutButton.setVisibility(View.VISIBLE);
        cancelEditedWorkoutButton.setVisibility(View.VISIBLE);
        createWorkoutButton.setVisibility(View.GONE);
        workoutNameEditText.setText(parentActivity.viewModel.getBuildWorkout().getName());
        workoutDescriptionEditText.setText(parentActivity.viewModel.getBuildWorkout().getDescription());
    }

    public void setEditableWorkout(Workout editableWorkout) {
        this.editableWorkout = editableWorkout;
    }

}
