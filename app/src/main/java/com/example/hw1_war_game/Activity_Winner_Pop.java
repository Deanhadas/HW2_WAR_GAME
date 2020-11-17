package com.example.hw1_war_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Winner_Pop extends AppCompatActivity {
    private String winner;
    private TextView activity_winner_pop_LBL_winner_name;
    private Button activity_winner_pop_BTN_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__winner__pop);
        findViewsByID();
        initView();



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                winner = null;
            } else {
                winner = extras.getString("NAME_PLAYER");
            }
        } else {
            winner = (String) savedInstanceState.getSerializable("NAME_PLAYER");
        }

        activity_winner_pop_LBL_winner_name.setText(winner);
    }


    private void findViewsByID() {
        activity_winner_pop_LBL_winner_name = findViewById(R.id.activity_winner_pop_LBL_winner_name);
        activity_winner_pop_BTN_exit = findViewById(R.id.activity_winner_pop_BTN_exit);

    }

    private void initView() {

        activity_winner_pop_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LOG", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG", "onResume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LOG", "onSaveInstanceState");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG", "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG", "onDestroy");
    }

*/
}