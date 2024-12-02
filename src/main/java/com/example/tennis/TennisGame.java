package com.example.tennis;

import com.example.tennis.exception.UnknownPlayerException;

import java.util.List;
import java.util.stream.IntStream;

public class TennisGame {

    public static final String PLAYER_A_NAME = "A";
    public static final String PLAYER_B_NAME = "B";

    public List<String> getScoreDetails(String scorerInput) throws UnknownPlayerException {
        List<String> scorers = getScoreListFromScorerInput(scorerInput);
        checkScorerList(scorers);
        return List.of();
    }
    private void checkScorerList(List<String> scorers) throws UnknownPlayerException {
        for (String playerName : scorers) {
            if (!PLAYER_A_NAME.equals(playerName) && !PLAYER_B_NAME.equals(playerName)) {
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
