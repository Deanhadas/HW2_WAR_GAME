package com.example.hw1_war_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Activity_Game_Table extends AppCompatActivity {
    public static final String NAME_PLAYER = "NAME_PLAYER";
    public static final String TIE = "TIE";

    private TextView activity_game_table_LBL_player_right, activity_game_table_LBL_player_left;
  //  private ImageView activity_game_table_IMG_icon_right, activity_game_table_IMG_icon_left;
    private TextView activity_game_table_LBL_score_left, activity_game_table_LBL_score_right;
    private Button activity_pop_BTN_go;
    private ImageView activity_game_table_IMG_card_left, activity_game_table_IMG_card_right;

    private ArrayList<Card> fullDeck;
    private int score_left = 0;
    private int score_right = 0;

    public class Card {
        private int id_card_img, val;

        public Card(int id_card_img, int val) {
            this.id_card_img = id_card_img;
            this.val = val;
        }

        public int getIdCardIMG() {
            return id_card_img;
        }

        public int getVal() {
            return val;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__game__table);
        fullDeck = new ArrayList<Card>();
        buildArrayOfCard();
        Collections.shuffle(fullDeck);
        findViewsByID();
        initView();

    }


    private void findViewsByID() {
        activity_game_table_IMG_card_left = findViewById(R.id.activity_game_table_IMG_card_left);
        activity_game_table_IMG_card_right = findViewById(R.id.activity_game_table_IMG_card_right);
        activity_pop_BTN_go = findViewById(R.id.activity_pop_BTN_go);
        activity_game_table_LBL_score_left = findViewById(R.id.activity_game_table_LBL_score_left);
        activity_game_table_LBL_score_right = findViewById(R.id.activity_game_table_LBL_score_right);
     //   activity_game_table_IMG_icon_right = findViewById(R.id.activity_game_table_IMG_icon_right);
     //   activity_game_table_IMG_icon_left = findViewById(R.id.activity_game_table_IMG_icon_left);
        activity_game_table_LBL_player_right = findViewById(R.id.activity_game_table_LBL_player_right);
        activity_game_table_LBL_player_left = findViewById(R.id.activity_game_table_LBL_player_left);
    }

    private void initView() {
        activity_pop_BTN_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullCard();

            }
        });

    }

    private void buildArrayOfCard() {

        int imageID;
        char[] kind = {'h', 'c', 'd', 's'};
        for (int i = 2; i <= 14; i++) {
            for (int j = 0; j <= 3; j++) {
                String uri = "@drawable/" + kind[j] + "" + i;
                imageID = getResources().getIdentifier(uri, null, getPackageName());
                fullDeck.add(new Card(imageID, i));
            }
        }


    }


    private void pullCard() {

        if (fullDeck.isEmpty()) {

            Intent myIntent = new Intent(Activity_Game_Table.this, Activity_Winner_Pop.class);

            if (score_left > score_right) {

                myIntent.putExtra(NAME_PLAYER, activity_game_table_LBL_player_left.getText());
            } else if (score_left < score_right) {
                myIntent.putExtra(NAME_PLAYER, activity_game_table_LBL_player_right.getText());
                
            } else {
                myIntent.putExtra(NAME_PLAYER, TIE);
            }
            startActivity(myIntent);
            finish();

        } else {
            Card card_left = fullDeck.remove(0);
            Card card_right = fullDeck.remove(0);
            activity_game_table_IMG_card_left.setImageResource(card_left.getIdCardIMG());
            activity_game_table_IMG_card_right.setImageResource(card_right.getIdCardIMG());
            if (card_left.val > card_right.val) {
                score_left++;
                activity_game_table_LBL_score_left.setText("" + score_left);
            } else if (card_left.val < card_right.val) {
                score_right++;
                activity_game_table_LBL_score_right.setText("" + score_right);
            }
        }

    }


}