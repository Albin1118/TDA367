package com.example.ziggy.trainingtracker.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;



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

    private void initStartingView(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StartPageFragment())
                .commit();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {
        //Listener for the presses on the NavBar
        mBottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_dashboard:
                        clearBackStack();
                        return true;
                    case R.id.nav_workouts:
                        clearBackStack();
                        selectedFragment = new WorkoutTabFragment();
                        break;
                    case R.id.nav_active_workout:
                        clearBackStack();

                        /*
                        if (onGoingActiveWorkout()) {
                            selectedFragment = new ActiveWorkoutFragment();
                        }
                        else {
                            selectedFragment = new PreActiveWorkoutFragment();
                        }*/

                        selectedFragment = new PreActiveWorkoutFragment();

                        break;
                    case R.id.nav_exercises:
                        clearBackStack();
                        selectedFragment = new ExerciseTabFragment();
                        break;
                    case R.id.nav_more:
                        clearBackStack();
                        selectedFragment = new MoreTabFragment();
                        break;

                }
                setFragmentContainerContentFromTab(selectedFragment);

                return true;
            }
        });
    }

    /*
    @Override
    public void onStop() {
        super.onStop();
        viewModel.saveAllData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.saveAllData();
    }
    */

    public void hideBottomNavigationBar(){
        mBottomNavBar.setVisibility(View.INVISIBLE);
    }

    public void showBottomNavigationBar(){
        mBottomNavBar.setVisibility(View.VISIBLE);
    }

    public boolean onGoingActiveWorkout(){
        return viewModel.checkActiveWorkoutStatus();
    }

    /**
     * Pop the fragment at the top of the back stack.
     */
    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Clear the back stack, back to the start page.
     */
    public void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Set the state of the bottom navigation bar.
     * Gets called in each of the tab fragment's onCreateView methods to set the nav bar to
     * the corresponding item, for example when the back button is pressed.
     * @param id Id of the menuItem to set checked
     */
    public void setNavBarState(int id) {
        mBottomNavBar.getMenu().findItem(id).setChecked(true);
    }

    /**
     * Sets the current displayed Fragment/content in the Fragment Container.
     * @param f The fragment to set as the displayed fragment
     */

    public void setFragmentContainerContentFromTab(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit();
    }

    public void setFragmentContainerContent(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_from_top)
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit();
    }

}
