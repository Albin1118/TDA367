package com.example.ziggy.trainingtracker.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity implements NavigationManager {

    private BottomNavigationView mBottomNavBar;
    MainViewModel viewModel;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CUSTOM_EXERCISE_DATA = "customExerciseData";
    public static final String CUSTOM_WORKOUT_DATA =  "customWorkoutData";
    public static final String USER_DATA = "userData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initComponents();
        initStartingView();
        initListeners();
        updateViewModelData();

    }


    private void saveData(){
        // Init sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Create gson object for json functionality
        Gson gson = new Gson();

        // Get current state of trainingtracker
        String customExerciseListJson = gson.toJson(viewModel.getCustomExercises());
        String customWorkoutListJson = gson.toJson(viewModel.getCustomWorkouts());

        // Add the Json strings to the shared prefs dir
        editor.putString(CUSTOM_EXERCISE_DATA, customExerciseListJson);
        editor.putString(CUSTOM_WORKOUT_DATA, customWorkoutListJson);
        //Save changes
        editor.apply();

        /*
        System.out.println("Saved following Data to Shared Preferences");
        System.out.println(customExerciseListJson);
        System.out.println(customWorkoutListJson);
        */

    }


    private String loadCustomExerciseData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();

        // Retrieve json string from shared prefs, return null if string not found
        String customExerciseListJson = sharedPreferences.getString(CUSTOM_EXERCISE_DATA, null);

        System.out.println("LOADING DATA ");
        System.out.println(customExerciseListJson);

        return customExerciseListJson;

    }


    private String loadCustomWorkoutData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();

        // Retrieve json string from shared prefs, return null if string not found
        String customWorkoutListJson = sharedPreferences.getString(CUSTOM_WORKOUT_DATA, null);

        System.out.println("LOADING DATA ");
        System.out.println(customWorkoutListJson);

        return  customWorkoutListJson;
    }




    private void updateViewModelData(){
        viewModel.loadUserCustomListsFromJson(loadCustomWorkoutData(), loadCustomExerciseData());
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
                switch (menuItem.getItemId()) {
                    case R.id.nav_dashboard:
                        navigateHome();
                        return true;
                    case R.id.nav_workouts:
                        navigateWorkouts();
                        break;
                    case R.id.nav_active_workout:
                        navigatePreActiveWorkout();

                        /*
                        if (onGoingActiveWorkout()) {
                            navigateActiveWorkout();
                        }
                        else {
                            navigatePreActiveWorkout();
                        }*/

                        break;
                    case R.id.nav_exercises:
                        navigateExercises();
                        break;
                    case R.id.nav_more:
                        navigateMore();
                        break;

                }
                return true;
            }
        });
    }

    public void onPause() {
        super.onPause();
        saveData();

    }

    public boolean onGoingActiveWorkout(){
        return viewModel.checkActiveWorkoutStatus();
    }

    /**
     * Sets the current displayed Fragment/content in the Fragment Container.
     * @param f The fragment to set as the displayed fragment
     */

    private void setFragmentContainerContentFromTab(Fragment f){
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

    /**
     * Pop the fragment at the top of the back stack.
     */
    private void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Clear the back stack, back to the start page.
     */
    private void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Set the state of the bottom navigation bar.
     * Gets called in each of the tab fragment's onCreateView methods to set the nav bar to
     * the corresponding item, for example when the back button is pressed.
     * @param id Id of the menuItem to set checked
     */
    @Override
    public void setNavBarState(int id) {
        mBottomNavBar.getMenu().findItem(id).setChecked(true);
    }

    @Override
    public void hideNavigationBar(){
        mBottomNavBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNavigationBar(){
        mBottomNavBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void goBack() {
        popBackStack();
    }

    @Override
    public void navigateHome() {
        clearBackStack();
    }

    @Override
    public void navigateWorkouts() {
        clearBackStack();
        setFragmentContainerContentFromTab(new WorkoutTabFragment());
    }

    @Override
    public void navigatePreActiveWorkout() {
        clearBackStack();
        setFragmentContainerContentFromTab(new PreActiveWorkoutFragment());
    }

    @Override
    public void navigateActiveWorkout(Workout workout) {
        viewModel.setActiveWorkoutStatus(true);
        ActiveWorkoutFragment f = new ActiveWorkoutFragment();
        f.setCurrentWorkout(workout);
        clearBackStack();
        setFragmentContainerContentFromTab(f);
        Toast.makeText(this, "Workout selected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateExercises() {
        clearBackStack();
        setFragmentContainerContentFromTab(new ExerciseTabFragment());
    }

    @Override
    public void navigateMore() {
        clearBackStack();
        setFragmentContainerContentFromTab(new MoreTabFragment());
    }

    @Override
    public void navigateExerciseCreator() {
        setFragmentContainerContent(new ExerciseCreatorFragment());
    }

    @Override
    public void navigateExerciseEditor(Exercise exercise) {
        ExerciseCreatorFragment fragment = new ExerciseCreatorFragment();
        fragment.setEditableExercise(exercise);
        setFragmentContainerContent(fragment);
    }

    @Override
    public void navigateExerciseDetailView(Exercise exercise) {
        ExerciseDetailViewFragment fragment = new ExerciseDetailViewFragment();
        fragment.setExercise(exercise);
        setFragmentContainerContent(fragment);
    }

    @Override
    public void navigateWorkoutCreator() {
        setFragmentContainerContent(new WorkoutCreatorFragment());
    }

    @Override
    public void navigateWorkoutEditor(Workout workout) {
        WorkoutCreatorFragment fragment = new WorkoutCreatorFragment();
        fragment.setEditableWorkout(workout);
        setFragmentContainerContent(fragment);
    }

    @Override
    public void navigateWorkoutBlockCreator() {
        setFragmentContainerContent(new WorkoutBlockCreatorFragment());
    }

    @Override
    public void navigateWorkoutDetailView(Workout workout) {
        WorkoutDetailViewFragment fragment = new WorkoutDetailViewFragment();
        fragment.setWorkout(workout);
        setFragmentContainerContent(fragment);
    }

    @Override
    public void navigateStatistics() {
        setFragmentContainerContent(new StatisticsFragment());
    }
}
