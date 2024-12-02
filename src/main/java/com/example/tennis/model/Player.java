package com.example.tennis.model;

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

    public String scoreToDisplay() {
        return switch (getScore()) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> throw new IllegalStateException("Unexpected value: " + getScore());
        };
    }
}
