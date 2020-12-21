package com.example.hw1_war_game.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hw1_war_game.Objects.Card;
import com.example.hw1_war_game.Objects.Game_Logic;
import com.example.hw1_war_game.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Game_Table extends AppCompatActivity {

    public static final String TIE = "TIE";
    public static final String NAME_PLAYER = "NAME_PLAYER";


   // private Game_Logic game_logic;

    private TextView activity_game_table_LBL_player_right, activity_game_table_LBL_player_left;
    private ImageView activity_game_table_IMG_icon_right, activity_game_table_IMG_icon_left;
    public TextView activity_game_table_LBL_score_left, activity_game_table_LBL_score_right;
    public TextView activity_game_table_LBL_timer;
    private Timer timer;
    private ProgressBar activity_game_table_PBAR;
    Game_Logic gm = new Game_Logic();

    private Button activity_pop_BTN_go;
    public ImageView activity_game_table_IMG_card_left, activity_game_table_IMG_card_right;

    private ArrayList<Card> fullDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__game__table);
        fullDeck = new ArrayList<Card>();
        fullDeck= gm.buildArrayOfCard(this);

        findViewsByID();
        initView();

    }


    private void findViewsByID() {
        activity_game_table_IMG_card_left = findViewById(R.id.activity_game_table_IMG_card_left);
        activity_game_table_IMG_card_right = findViewById(R.id.activity_game_table_IMG_card_right);
        activity_pop_BTN_go = findViewById(R.id.activity_pop_BTN_go);
        activity_game_table_LBL_score_left = findViewById(R.id.activity_game_table_LBL_score_left);
        activity_game_table_LBL_score_right = findViewById(R.id.activity_game_table_LBL_score_right);
        activity_game_table_LBL_player_right = findViewById(R.id.activity_game_table_LBL_player_right);
        activity_game_table_LBL_player_left = findViewById(R.id.activity_game_table_LBL_player_left);
        activity_game_table_LBL_timer=findViewById(R.id.activity_game_table_LBL_timer);
        activity_game_table_PBAR=findViewById(R.id.activity_game_table_PBAR);
    }

    private void initView() {
        activity_pop_BTN_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_pop_BTN_go.setVisibility(View.INVISIBLE);

        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int[] gameOver = {-2,0};

                        if(gameOver[0]==-2) {
                            gameOver = Game_Logic.pullCard(Activity_Game_Table.this, fullDeck);
                        }

                       if(gameOver[0]==1){
                           Intent myIntent = new Intent(Activity_Game_Table.this, Activity_Winner_Pop.class);
                           myIntent.putExtra(NAME_PLAYER, activity_game_table_LBL_player_left.getText());
                         //  myIntent.putExtra(Activity_Winner_Pop.NAME_PLAYER, activity_game_table_LBL_player_left.getText());
                           myIntent.putExtra(Activity_Winner_Pop.WINNER_SCORE,gameOver[1]);

                           startActivity(myIntent);
                           timer.cancel();
                           finish();
                       }
                        if(gameOver[0]==2){
                            Intent myIntent = new Intent(Activity_Game_Table.this, Activity_Winner_Pop.class);

                            myIntent.putExtra(NAME_PLAYER, activity_game_table_LBL_player_right.getText());
                            myIntent.putExtra(Activity_Winner_Pop.WINNER_SCORE,gameOver[1]);
                            startActivity(myIntent);
                            timer.cancel();
                            finish();
                        }
                        if(gameOver[0]==-1){
                            Intent myIntent = new Intent(Activity_Game_Table.this, Activity_Winner_Pop.class);
                            myIntent.putExtra(NAME_PLAYER, TIE);
                            myIntent.putExtra(Activity_Winner_Pop.WINNER_SCORE,0);
                            startActivity(myIntent);
                            timer.cancel();
                            finish();
                        }
                        activity_game_table_PBAR.setProgress(activity_game_table_PBAR.getProgress()-1);
                    }
                });
            }
        },0,3000);


            }
        });

    }







}