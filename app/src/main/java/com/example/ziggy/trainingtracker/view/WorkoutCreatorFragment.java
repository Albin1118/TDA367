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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity)getActivity();
        initBuildWorkout();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_creator, container, false);
        initViews();
        initListeners();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveBuildWorkout();
    }

    private void initBuildWorkout() {
        if (editableWorkout == null) {
            parentActivity.viewModel.buildWorkout = new Workout("", "", new ArrayList<>());
        } else {
            String name = editableWorkout.getName();
            String description = editableWorkout.getDescription();
            List<WorkoutBlock> blocks = new ArrayList<>(editableWorkout.getBlocks());
            parentActivity.viewModel.buildWorkout = new Workout(name, description, blocks);
        }
    }

    private void initViews() {
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        header = getLayoutInflater().inflate(R.layout.fragment_workout_creator_list_header, workoutBlocksListView, false);
        footer = getLayoutInflater().inflate(R.layout.fragment_workout_creator_list_footer, workoutBlocksListView, false);
        workoutNameEditText = header.findViewById(R.id.workoutNameEditText);
        workoutDescriptionButton = header.findViewById(R.id.workoutDescriptionButton);
        workoutDescriptionLayout = header.findViewById(R.id.workoutDescriptionLayout);
        workoutDescriptionEditText = header.findViewById(R.id.workoutDescriptionEditText);
        addWorkoutBlockButton = footer.findViewById(R.id.addWorkoutBlockButton);
        createWorkoutButton = view.findViewById(R.id.createWorkoutButton);
        saveEditedWorkoutButton = view.findViewById(R.id.saveEditedWorkoutButton);
        cancelEditedWorkoutButton = view.findViewById(R.id.cancelEditedWorkoutButton);
        workoutBlocksListView.addHeaderView(header);
        workoutBlocksListView.addFooterView(footer);

        if (editableWorkout != null) {
            editMode();
        }
        workoutNameEditText.setText(parentActivity.viewModel.buildWorkout.getName());
        workoutDescriptionEditText.setText(parentActivity.viewModel.buildWorkout.getDescription());
        adapter = new WorkoutBlockListAdapter(getContext(), parentActivity.viewModel.buildWorkout.getBlocks());
        workoutBlocksListView.setAdapter(adapter);
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
                parentActivity.setFragmentContainerContent(fragment);
            }
        });

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBuildWorkout();
                parentActivity.viewModel.addCustomWorkout(parentActivity.viewModel.buildWorkout);

                parentActivity.setFragmentContainerContent(new WorkoutTabFragment());
                Toast.makeText(getContext(), "New workout created!", Toast.LENGTH_SHORT).show();
            }
        });
        saveEditedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBuildWorkout();
                String name = parentActivity.viewModel.buildWorkout.getName();
                String description = parentActivity.viewModel.buildWorkout.getDescription();
                List<WorkoutBlock> blocks = parentActivity.viewModel.buildWorkout.getBlocks();
                parentActivity.viewModel.editCustomWorkout(editableWorkout, name, description, blocks);

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

    private void saveBuildWorkout() {
        parentActivity.viewModel.buildWorkout.setName(workoutNameEditText.getText().toString());
        parentActivity.viewModel.buildWorkout.setDescription(workoutDescriptionEditText.getText().toString());
    }

    private void editMode() {
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
