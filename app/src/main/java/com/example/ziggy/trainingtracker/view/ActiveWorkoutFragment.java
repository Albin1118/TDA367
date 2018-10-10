package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Workout;


public class ActiveWorkoutFragment extends Fragment {

    private Button startButton;
    private Button pauseButton;
    private TextView currentWorkoutName;

    private Workout currentWorkout;

    private MainActivity parentActivity;
    private View view;

    private long lastPause;

    private Chronometer mChronometer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_active_workout, container, false);
        parentActivity = ((MainActivity)getActivity());
        parentActivity.setNavBarState(R.id.nav_active_workout);

        initViews();
        initListeners();

        //currentWorkoutName.setText(currentWorkout.getName());

        pauseButton.setVisibility(View.INVISIBLE);

        return view;
    }


    private void initViews() {
        startButton = view.findViewById(R.id.begin_workout_button);
        pauseButton = view.findViewById(R.id.pause_workout_button);
        currentWorkoutName = view.findViewById(R.id.current_workout_name);

        mChronometer = view.findViewById(R.id.chronometer);

    }

    private void initListeners() {

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lastPause != 0){
                    mChronometer.setBase(mChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }

                else {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }

                mChronometer.start();
                startButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPause = SystemClock.elapsedRealtime();
                mChronometer.stop();
                startButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.INVISIBLE);
            }
        });

    }


    public void setCurrentWorkout(Workout currentWorkout) {
        this.currentWorkout = currentWorkout;
    }
}



