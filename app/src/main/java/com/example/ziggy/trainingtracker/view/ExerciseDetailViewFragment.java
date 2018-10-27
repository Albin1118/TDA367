package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseDetailViewModel;

/**
 * Fragment representing a view displaying contents of a selected exercise
 */
public class ExerciseDetailViewFragment extends Fragment {


    private TextView exerciseNameTextView;
    private TextView exerciseUnitTextView;
    private TextView exerciseDescriptionTextView;
    private TextView exerciseInstructionsTextView;
    private TextView exerciseCategoryTextView;
    private Button removeExerciseButton;
    private Button editExerciseButton;

    private View view;
    private ExerciseDetailViewModel viewModel;
    private NavigationManager navigator;

    public static ExerciseDetailViewFragment newInstance(ExerciseDetailViewModel viewModel, NavigationManager navigator) {
        ExerciseDetailViewFragment fragment = new ExerciseDetailViewFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ExerciseDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercisedetailview, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView);
        exerciseUnitTextView = view.findViewById(R.id.exerciseUnitTextView);
        exerciseCategoryTextView = view.findViewById(R.id.exerciseCategoryTextView);
        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
        exerciseDescriptionTextView.setMovementMethod(new ScrollingMovementMethod());
        exerciseInstructionsTextView = view.findViewById(R.id.exerciseInstructionsTextView);
        exerciseInstructionsTextView.setMovementMethod(new ScrollingMovementMethod());
        removeExerciseButton = view.findViewById(R.id.removeExerciseButton);
        editExerciseButton = view.findViewById(R.id.editExerciseButton);

        exerciseNameTextView.setText(viewModel.getExerciseName());
        exerciseUnitTextView.setText(viewModel.getExerciseUnit());
        exerciseCategoryTextView.setText(viewModel.getExerciseCategories());
        exerciseDescriptionTextView.setText(viewModel.getExerciseDescription());
        exerciseInstructionsTextView.setText(viewModel.getExerciseInstructions());

        if (viewModel.isCustomExercise()) {
            removeExerciseButton.setVisibility(View.VISIBLE);
            editExerciseButton.setVisibility(View.VISIBLE);
        }
    }

    private void initListeners() {
        removeExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeExercise();
                navigator.goBack();
            }
        });

        editExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateExerciseEditor(viewModel.getExercise());
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
