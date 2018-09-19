package com.example.ziggy.trainingtracker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.rick);


        Button cardioButton = (Button) findViewById(R.id.cardioButton);
        Button strengthButton = (Button) findViewById(R.id.strengthButton);

        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardioActivity.class));
            }
        });

        strengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mp.start();
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));

            }
        });



    }
}
