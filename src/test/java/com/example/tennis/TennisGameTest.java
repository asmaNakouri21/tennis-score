package com.example.tennis;


import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TennisGameTest {
    @Test
    public void should_write_scenario_for_player_A_Won_all_balls() {

        // GIVEN
        String scorerList = "AAAA";
        var expectedScenario = List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 30 / Player B : 0",
                "Player A : 40 / Player B : 0",
                "Player A wins the game");

        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(scorerList);

        // THEN
        assertEquals(expectedScenario, actualScenario);
    }

    @Test
    public void should_write_scenario_for_player_B_Won_all_balls() {

        // GIVEN
        String scorerList = "BBBB";
        var expectedScenario = List.of(
                "Player A : 0 / Player B : 15",
                "Player A : 0 / Player B : 30",
                "Player A : 0 / Player B : 40",
                "Player B wins the game");

        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(scorerList);

        // THEN
        assertEquals(expectedScenario, actualScenario);
    }


    @Test
    public void should_get_error_when_scorer_list_is_invalid() {
        // GIVEN
        String scorerList = "AAAAA";

        // WHEN
        TennisGame tennisGame = new TennisGame();

        // THEN
        assertThrows(Exception.class , () -> tennisGame.getScoreDetails(scorerList));
    }

    @Test
    public void should_print_scenario_when_player_B_win_after_remontada_before_deuce() {
        // GIVEN
        var expectedScenario = List.of(
                "Player A : 15 / Player B : 0",
                "Player A : 30 / Player B : 0",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 30 / Player B : 40",
                "Player B wins the game");

        String remontadaInput = "AABBBB";
        // WHEN
        TennisGame tennisGame = new TennisGame();
        var actualScenario = tennisGame.getScoreDetails(remontadaInput);

        // THEN
        assertEquals(expectedScenario, actualScenario);
    }

    @Test
    public void should_print_scenario_when_player_B_win_in_random_scenario() {

        // GIVEN
        var expectedScenario = List.of(
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
        assertEquals(expectedScenario, actualScenario);
    }

    @Test
    public void should_print_scenario_when_player_A_win_after_deuce_and_one_advantage() {

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

    @Test
    public void should_print_scenario_when_player_B_win_after_deuce_and_two_advantage_rounds() {

        // GIVEN
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
        var actualScenario = tennisGame.getScoreDetails("BAABABABBB");

        // THEN
        assertEquals(expectedScenario, actualScenario);

    }

}