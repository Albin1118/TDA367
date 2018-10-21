package com.example.ziggy.trainingtracker.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.ActiveWorkoutViewModel;

public class ActiveWorkoutFragment extends Fragment {

    private Button startButton;
    private Button pauseButton;
    private TextView currentWorkoutName;
    private ListView currentWorkoutBlockListView;

    private View view;
    private ActiveWorkoutViewModel viewModel;
    private NavigationManager navigator;

    private long lastPause;
    private Chronometer mChronometer;

    public static ActiveWorkoutFragment newInstance(ActiveWorkoutViewModel viewModel, NavigationManager navigator) {
        ActiveWorkoutFragment fragment = new ActiveWorkoutFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ActiveWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_active_workout, container, false);
        navigator.setNavBarState(R.id.nav_active_workout);
        navigator.hideNavigationBar();

        initViews();
        initListeners();
        showStartButton();

        ArrayAdapter<IWorkoutBlock> adapter = new WorkoutBlockListAdapter(getContext(), viewModel.getActiveWorkout().getBlocks());
        currentWorkoutBlockListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        navigator.showNavigationBar();
    }

    private void initViews() {
        startButton = view.findViewById(R.id.begin_workout_button);
        pauseButton = view.findViewById(R.id.pause_workout_button);
        currentWorkoutName = view.findViewById(R.id.current_workout_name);
        currentWorkoutBlockListView = view.findViewById(R.id.active_workout_exercise_list_view);
        mChronometer = view.findViewById(R.id.chronometer);

        currentWorkoutBlockListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        currentWorkoutName.setText(viewModel.getActiveWorkout().getName());
    }

    private void initListeners() {

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.startWorkout();

                if (lastPause != 0){
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                } else {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }

                mChronometer.start();
                showPauseButton();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                showStartButton();
            }
        });

        pauseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.app_name);
                builder.setMessage("Are you sure you want to stop the current workout?");
                builder.setIcon(R.drawable.ic_wb_incandescent_black_24dp);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        viewModel.finishWorkout();
                        navigator.navigateHome();
                        Toast.makeText(getContext(), "Workout canceled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });

        currentWorkoutBlockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
            }
        });

    }


    private void showPauseButton(){
        startButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
    }


    private void showStartButton(){
        startButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
    }
}



