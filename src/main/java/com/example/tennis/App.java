package com.example.tennis;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TennisGame tennisGame = new TennisGame();
        System.out.println("Please enter the game score (e.g., 'AABBAA'):");
        Scanner scanner = new Scanner(System.in);
        String gameInput = scanner.nextLine();

        List<String> scenario = null;
        try {
            scenario = tennisGame.getScoreDetails(gameInput);
            for(var roundResult: scenario){
                System.out.println(roundResult);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
