package com.example.tennis;

public class Player {
    private final String name;
    int score = 0;
    public Player(String name) {
        this.name = name;
    }
    public void increaseScore() {
        score += 1;
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
}
