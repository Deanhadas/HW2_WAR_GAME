package com.example.hw1_war_game.Activitys;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hw1_war_game.R;


public class MainActivity extends AppCompatActivity {

    private Button main_BTN_move_to_game_table;
    private MediaPlayer mp_open_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByID();
        initView();
        mp_open_game.start();
    }




    private void findViewsByID() {
        main_BTN_move_to_game_table=findViewById(R.id.main_BTN_move_to_game_table);
        mp_open_game = MediaPlayer.create(this, R.raw.open_game);

    }

    private void initView() {
        main_BTN_move_to_game_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, Activity_Game_Table.class);
                startActivity(myIntent);
                mp_open_game.stop();
            }
        });



    }


}