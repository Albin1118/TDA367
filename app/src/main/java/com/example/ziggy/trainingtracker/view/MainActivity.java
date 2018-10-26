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

import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.viewmodel.AchievementsViewModel;
import com.example.ziggy.trainingtracker.viewmodel.ActiveChallengeViewModel;
import com.example.ziggy.trainingtracker.viewmodel.ActiveWorkoutViewModel;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseCreatorViewModel;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseDetailViewModel;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.StatisticViewModel;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutCreatorViewModel;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutDetailViewModel;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutTabViewModel;


public class MainActivity extends AppCompatActivity implements NavigationManager {

    private BottomNavigationView mBottomNavBar;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getSharedPreferences("Themes", 0).getInt("theme", 0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel(getApplication());

        initComponents();
        initStartingView();
        initListeners();
    }



    private void saveData(){
        viewModel.saveData();
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
                        navigateActiveWorkout();
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

    /**
     * Sets a fragment in the fragment container to be displayed on the screen.
     * @param f The fragment to set as the displayed fragment
     */
    private void setFragmentContainerContent(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_from_top)
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Sets a fragment in the fragment container to be displayed on the screen with a special entry animation meant for the tabs in the navigation bar.
     * @param f The fragment to set as the displayed fragment
     */
    private void setFragmentContainerContentFromTab(Fragment f){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                .replace(R.id.fragment_container, f)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Pops the fragment at the top of the back stack.
     */
    private void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Clears the back stack, back to the start page.
     */
    private void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Sets the state of the bottom navigation bar.
     * Gets called in each of the tab fragment's onCreateView methods to set the nav bar to
     * the corresponding item, for example when the back button is pressed.
     * @param id Id of the menuItem to set as selected
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
        WorkoutTabViewModel viewModel = ViewModelProviders.of(this).get(WorkoutTabViewModel.class);
        viewModel.init(this.viewModel.getModel());

        clearBackStack();
        setFragmentContainerContentFromTab(WorkoutTabFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateActiveWorkout() {
        ActiveWorkoutViewModel viewModel = ViewModelProviders.of(this).get(ActiveWorkoutViewModel.class);
        viewModel.init(this.viewModel.getModel());

        clearBackStack();
        if (viewModel.isWorkoutActive()) {
            setFragmentContainerContentFromTab(ActiveWorkoutFragment.newInstance(viewModel, this));
        } else {
            setFragmentContainerContentFromTab(PreActiveWorkoutFragment.newInstance(viewModel, this));
        }
    }

    @Override
    public void navigateActiveWorkout(IWorkout workout) {
        ActiveWorkoutViewModel viewModel = ViewModelProviders.of(this).get(ActiveWorkoutViewModel.class);
        viewModel.init(this.viewModel.getModel(), workout);

        clearBackStack();
        setFragmentContainerContentFromTab(ActiveWorkoutFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateExercises() {
        ExerciseTabViewModel viewModel = ViewModelProviders.of(this).get(ExerciseTabViewModel.class);
        viewModel.init(this.viewModel.getModel());

        clearBackStack();
        setFragmentContainerContentFromTab(ExerciseTabFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateMore() {
        clearBackStack();
        setFragmentContainerContentFromTab(MoreTabFragment.newInstance(this));
    }

    @Override
    public void navigateExerciseCreator() {
        ExerciseCreatorViewModel viewModel = ViewModelProviders.of(this).get(ExerciseCreatorViewModel.class);
        viewModel.init(this.viewModel.getModel());

        setFragmentContainerContent(ExerciseCreatorFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateExerciseEditor(IExercise exercise) {
        ExerciseCreatorViewModel viewModel = ViewModelProviders.of(this).get(ExerciseCreatorViewModel.class);
        viewModel.init(this.viewModel.getModel(), exercise);

        setFragmentContainerContent(ExerciseCreatorFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateExerciseDetailView(IExercise exercise) {
        ExerciseDetailViewModel viewModel = ViewModelProviders.of(this).get(ExerciseDetailViewModel.class);
        viewModel.init(this.viewModel.getModel(), exercise);

        setFragmentContainerContent(ExerciseDetailViewFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateWorkoutCreator() {
        WorkoutCreatorViewModel viewModel = ViewModelProviders.of(this).get(WorkoutCreatorViewModel.class);
        viewModel.init(this.viewModel.getModel());

        setFragmentContainerContent(WorkoutCreatorFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateWorkoutEditor(IWorkout workout) {
        WorkoutCreatorViewModel viewModel = ViewModelProviders.of(this).get(WorkoutCreatorViewModel.class);
        viewModel.init(this.viewModel.getModel(), workout);

        setFragmentContainerContent(WorkoutCreatorFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateWorkoutBlockCreator() {
        WorkoutCreatorViewModel viewModel = ViewModelProviders.of(this).get(WorkoutCreatorViewModel.class);
        setFragmentContainerContent(WorkoutBlockCreatorFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateWorkoutDetailView(IWorkout workout) {
        WorkoutDetailViewModel viewModel = ViewModelProviders.of(this).get(WorkoutDetailViewModel.class);
        viewModel.init(this.viewModel.getModel(), workout);

        setFragmentContainerContent(WorkoutDetailViewFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateStatistics() {
        StatisticViewModel viewModel = ViewModelProviders.of(this).get(StatisticViewModel.class);
        viewModel.init(this.viewModel.getModel());
        setFragmentContainerContent(StatisticsFragment.newInstance(viewModel, this));
    }

    @Override
    public void navigateActiveChallenge(IChallenge challenge) {
        ActiveChallengeViewModel viewModel = ViewModelProviders.of(this).get(ActiveChallengeViewModel.class);
        viewModel.init(this.viewModel.getModel(), challenge);

        setFragmentContainerContent(ActiveChallengeFragment.newInstance(viewModel, this));

    }

    @Override
    public void navigateAchievements() {
        AchievementsViewModel viewModel = ViewModelProviders.of(this).get(AchievementsViewModel.class);
        viewModel.init(this.viewModel.getModel());
        setFragmentContainerContent(AchievementsFragment.newInstance(viewModel));
    }
}
