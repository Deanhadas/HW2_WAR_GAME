package com.example.hw1_war_game.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.hw1_war_game.R;
import com.example.hw1_war_game.callbackes.LocationCallback;
import com.example.hw1_war_game.fragments.Fragment_Top_Ten_List;
import com.example.hw1_war_game.fragments.MapsFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_Top_Ten extends AppCompatActivity implements LocationCallback {

    FrameLayout activity_top_ten_FRL_score_list, activity_top_ten_FRL_map;
    MapsFragment mapsFragment;
    Fragment_Top_Ten_List fragment_top_ten_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__top__ten);
        findViews();

        //inject to frame the fragment
        fragment_top_ten_list = new Fragment_Top_Ten_List(this);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_top_ten_FRL_score_list, fragment_top_ten_list).commit();

        //inject to frame the fragment
        mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_top_ten_FRL_map, mapsFragment).commit();
    }

    private void findViews() {
        activity_top_ten_FRL_score_list = findViewById(R.id.activity_top_ten_FRL_score_list);
        activity_top_ten_FRL_map = findViewById(R.id.activity_top_ten_FRL_map);
    }

    @Override
    public void getLocation(double x, double y) {
        Log.d("LOG","FROM TOP TEN ACTIVITY!!!: "+x+" "+y);
        mapsFragment.getMap().clear();
        LatLng latlng = new LatLng(x,y);
        mapsFragment.getMap().addMarker(new MarkerOptions().position(latlng));
    }
}