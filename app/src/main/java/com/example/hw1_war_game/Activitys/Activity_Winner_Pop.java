package com.example.hw1_war_game.Activitys;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hw1_war_game.R;
import com.example.hw1_war_game.Objects.Winner;
import com.example.hw1_war_game.utils.MySp;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;

public class Activity_Winner_Pop extends AppCompatActivity {
    public static final String WINNER_SCORE = "WINNER_SCORE";
    private TextView activity_winner_pop_LBL_winner_name;
    private Button activity_winner_pop_BTN_exit;
    private Button activity_winner_pop_BTN_top10;
    String winner;
    int win_score;
    Gson gson;
    //for permission
    private final int LOCATION_REQUEST_CODE = 100;

    ArrayList<Winner> top10;
    private MediaPlayer mp_winner_song;

    private void getLocationPermission() {
        this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    private boolean checkLocationPermission() {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__winner__pop);
        findViewsByID();
        initView();
        top10 = new ArrayList<Winner>();

        gson = new Gson();
        for (int i = 0; i < 10; i++) {
            String temp = MySp.getInstance().getString(i + "", "");
            if (!temp.isEmpty()) {
                top10.add(gson.fromJson(temp, Winner.class));
            } else {
                i = 10;
            }
        }

        //get String from privius Activity
        winner = getIntent().getStringExtra(Activity_Game_Table.NAME_PLAYER);

        win_score = getIntent().getIntExtra(WINNER_SCORE, 0);
        activity_winner_pop_LBL_winner_name.setText(winner);
        mp_winner_song.start();
        Log.d("LOG", "BI");

        if (!checkLocationPermission()) {
            getLocationPermission();
        } else {
            takeLocation();
        }
    }

    private void insertWinner(double x_map, double y_map){
        top10.add(new Winner(win_score, winner, x_map, y_map));
        top10.sort(new Comparator<Winner>() {
            @Override
            public int compare(Winner o1, Winner o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        if (top10.size() > 10) {
            top10.remove(10);
        }

        //insert to array list of top ten
        for (int i = 0; i < top10.size(); i++) {
            MySp.getInstance().setString(i + "", gson.toJson(top10.get(i), Winner.class));
        }
    }

    private void takeLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    insertWinner(location.getLatitude(),location.getLongitude());
                }else{
                    insertWinner(0.0,0.0);
                }
            }
        });
    }

    private void findViewsByID() {
        activity_winner_pop_LBL_winner_name = findViewById(R.id.activity_winner_pop_LBL_winner_name);
        activity_winner_pop_BTN_exit = findViewById(R.id.activity_winner_pop_BTN_exit);
        activity_winner_pop_BTN_top10= findViewById(R.id.activity_winner_pop_BTN_top10);

        mp_winner_song = MediaPlayer.create(this, R.raw.winner);
    }

    private void initView() {

        activity_winner_pop_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activity_winner_pop_BTN_top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_Winner_Pop.this, Activity_Top_Ten.class);
                startActivity(myIntent);
                finish();
            }
        });

    }

}