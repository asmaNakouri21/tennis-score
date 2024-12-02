package com.example.tennis;

import com.example.tennis.exception.UnknownPlayerException;
import com.example.tennis.exception.WrongInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TennisGame {

    Player playerA = new Player("A");
    Player playerB = new Player("B");
    private final List<String> scenario = new ArrayList<>();
    private static final String WIN_MESSAGE_TEMPLATE = "Player %s wins the game";
    public static final String SCORE_MESSAGE_TEMPLATE = "Player A : %s / Player B : %s";
    public static final String DEUCE_MESSAGE = "Deuce";
    public static final String ADVANTAGE_MESSAGE_TEMPLATE = "Deuce, advantage for Player %s";

    public List<String> getScoreDetails(String scorerInput) throws Exception {
        List<String> scorers = getScoreListFromScorerInput(scorerInput);
        for (String scorer : scorers) {
            Player winnerBall = getWinnerBall(scorer);
            Player loserBall = getLooserBall(scorer);
            winnerBall.increaseScore();
            var gameState = getGameState(winnerBall, loserBall);
            scenario.add(formatedScore(gameState, winnerBall));        }
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

    private String getGameState(Player winnerBall, Player loserBall) throws WrongInputException {
        if (isGameInLoveStage(winnerBall, loserBall)) {
            if (winnerBall.getScore() == 4) {
                return "WIN";
            } else if((winnerBall.getScore() < 4)){
                return "SCORE";
            }else{
                throw new WrongInputException("Unknown player name");
            }
        } else {
            if (isDeuce(winnerBall, loserBall)) {
                return "DEUCE";
            } else {
                if (winnerBall.getScore() - loserBall.getScore() == 2){
                    return "WIN";
                } else if (winnerBall.getScore() - loserBall.getScore() == 1){
                    return "ADVANTAGE";
                }
                else {
                    throw new WrongInputException("Wrong Input Score");
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

    private Player getWinnerBall(String scorerName) throws UnknownPlayerException {
        return switch (scorerName) {
            case "A" -> playerA;
            case "B" -> playerB;
            default -> throw new UnknownPlayerException("Unknown player name" + scorerName);
        };
    }

    private Player getLooserBall(String ballWinnerName) throws UnknownPlayerException {
        return getWinnerBall(ballWinnerName).equals(playerA) ? playerB : playerA;
    }

    private boolean isGameInLoveStage(Player winnerBall, Player loserBall) {
        return winnerBall.getScore() < 3 || loserBall.getScore() < 3;
    }
}
