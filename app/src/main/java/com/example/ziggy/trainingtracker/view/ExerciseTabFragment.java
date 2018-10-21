package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

/**
 * Fragment representing the exercise-tab, where the list of exercises is displayed
 */
public class ExerciseTabFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;

    private View view;
    private ExerciseTabViewModel viewModel;
    private NavigationManager navigator;

    public static ExerciseTabFragment newInstance(ExerciseTabViewModel viewModel, NavigationManager navigator) {
        ExerciseTabFragment fragment = new ExerciseTabFragment();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return ExercisesPageFragment.newInstance(viewModel, navigator);
                    case 1:
                        return ChallengesPageFragment.newInstance(viewModel, navigator);
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);
        initViews();
        initListeners();
        navigator.setNavBarState(R.id.nav_exercises);

        return view;
    }

    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(pagerAdapter);
    }

    private void initListeners() {

    }
}
