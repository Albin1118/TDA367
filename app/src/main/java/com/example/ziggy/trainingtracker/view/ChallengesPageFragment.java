package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

public class ChallengesPageFragment extends Fragment {

    private View view;
    private ExerciseTabViewModel viewModel;
    private NavigationManager navigator;

    public static ChallengesPageFragment newInstance(ExerciseTabViewModel viewModel, NavigationManager navigator) {
        ChallengesPageFragment fragment = new ChallengesPageFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ExerciseTabViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_challenges_page, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {

    }

    private void initListeners() {

    }
}
