package com.example.tennis;

import com.example.tennis.exception.UnknownPlayerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TennisGame {

    Player playerA = new Player("A");
    Player playerB = new Player("B");
    private static final String WIN_MESSAGE = "Player %s wins the game";
    private final List<String> scenario = new ArrayList<>();

    public List<String> getScoreDetails(String scorerInput) throws UnknownPlayerException {
        List<String> scorers = getScoreListFromScorerInput(scorerInput);
        for (String scorer : scorers) {
            Player playerWinner = getPlayerWinner(scorer);
            playerWinner.increaseScore();
            scenario.add(calculateRoundScore(scorer));
        }
        return scenario;
    }

    private Player getPlayerWinner(String score) throws UnknownPlayerException {
        if (score.equals("A")) {
            return playerA;
        }
        if (score.equals("B")) {
            return playerB;
        }
        throw new UnknownPlayerException("Unknown player " + score);
    }

    private String calculateRoundScore(String scorerName) {
        if (playerA.getScore() == 4 || playerB.getScore() == 4) {
            return String.format(WIN_MESSAGE, scorerName);
        } else if (playerA.getScore() == 4 || playerB.getScore() == 4) {
            throw new IllegalStateException("Unexpected value: ");
        } else {
            return String.format("Player A : %s / Player B : %s", scoreToDisplay(playerA.getScore()), scoreToDisplay(playerB.getScore()));
        }
    }
    private String scoreToDisplay(int intScore) {
        return switch (intScore) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> throw new IllegalStateException("Unexpected value: " + intScore);
        };
    }

    private List<String> getScoreListFromScorerInput(String gameInput) {
        return IntStream.range(0, gameInput.length())
                .mapToObj(index -> gameInput.substring(index, index + 1))
                .toList();
    }
}
