package com.example.tennis;

import com.example.tennis.enums.GameStateEnum;
import com.example.tennis.exception.UnknownPlayerException;
import com.example.tennis.exception.WrongInputException;
import com.example.tennis.model.Player;

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
    public static final int pointsToWinFromLove = 4;
    public static final int SCORE_DIFFERENCE_FOR_ADVANTAGE_AFTER_DEUCE = 1;
    public static final int SCORE_DIFFERENCE_FOR_WIN_AFTER_DEUCE = 2;


    public List<String> getScoreDetails(String scorerInput) throws Exception {
        List<String> scorers = getScoreListFromScorerInput(scorerInput);
        for (String scorer : scorers) {
            Player winnerBall = getWinnerBall(scorer);
            Player loserBall = getLooserBall(scorer);
            winnerBall.increaseScore();
            var gameState = getGameState(winnerBall, loserBall);
            scenario.add(formatedScore(gameState, winnerBall));
        }
        return scenario;
    }

    private String formatedScore(GameStateEnum gameState, Player winnerBall) {
        return switch (gameState) {
            case ADVANTAGE -> String.format(ADVANTAGE_MESSAGE_TEMPLATE, winnerBall.getName());
            case WIN -> String.format(WIN_MESSAGE_TEMPLATE, winnerBall.getName());
            case SCORE -> String.format(SCORE_MESSAGE_TEMPLATE, playerA.scoreToDisplay(), playerB.scoreToDisplay());
            case DEUCE -> DEUCE_MESSAGE;
        };
    }

    private GameStateEnum getGameState(Player winnerBall, Player loserBall) throws WrongInputException {
        if (isGameInLoveStage(winnerBall, loserBall)) {
            return getGameStateForGameInLove(winnerBall);
        } else {
            if (isDeuce(winnerBall, loserBall)) {
                return GameStateEnum.DEUCE;
            } else {
                return getGameStateAfterDeuce(winnerBall, loserBall);
            }
        }
    }

    private GameStateEnum getGameStateAfterDeuce(Player winnerBall, Player loserBall) throws WrongInputException {
        int scoreDifference = winnerBall.getScore() - loserBall.getScore();
        return switch (scoreDifference) {
            case SCORE_DIFFERENCE_FOR_ADVANTAGE_AFTER_DEUCE -> GameStateEnum.ADVANTAGE;
            case SCORE_DIFFERENCE_FOR_WIN_AFTER_DEUCE -> GameStateEnum.WIN;
            default -> throw new WrongInputException("Wrong Input Score");
        };

    }

    private GameStateEnum getGameStateForGameInLove(Player winnerBall) throws WrongInputException {
        if ((winnerBall.getScore() < pointsToWinFromLove)) {
            return GameStateEnum.SCORE;
        } else if (winnerBall.getScore() == pointsToWinFromLove) {
            return GameStateEnum.WIN;
        } else {
            throw new WrongInputException("Wrong Input Score");
        }
    }


    private boolean isDeuce(Player winnerBall, Player loserBall) {
        return winnerBall.getScore() == loserBall.getScore();
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
