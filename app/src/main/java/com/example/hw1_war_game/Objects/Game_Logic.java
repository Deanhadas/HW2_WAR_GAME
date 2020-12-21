package com.example.hw1_war_game.Objects;

import android.content.Context;
import android.content.Intent;

import com.example.hw1_war_game.Activitys.Activity_Game_Table;
import com.example.hw1_war_game.Activitys.Activity_Winner_Pop;
import com.example.hw1_war_game.Objects.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Game_Logic {

    private static int score_left = 0;
    private static int score_right = 0;
    public static ArrayList<Card> buildArrayOfCard(Context context) {

        ArrayList<Card> fullDeck = new ArrayList<Card>();

        int imageID;
        char[] kind = {'h', 'c', 'd', 's'};
        for (int i = 2; i <= 14; i++) {
            for (int j = 0; j <= 3; j++) {
                String uri = "@drawable/" + kind[j] + "" + i;
                imageID = context.getResources().getIdentifier(uri, null, context.getPackageName());
                fullDeck.add(new Card(imageID, i));
            }
        }
        Collections.shuffle(fullDeck);
        return fullDeck;
    }


    public static int[] pullCard(Activity_Game_Table context, ArrayList<Card> fullDeck) {

        int[] arrReturn = new int[2];
        if (fullDeck.isEmpty()) {

            Intent myIntent = new Intent(context, Activity_Winner_Pop.class);

            if (score_left > score_right) {
                //  SEND WINNER SCORE
                arrReturn[0]=1;
                arrReturn[1]=score_left;

                score_left = 0;
                score_right = 0;
                return arrReturn;

            } else if (score_left < score_right) {
                //  SEND WINNER SCORE
                arrReturn[0]=2;
                arrReturn[1]=score_right;
                score_left = 0;
                score_right = 0;
                return arrReturn;

            } else {

                arrReturn[0]=-1;
                arrReturn[1]=0;
                score_left = 0;
                score_right = 0;
                return arrReturn;//TIE
            }
          //  startActivity(myIntent);
            //finish();

        } else {
            Card card_left   = fullDeck.remove(0);
            Card card_right  = fullDeck.remove(0);

            ((Activity_Game_Table)context).activity_game_table_IMG_card_left.setImageResource(card_left.getIdCardIMG());
            ((Activity_Game_Table)context).activity_game_table_IMG_card_right.setImageResource(card_right.getIdCardIMG());
            if (card_left.getVal() > card_right.getVal()) {
                score_left++;
                ((Activity_Game_Table)context).activity_game_table_LBL_score_left.setText("" + score_left);

            } else if (card_left.getVal() < card_right.getVal()) {
                score_right++;
                ((Activity_Game_Table)context).activity_game_table_LBL_score_right.setText("" + score_right);
            }

            arrReturn[0]=-2;
            arrReturn[1]=0;
            return arrReturn;//NOT END OF THE GAME
        }

    }


}
