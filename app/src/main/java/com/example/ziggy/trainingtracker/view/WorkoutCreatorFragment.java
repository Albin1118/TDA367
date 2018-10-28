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
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutCreatorViewModel;

/**
 * Represents a view where the user can create custom workouts, by entering a title and description
 * and adding one or several WorkoutBlocks
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

    private View view;
    private WorkoutCreatorViewModel viewModel;
    private NavigationManager navigator;

    private boolean descriptionClosed;

    public static WorkoutCreatorFragment newInstance(WorkoutCreatorViewModel viewModel, NavigationManager navigator) {
        WorkoutCreatorFragment fragment = new WorkoutCreatorFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(WorkoutCreatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workoutcreator, container, false);
        initViews();
        initListeners();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveBuildWorkout();
    }

    private void initViews() {
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        View header = getLayoutInflater().inflate(R.layout.item_workoutcreatorheader, workoutBlocksListView, false);
        View footer = getLayoutInflater().inflate(R.layout.item_workoutcreatorfooter, workoutBlocksListView, false);

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
        workoutNameEditText.setText(viewModel.getBuildWorkoutName());
        workoutDescriptionEditText.setText(viewModel.getBuildWorkoutDescription());
        ArrayAdapter<IWorkoutBlock> adapter = new WorkoutBlockListAdapter(getContext(), viewModel.getBuildWorkoutBlocks(), true);
        workoutBlocksListView.setAdapter(adapter);

        if (viewModel.isEditMode()) {
            editMode();
        }
    }

    private void initListeners() {
        final int descriptionLayoutHeight = workoutDescriptionLayout.getLayoutParams().height;
        descriptionClosed = true;
        workoutDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropDownAnim a;
                if (descriptionClosed) {
                    a = new DropDownAnim(workoutDescriptionLayout, descriptionLayoutHeight);
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
                navigator.navigateWorkoutBlockCreator();
            }
        });

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(necessaryFieldsFilled()){
                    saveBuildWorkout();
                    viewModel.createWorkout();
                    navigator.goBack();
                    Toast.makeText(getContext(), "New workout created!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "A workout needs a name and at least one block!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        saveEditedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBuildWorkout();
                viewModel.saveWorkout();
                navigator.goBack();
            }
        });

        cancelEditedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goBack();
            }
        });
    }

    private void saveBuildWorkout() {
        viewModel.setBuildWorkoutName(workoutNameEditText.getText().toString());
        viewModel.setBuildWorkoutDescription(workoutDescriptionEditText.getText().toString());
    }

    private void editMode() {
        saveEditedWorkoutButton.setVisibility(View.VISIBLE);
        cancelEditedWorkoutButton.setVisibility(View.VISIBLE);
        createWorkoutButton.setVisibility(View.GONE);
    }

    private Boolean necessaryFieldsFilled(){
        return !workoutNameEditText.getText().toString().equals("") && !workoutBlocksListView.getAdapter().isEmpty();
    }
}
