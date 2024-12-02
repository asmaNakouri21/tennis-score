package com.example.tennis;

import com.example.tennis.exception.UnknownPlayerException;
import org.junit.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {
    @Test
    public void should_write_scenario_for_player_A_Won_all_balls() throws UnknownPlayerException {

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
    public void should_write_scenario_for_player_B_Won_all_balls() throws UnknownPlayerException {

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
    public void should_get_error_when_scorer_list_is_invalid_cas1() {
        // GIVEN
        String inputScore = "AAAAA";

        // WHEN
        TennisGame tennisGame = new TennisGame();

        // THEN
        assertThrows(IllegalStateException.class , () -> tennisGame.getScoreDetails(inputScore));
    }

    @Test
    public void should_get_error_when_scorer_list_is_invalid() {
        // GIVEN
        String scorerList = "ACAZ";
        // WHEN
        TennisGame tennisGame = new TennisGame();
        // THEN
        assertThrows(UnknownPlayerException.class , () -> tennisGame.getScoreDetails(scorerList));
    }

    @Test
    public void should_print_scenario_when_player_B_win_after_remontada_before_deuce() throws UnknownPlayerException {
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
    public void should_print_scenario_when_player_B_win_in_random_scenario() throws UnknownPlayerException {
        // GIVEN
        var expectedScore = List.of(
                "Player A : 15 / Player B : 0", // A score
                "Player A : 15 / Player B : 15", // B score
                "Player A : 30 / Player B : 15", // A score
                "Player A : 30 / Player B : 30", // B score
                "Player A : 40 / Player B : 30", // A score
                "Player A wins the game");       // A score
        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails("ABABAA");
        // THEN
        assertEquals(expectedScore, actualScenario);
    }

    @Test
    public void should_print_scenario_when_player_A_win_after_deuce_and_one_advantage() throws UnknownPlayerException {
        // GIVEN
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
        var actualScenario = tennisGame.getScoreDetails("ABABBAAA");
        // THEN
        assertEquals(expectedScenario, actualScenario);
    }

}
