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
        checkScorerList(scorers);
        for (String scorer : scorers) {
            updateScores(scorer);
            scenario.add(calculateRoundScore(scorer));
        }
        return scenario;
    }
    private void updateScores(String scorer) {
        if (scorer.equals(playerA.getName())) {
            playerA.increaseScore();
        }
        if (scorer.equals(playerB.getName())) {
            playerB.increaseScore();
        }
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

    private void checkScorerList(List<String> scorers) throws UnknownPlayerException {
        for (String playerName : scorers) {
            if (!playerA.getName().equals(playerName) && !playerB.getName().equals(playerName)) {
                throw new UnknownPlayerException("Unknown player " + playerName);
            }
        }
    }
    private List<String> getScoreListFromScorerInput(String gameInput) {
        return IntStream.range(0, gameInput.length())
                .mapToObj(index -> gameInput.substring(index, index + 1))
                .toList();
    }
}
