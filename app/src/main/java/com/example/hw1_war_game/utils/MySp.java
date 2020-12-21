package com.example.hw1_war_game.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySp {
    private static MySp instance;
    private SharedPreferences pref;

    private MySp(Context context){
        pref = context.getSharedPreferences("MY_TOP_10",Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if(instance == null){
            instance = new MySp(context.getApplicationContext());
        }
    }

    public static MySp getInstance(){
        return instance;
    }

    public String getString(String key, String def){
        return pref.getString(key,def);
    }

    public void setString(String key, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

}
