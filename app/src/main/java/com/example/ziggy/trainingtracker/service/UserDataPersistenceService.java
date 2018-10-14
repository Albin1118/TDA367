package com.example.ziggy.trainingtracker.service;


import com.example.ziggy.trainingtracker.model.User;
import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.google.gson.Gson;


public class UserDataPersistenceService {

    private Gson gson;
    private User userData;
    private String JsonData;

    public UserDataPersistenceService(MainViewModel mainViewModel) {
        gson = new Gson();
        
    }

    public void setUser(User userData) {
        this.userData = userData;
    }

    private String convertUserToJson(){
        String json = gson.toJson(userData);

        return json;
    }

    private User convertJsonToUser(){
        User user = gson.fromJson(JsonData, User.class);

        return user;
    }



}
