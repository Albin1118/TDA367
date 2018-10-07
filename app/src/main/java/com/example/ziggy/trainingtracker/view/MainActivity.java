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
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;

import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavBar;

    MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initComponents();
        initStartingView();
        initListeners();
    }

    private void initComponents() {
        mBottomNavBar = findViewById(R.id.bottom_navigation_bar);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {

        //Listener for the presses on the NavBar
        mBottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (mBottomNavBar.getSelectedItemId() != menuItem.getItemId()) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_dashboard:
                            selectedFragment = new StartPageFragment();
                            break;
                        case R.id.nav_workouts:
                            selectedFragment = new WorkoutTabFragment();
                            break;
                        case R.id.nav_exercises:
                            selectedFragment = new ExerciseTabFragment();
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;

                    }
                    setFragmentContainerContentFromTab(selectedFragment);
                }

                return true;
            }
        });
    }


    private void initStartingView(){
        setFragmentContainerContent(new StartPageFragment());
    }

    public void setFragmentContainerContentFromTab(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                .replace(R.id.fragment_container, f)
                .commit();
    }

    public void setFragmentContainerContent(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f)
                //.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_from_top, R.anim.enter_from_bottom, R.anim.exit_from_top) //not available right now
                .addToBackStack(null)
                .commit();
    }

}
