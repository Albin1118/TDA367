package com.example.ziggy.trainingtracker.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class UserDataPersistenceService extends Service {

    private Gson gson;
    private ITrainingTracker trainingTracker;
    private ITrainingTracker deSerializedTrainingTracker;
    private String jsonData;


    private String fileName;

    public UserDataPersistenceService(ITrainingTracker trainingTracker) {
        this.trainingTracker = trainingTracker;
        fileName = "serialized_data.txt";
        gson = new Gson();
    }



    public void serializeTrainingTrackerLocally(){
        jsonData = gson.toJson(trainingTracker);
        writeToFile(jsonData, this);
    }

    private void deSerializeTrainingTracker(){
        String trainingTrackerJsonData = readFromFile(this);
        System.out.println(trainingTrackerJsonData);
        deSerializedTrainingTracker = gson.fromJson(trainingTrackerJsonData, TrainingTracker.class);

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

    public ITrainingTracker getDeSerializedTrainingTracker() {
        try {
            deSerializeTrainingTracker();
        }
        catch (Exception e) {
            System.out.println("Could not get deSerializedTrainingTracker");
            return null;
        }
        return deSerializedTrainingTracker;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}




