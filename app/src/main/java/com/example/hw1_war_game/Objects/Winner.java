package com.example.hw1_war_game.Objects;

public class Winner {
    private int score;
    private String name;
    private double x_map,y_map;
    public Winner(int score, String name , double x_map,double y_map) {
        this.score = score;
        this.name = name;
        this.x_map = x_map;
        this.y_map = y_map;
    }
    public int getScore() {
        return score;
    }
    public String getName() { return name; }
    public double getMapX() { return x_map; }
    public double getMapY() {
        return y_map;
    }
}





