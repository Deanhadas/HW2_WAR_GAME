package com.example.hw1_war_game.Activitys;

import android.app.Application;

import com.example.hw1_war_game.utils.MySp;

public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        MySp.init(this);
    }
}
