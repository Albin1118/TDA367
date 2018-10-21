package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutDetailViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment representing a view displaying contents of a selected workout
 */
public class WorkoutDetailViewFragment extends Fragment {
    private List<IWorkoutBlock>workoutBlocks = new ArrayList<>();

    private TextView workoutNameTextView;
    private Button workoutDescriptionButton;
    private ConstraintLayout workoutDescriptionLayout;
    private TextView workoutDescriptionTextView;
    private ListView workoutBlocksListView;
    private Button startWorkoutButton;
    private Button editWorkoutButton;
    private Button removeWorkoutButton;

    private View view;
    private WorkoutDetailViewModel viewModel;
    private NavigationManager navigator;

    private boolean descriptionClosed;

    public static WorkoutDetailViewFragment newInstance(WorkoutDetailViewModel viewModel, NavigationManager navigator) {
        WorkoutDetailViewFragment fragment = new WorkoutDetailViewFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(WorkoutDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workoutdetailview, container, false);
        navigator = (MainActivity)getActivity();
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        View header = getLayoutInflater().inflate(R.layout.item_workoutdetailviewheader, workoutBlocksListView, false);
        workoutNameTextView = header.findViewById(R.id.workoutNameTextView);
        workoutDescriptionButton = header.findViewById(R.id.workoutDescriptionButton);
        workoutDescriptionLayout = header.findViewById(R.id.workoutDescriptionLayout);
        workoutDescriptionTextView = header.findViewById(R.id.workoutDescriptionTextView);
        startWorkoutButton = view.findViewById(R.id.startWorkoutButton);
        editWorkoutButton = view.findViewById(R.id.editWorkoutButton);
        removeWorkoutButton = view.findViewById(R.id.removeWorkoutButton);
        workoutBlocksListView.addHeaderView(header);

        workoutNameTextView.setText(viewModel.getWorkout().getName());
        workoutDescriptionTextView.setText(viewModel.getWorkout().getDescription());
        ArrayAdapter<IWorkoutBlock> adapter = new WorkoutBlockListAdapter(getContext(), viewModel.getWorkout().getBlocks());
        workoutBlocksListView.setAdapter(adapter);

        if(viewModel.isCustomWorkout()) {
            removeWorkoutButton.setVisibility(View.VISIBLE);
            editWorkoutButton.setVisibility(View.VISIBLE);
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
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateActiveWorkout(viewModel.getWorkout());
            }
        });

        editWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateWorkoutEditor(viewModel.getWorkout());
            }
        });

        removeWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeWorkout();
                navigator.navigateWorkouts();
            }
        });
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
}
