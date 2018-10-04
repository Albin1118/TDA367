package com.example.ziggy.trainingtracker.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    MainViewModel viewModel;
    SectionsStatePagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
        initDataBinding();

    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {

        //Listener for the presses on the NavBar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment currentFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_dashboard:
                        currentFragment = new StartPageFragment();
                        break;
                    case R.id.nav_workouts:
                        currentFragment = new WorkoutTabFragment();
                        break;
                    case R.id.nav_exercises:
                        currentFragment = new ExerciseTabFragment();
                        break;
                    case R.id.nav_settings:
                        currentFragment = new SettingsFragment();
                        break;

                }

                setFragmentContainerContent(currentFragment);
                return true;
            }
        });
    }


    private void initDataBinding() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }


    public void setFragmentContainerContent(Fragment f){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f)
                .commit();
    }

    public void setWorkoutDetailView(Workout w){
        WorkoutDetailViewFragment fragment = (WorkoutDetailViewFragment)adapter.getItem(6);
        fragment.setWorkoutDetailViewComponents(w.getName(),w.getDescription(), w.getBlocks());
        //fragment.setWorkoutNameTextView(w.getName());
        //fragment.setWorkoutDescriptionTextView(w.getDescription());
        onResume();
    }

    public void setCustomExerciseDetailView(Exercise e, int index){
        CustomExerciseDetailViewFragment fragment = (CustomExerciseDetailViewFragment)adapter.getItem(7);
        fragment.getCurrentExerciseIndex(viewModel.getCustomExercises().indexOf(e));
        fragment.setExerciseDetailViewComponents(e.getName(), e.getDescription(), e.getInstructions());
    }

    public void setExerciseDetailView(Exercise e){
        ExerciseDetailViewFragment fragment = (ExerciseDetailViewFragment)adapter.getItem(8);
        fragment.setExerciseDetailViewComponents(e.getName(), e.getDescription(), e.getInstructions());
    }

    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
