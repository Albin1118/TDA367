package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;

/**
 * Fragment representing a view displaying contents of a selected exercise
 */
public class ExerciseDetailViewFragment extends Fragment {

    private String exerciseName = "Exercise name";
    private String exerciseDescription = "Exercise description";

    private TextView exerciseNameTextView;
    private TextView exerciseDescriptionTextView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_detail_view, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView);
        exerciseNameTextView.setText(exerciseName);

        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
        exerciseDescriptionTextView.setText(exerciseDescription);
    }

    private void initListeners() {

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
     * @param exerciseName name of the selected exercise
     * @param exerciseDescription description of the selected exercise
     */
    public void setExerciseDetailViewComponents(String exerciseName, String exerciseDescription){
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseNameTextView(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescriptionTextView(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }
}
