package com.example.ziggy.trainingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class CardioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        ScrollView workoutScrolLView = (ScrollView) findViewById(R.id.workoutScrollView);
        Button cardioBackButton = (Button) findViewById(R.id.cardioBackButton);

        cardioBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
