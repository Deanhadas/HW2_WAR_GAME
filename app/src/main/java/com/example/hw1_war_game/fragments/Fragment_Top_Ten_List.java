package com.example.hw1_war_game.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hw1_war_game.Objects.Winner;
import com.example.hw1_war_game.R;
import com.example.hw1_war_game.callbackes.LocationCallback;
import com.example.hw1_war_game.utils.MySp;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Fragment_Top_Ten_List extends Fragment {
    LocationCallback locationCallback;
    ArrayList<Winner> winners;
    String[] winnersString;
    Gson gson;
    ListView fragment_top_ten_list_FREG_list_winners;


    public Fragment_Top_Ten_List(LocationCallback locationCallback) {
        // Required empty public constructor
        this.locationCallback = locationCallback;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__top__ten__list, container, false);
        gson = new Gson();
        findVies(view);
        winners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String temp = MySp.getInstance().getString(i+"","");
            if(!temp.isEmpty()){
                winners.add(gson.fromJson(temp,Winner.class));
            }else{
                i = 10;
            }
        }

        winnersString = new String[winners.size()];
        for (int i = 0; i < winnersString.length; i++) {
            winnersString[i] = (i+1)+". " + winners.get(i).getName() + " " + winners.get(i).getScore() + " Points";
           // winnersString[i] = (i+1)+". " + winners.get(i).getName() + " " + winners.get(i).getScore() + " " + winners.get(i).getMapX() + " " + winners.get(i).getMapY();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.textview_for_listview, winnersString);

        fragment_top_ten_list_FREG_list_winners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                locationCallback.getLocation(winners.get(position).getMapX(),winners.get(position).getMapY());
            }
        });

        fragment_top_ten_list_FREG_list_winners.setAdapter(adapter);
        return view;
    }

    private void findVies(View view) {
        fragment_top_ten_list_FREG_list_winners = view.findViewById(R.id.fragment_top_ten_list_FREG_list_winners);
    }

}