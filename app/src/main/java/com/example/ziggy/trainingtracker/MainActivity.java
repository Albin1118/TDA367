package com.example.ziggy.trainingtracker;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.containter);

        setupViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewpager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StartPageFragment());
        adapter.addFragment(new ExercisesFragment());
        adapter.addFragment(new CreateExerciseFragment());
        viewpager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
