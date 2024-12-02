package com.example.tennis;

import com.example.tennis.exception.UnknownPlayerException;
import com.example.tennis.exception.WrongInputException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {
    @Test
    @DisplayName("Should correctly return the score details when Player A wins all the balls in a game")
    public void shouldReturnCorrectScoreForPlayerAWinAllBals() throws Exception {

        // GIVEN
        String inputScore = "AAAA";
        var expectedScore = List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 30 / Player B : 0",
                "Player A : 40 / Player B : 0",
                "Player A wins the game");

        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);

        // THEN
        assertEquals(expectedScore, actualScenario);
    }

    @Test
    @DisplayName("Should correctly return the score details when Player B wins all the balls in a game")
    public void shouldReturnCorrectScoreForPlayerBWinAllBals() throws Exception {

        // GIVEN
        String inputScore = "BBBB";
        var expectedScore = List.of(
                "Player A : 0 / Player B : 15",
                "Player A : 0 / Player B : 30",
                "Player A : 0 / Player B : 40",
                "Player B wins the game");

        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);

        // THEN
        assertEquals(expectedScore, actualScenario);
    }

    @Test
    @DisplayName("Should throw an error when inputScore gets other player then A and B")
    public void shouldGetErrorWhenScorerListIsInvalid() {
        // GIVEN
        String inputScore = "ACAZ";
        // WHEN
        TennisGame tennisGame = new TennisGame();
        // THEN
        assertThrows(UnknownPlayerException.class , () -> tennisGame.getScoreDetails(inputScore));
    }

    @Test
    @DisplayName("Should throw an error when Player A scores 5 points and Player B scores no points.")
    public void shouldGetErrorWhenScorerListIsInvalidCas2() {
        // GIVEN
        String inputScore = "AAAAA";

        // WHEN
        TennisGame tennisGame = new TennisGame();

        // THEN
        assertThrows(WrongInputException.class , () -> tennisGame.getScoreDetails(inputScore));
    }

    @Test
    @DisplayName("Should correctly return the score details when Player A wins 2 points and Player B wins 3 points and the game")
    public void shouldReturnCorrectScoreDetailsWhenPlayerBWinsGame() throws Exception {
        // GIVEN
        String inputScore = "AABBBB";
        var expectedScore = List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 30 / Player B : 0",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 30 / Player B : 40",
                "Player B wins the game");
        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);
        // THEN
        assertEquals(expectedScore, actualScenario);
    }
    @Test
    @DisplayName("Should correctly return the score details when Player B wins 2 points and Player A wins 4 points and the game")
    public void shouldReturnCorrectScoreDetailsWhenPlayerAWinsGame() throws Exception {
        // GIVEN
        String inputScore = "ABABAA";
        var expectedScore = List.of(
                "Player A : 15 / Player B : 0", // A score
                "Player A : 15 / Player B : 15", // B score
                "Player A : 30 / Player B : 15", // A score
                "Player A : 30 / Player B : 30", // B score
                "Player A : 40 / Player B : 30", // A score
                "Player A wins the game");       // A score
        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);
        // THEN
        assertEquals(expectedScore, actualScenario);
    }

    @Test
    @DisplayName("Should correctly return score details when the game reaches deuce after each player wins 4 points, and then Player A wins")
    public void shouldReturnCorrectScoreDetailsWhenGameReachesDeuceAndPlayerAWins() throws Exception {
        // GIVEN
        String inputScore = "ABABBAAA";

        var expectedScenario = List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 30 / Player B : 40",
                "Deuce",
                "Deuce, advantage for Player A",
                "Player A wins the game");
        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);
        // THEN
        assertEquals(expectedScenario, actualScenario);
    }

    @Test

    @DisplayName("Should correctly return score details when game reaches deuce twice and Player B wins")
    public void shouldReturnCorrectScoreDetailsWhenGameReachesDeuceAndPlayerBWins() throws Exception {
        // GIVEN
        String inputScore = "BAABABABBB";
        var expectedScenario = List.of(
                "Player A : 0 / Player B : 15",  // B score
                "Player A : 15 / Player B : 15", // A score
                "Player A : 30 / Player B : 15", // A score
                "Player A : 30 / Player B : 30", // B score
                "Player A : 40 / Player B : 30", // A score
                "Deuce", // B score : deuce
                "Deuce, advantage for Player A", // A score : deuce with A advantaged
                "Deuce", // B score : deuce with B advantaged
                "Deuce, advantage for Player B", // B score : deuce with A advantaged
                "Player B wins the game");       // B score and wins

        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(inputScore);

        // THEN
        assertEquals(expectedScenario, actualScenario);

    }

    @Test
    @DisplayName("Should throw an error when Player A scores after Player B wins the game")
    public void shouldThrowErrorWhenPlayerAScoresAfterPlayerBWins() throws Exception {
        // GIVEN
        String inputScore = "BAABABABBBA";
        // WHEN
        TennisGame tennisGame = new TennisGame();
        // THEN
        assertThrows(WrongInputException.class , () -> tennisGame.getScoreDetails(inputScore));

    }

    @Test
    @DisplayName("Should throw an error when Player B scores after Player A wins the game")
    public void shouldThrowErrorWhenPlayerBScoresAfterPlayerAWins() throws Exception {
        // GIVEN
        String inputScore = "ABABBAAAB";
        // WHEN
        TennisGame tennisGame = new TennisGame();
        // THEN
        assertThrows(WrongInputException.class , () -> tennisGame.getScoreDetails(inputScore));

    }

}
