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

    private BottomNavigationView bottomNavigationView;
    private ViewPager mViewPager;

    MainViewModel viewModel;
    SectionsStatePagerAdapter adapter;

    Map<String, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
        initDataBinding();
        //initFragmentMap();
        setupViewPager(mViewPager);
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        mViewPager = findViewById(R.id.viewPagerContainer);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position <=3) { //The amount of items on the navbar
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_dashboard:
                        setViewPager(0);
                        break;
                    case R.id.nav_workouts:
                        setViewPager(1);
                        break;
                    case R.id.nav_exercises:
                        setViewPager(2);
                        break;
                    case R.id.nav_settings:
                        setViewPager(3);
                        break;

                }
                return true;
            }
        });
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void initDataBinding() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void setupViewPager(ViewPager viewpager) {
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StartPageFragment()); //0
        adapter.addFragment(new WorkoutTabFragment()); //1
        adapter.addFragment(new ExerciseTabFragment()); //2
        adapter.addFragment(new SettingsFragment()); //3
        adapter.addFragment(new ExerciseCreatorFragment()); //4
        adapter.addFragment(new WorkoutCreatorFragment()); //5
        adapter.addFragment(new WorkoutDetailViewFragment()); //6
        adapter.addFragment(new CustomExerciseDetailViewFragment()); //7
        adapter.addFragment(new ExerciseDetailViewFragment()); //8
        adapter.addFragment(new WorkoutBlockCreatorFragment()); // 9

        viewpager.setAdapter(adapter);
    }

   /* public void initFragmentMap(){ //tmp,
        fragmentMap.put("ExerciseCreator", new ExerciseCreatorFragment());
        fragmentMap.put("WorkoutCreator", new WorkoutCreatorFragment());
        fragmentMap.put("WorkoutDetailView)", new WorkoutDetailViewFragment());
    }*/

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

    public void setWorkoutCreatorFragment(WorkoutBlock workoutBlock){
        WorkoutCreatorFragment fragment = (WorkoutCreatorFragment)adapter.getItem(5);
        fragment.addWorkoutBlock(workoutBlock);
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
