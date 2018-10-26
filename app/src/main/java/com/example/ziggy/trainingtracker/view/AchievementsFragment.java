package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Achievement;
import com.example.ziggy.trainingtracker.viewmodel.AchievementsViewModel;

/**
 * Fragment for displaying achievements
 */

public class AchievementsFragment extends Fragment {

    private ListView achievementsListView;

    private View view;
    private AchievementsViewModel viewModel;

    public static AchievementsFragment newInstance(AchievementsViewModel viewModel) {
        AchievementsFragment fragment = new AchievementsFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    public void setViewModel(AchievementsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_achievements, container, false);
        initViews();
        initListeners();

        return view;

    }

    private void initViews() {
        achievementsListView = view.findViewById(R.id.achievementsListView);

        achievementsListView.setAdapter(new ArrayAdapter<Achievement>(getContext(), R.layout.item_achievement, R.id.achievementNameTextView, viewModel.getAchievements()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView achievementNameTextView = view.findViewById(R.id.achievementNameTextView);
                TextView progressTextView = view.findViewById(R.id.progressTextView);
                ProgressBar progressBar = view.findViewById(R.id.progressBar);

                int progress = viewModel.getAchievements().get(position).getProgress();
                int requirement = viewModel.getAchievements().get(position).getRequirement();

                achievementNameTextView.setText(viewModel.getAchievements().get(position).getName());
                progressTextView.setText(progress + "/" + requirement);
                progressBar.setProgress(100*progress/requirement);

                return view;
            }
        });
    }

    private void initListeners() {

    }
}



