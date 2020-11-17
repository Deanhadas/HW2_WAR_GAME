package com.example.hw1_war_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button main_BTN_move_to_game_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewsByID();
        initView();
    }




    private void findViewsByID() {
        main_BTN_move_to_game_table=findViewById(R.id.main_BTN_move_to_game_table);
    }

    private void initView() {
        main_BTN_move_to_game_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,Activity_Game_Table.class);
                startActivity(myIntent);
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