package com.example.ziggy.trainingtracker.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingTracker {
    private User user = new User();
    private List<Workout> workouts = new ArrayList<>();
    private Map<String, Exercise> exercises = new HashMap<>();

    public TrainingTracker() {
        loadExercises();
    }

    private void loadExercises() {
        System.out.println("loading exercises...");
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/res/raw/exercises.txt"), "UTF-8"))
        ){
            String line;
            while((line = reader.readLine()) != null) {
                this.parseExerciseLine(line);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    private void parseExerciseLine(String line) {
        String[] tokens = line.split("\\s*;\\s*");
        if (tokens.length == 2) {
            String name = tokens[0];
            String description = tokens[1];
            exercises.put(name, new Exercise(name, description));
        } else {
            System.out.println("Wrong exercise format: " + line);
        }
    }
}
