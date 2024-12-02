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

    private static final String WIN_MESSAGE_TEMPLATE = "Player %s wins the game";
    public static final String SCORE_MESSAGE_TEMPLATE = "Player A : %s / Player B : %s";
    public static final String DEUCE_MESSAGE = "Deuce";
    public static final String ADVANTAGE_MESSAGE_TEMPLATE = "Deuce, advantage for Player %s";

    public List<String> getScoreDetails(String scorerInput) throws UnknownPlayerException {
        List<String> scorers = getScoreListFromScorerInput(scorerInput);
        for (String scorer : scorers) {
            Player winnerBall = getWinnerPlayer(scorer);
            Player loserBall = getLooserPlayer(scorer);
            winnerBall.increaseScore();
            var roundResult = calculateRoundScore(winnerBall, loserBall);

            scenario.add(formatedScore(roundResult, winnerBall));        }
        return scenario;
    }

    private String formatedScore(String roundResult, Player roundScorer) {
        return switch (roundResult) {
            case "ADVANTAGE" -> String.format(ADVANTAGE_MESSAGE_TEMPLATE, roundScorer.getName());
            case "WIN" -> String.format(WIN_MESSAGE_TEMPLATE, roundScorer.getName());
            case "SCORE" -> String.format(SCORE_MESSAGE_TEMPLATE, playerA.scoreToDisplay(), playerB.scoreToDisplay());
            case "DEUCE" -> DEUCE_MESSAGE;
            default -> "";
        };
    }

    private String calculateRoundScore(Player winnerPlayer, Player loserPlayer) {
        if (isGameInLoveStage(winnerPlayer, loserPlayer)) {
            if (winnerPlayer.getScore() >= 4) {
                return "WIN";
            } else {
                return "SCORE";
            }
        } else {
            if (isDeuce(winnerPlayer, loserPlayer)) {
                return "DEUCE";
            } else {
                if (winnerPlayer.getScore() - loserPlayer.getScore() == 2) {
                    return "WIN";
                } else {
                    return "ADVANTAGE";
                }
            }
        }
    }


    private boolean isDeuce(Player roundScorer, Player roundLooser) {
        return roundScorer.getScore() == roundLooser.getScore();
    }



    private List<String> getScoreListFromScorerInput(String gameInput) {
        return IntStream.range(0, gameInput.length())
                .mapToObj(index -> gameInput.substring(index, index + 1))
                .toList();
    }

    private Player getWinnerPlayer(String scorerName) throws UnknownPlayerException {
        return switch (scorerName) {
            case "A" -> playerA;
            case "B" -> playerB;
            default -> throw new UnknownPlayerException("Unknown player name" + scorerName);
        };
    }

    private Player getLooserPlayer(String ballWinnerName) throws UnknownPlayerException {
        return getWinnerPlayer(ballWinnerName).equals(playerA) ? playerB : playerA;
    }

    private boolean isGameInLoveStage(Player roundScorer, Player roundLooser) {
        return roundScorer.getScore() < 3 || roundLooser.getScore() < 3;
    }
}
