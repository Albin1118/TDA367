package com.example.ziggy.trainingtracker.Services;

import com.example.ziggy.trainingtracker.model.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadExercisesService {

    private List<Exercise> exercises = new ArrayList<>();

    /**
     * Reads res/raw/exercises.txt line for line decoding the Strings into Exercise objects
     * and adds them to the list of Exercises that gets returned.
     * @return the list of exercises decoded from the text file
     */
    public List<Exercise> loadExercises() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("res/raw/exercises.txt"), "UTF-8"))
        ){
            String line;
            while ((line = reader.readLine()) != null) {
                this.parseExerciseLine(line);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }
        return exercises;
    }

    /**
     * Decodes a line representing an Exercise in String format and adds it to the list of Exercises.
     * Format: name;description
     * @param line a line representing an Exercise in String format
     */
    private void parseExerciseLine(String line) {
        String[] tokens = line.split("\\s*;\\s*");
        if (tokens.length == 2) {
            String name = tokens[0];
            String description = tokens[1];
            exercises.add(new Exercise(name, description));
        } else {
            System.out.println("Wrong exercise format: " + line);
        }
    }
}
