package com.example.ziggy.trainingtracker.service;


import android.content.Context;
import android.util.Log;

import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class UserDataPersistenceService {

    private Gson gson;
    private TrainingTracker trainingTracker;
    private String jsonData;
    private Context context;

    private String fileName;

    public UserDataPersistenceService(TrainingTracker trainingTracker, MainViewModel mainViewModel, Context context) {
        this.trainingTracker = trainingTracker;
        this.context = context;

        fileName = "serialized_data.txt";
        gson = new Gson();
    }



    private void serializeTrainingTracker(){
        jsonData = gson.toJson(trainingTracker);
    }


    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String jsonString = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                jsonString = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return jsonString;
    }
}




