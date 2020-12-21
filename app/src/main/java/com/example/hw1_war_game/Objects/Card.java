package com.example.hw1_war_game.Objects;

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
