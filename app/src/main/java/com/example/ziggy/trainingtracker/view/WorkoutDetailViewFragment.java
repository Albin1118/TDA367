package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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

/**
 * Fragment representing a view displaying contents of a selected workout
 */
public class WorkoutDetailViewFragment extends Fragment {

    private String workoutName = "Workout name";
    private String workoutDescription = "Workout description";
    private List<WorkoutBlock>workoutBlocks = new ArrayList<>();

    private TextView workoutNameTextView;
    private Button workoutDescriptionButton;
    private ConstraintLayout workoutDescriptionLayout;
    private TextView workoutDescriptionTextView;
    private ListView workoutBlocksListView;
    private Button startWorkoutButton;

    private Button editWorkoutButton;
    private Button removeWorkoutButton;

    private MainActivity parentActivity;
    private View view;

    private boolean descriptionClosed = true;
    private Workout workout = new Workout("Name", "Description", workoutBlocks);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_detail_view, container, false);
        parentActivity = ((MainActivity)getActivity());
        initViews();
        initListeners();
        setWorkoutDetailViewComponents(workout.getName(), workout.getDescription(), workout.getBlocks());
        ArrayAdapter<WorkoutBlock> adapter = new WorkoutBlockListAdapter(getContext(), workout.getBlocks());
        workoutBlocksListView.setAdapter(adapter);
        return view;
    }

    private void initViews() {
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        View header = getLayoutInflater().inflate(R.layout.fragment_workout_detail_view_header, workoutBlocksListView, false);
        workoutNameTextView = header.findViewById(R.id.workoutNameTextView);
        workoutDescriptionButton = header.findViewById(R.id.workoutDescriptionButton);
        workoutDescriptionLayout = header.findViewById(R.id.workoutDescriptionLayout);
        workoutDescriptionTextView = header.findViewById(R.id.workoutDescriptionTextView);
        startWorkoutButton = view.findViewById(R.id.startWorkoutButton);
        editWorkoutButton = view.findViewById(R.id.editWorkoutButton);
        removeWorkoutButton = view.findViewById(R.id.removeWorkoutButton);
        workoutBlocksListView.addHeaderView(header);

        workoutNameTextView.setText(workoutName);
        workoutDescriptionTextView.setText(workoutDescription);


        showWorkoutInfo();
    }

    private void initListeners() {
        final int descriptionLayoutHeight = workoutDescriptionLayout.getLayoutParams().height;
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
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Workout started!", Toast.LENGTH_SHORT).show();
            }
        });

        editWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutCreatorFragment fragment = new WorkoutCreatorFragment();
                fragment.setEditableWorkout(workout);
                parentActivity.setFragmentContainerContent(fragment);
            }
        });

        removeWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.viewModel.removeCustomWorkout(workout);
                parentActivity.setFragmentContainerContent(new WorkoutTabFragment());
            }
        });
    }

    private void setWorkoutInfo() {
        workoutNameTextView.setText(workout.getName());
        workoutDescriptionTextView.setText(workout.getDescription());
    }

    private void showWorkoutInfo() {
        setWorkoutInfo();
        workoutDescriptionTextView.setVisibility(View.VISIBLE);
        workoutNameTextView.setVisibility(View.VISIBLE);
        startWorkoutButton.setVisibility(View.VISIBLE);


        if(parentActivity.viewModel.getCustomWorkouts().contains(workout)) {
            removeWorkoutButton.setVisibility(View.VISIBLE);
            editWorkoutButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refreshes the fragment if it's currently visible to the user
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setReorderingAllowed(false);
            ft.detach(this).attach(this).commit();

        }
    }

    /**
     * The method is called from the mainActivity and sets the instance variables of the detailView
     * so that the fragment's contents will be updated next time onCreate() is called.
     * @param workoutName name of the selected workout
     * @param workoutDescription description of the selected workout
     * @param workoutBlocks the list of workoutBlocks from the selected workout
     */
    public void setWorkoutDetailViewComponents(String workoutName, String workoutDescription, List<WorkoutBlock>workoutBlocks){
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.workoutBlocks = workoutBlocks;
    }

    public void setWorkoutNameTextView(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setWorkoutDescriptionTextView(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
