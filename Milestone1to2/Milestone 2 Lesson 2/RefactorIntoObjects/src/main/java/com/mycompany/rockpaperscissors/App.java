/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rockpaperscissors;

/**
 *
 * @author James
 */
import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        Random randomizer = new Random();

        String playAgain = "y";
        while (playAgain.equals("y")) {
            
            System.out.println("Let's play rock, paper, scissors");
            System.out.println("How many rounds do you want to play?");
            int numRounds = myScanner.nextInt();
            
            while (numRounds < 1 || numRounds > 10) {
                System.out.println("ERROR. Please enter a # between 1 and 10. How many rounds do you want to play?");
                numRounds = myScanner.nextInt();
            }

            int playerWins = 0;
            int compWins = 0;
            int ties = 0;

            for (int currentRound = 0; currentRound < numRounds; currentRound++) {
                System.out.println("Make your choice: 1 = Rock, 2 = Paper, 3 = Scissors");
                int playerChoice = myScanner.nextInt();
                while (playerChoice < 1 || playerChoice > 3) {
                    System.out.println("ERROR. Please enter 1 for Rock, 2 for Paper, or 3 for Scissors");
                    playerChoice = myScanner.nextInt();
                }
                int compChoice = randomizer.nextInt(3 - 1 + 1) + 1;

                if (playerChoice == compChoice) {
                    ties++;
                    System.out.println("We tied");
                }
                else {
                    if (playerChoice == 1 && compChoice == 2) {
                        compWins++;
                        System.out.println("The computer won");
                    }
                    else if (playerChoice == 1 && compChoice == 3) {
                        playerWins++;
                        System.out.println("You won");
                    }
                    else if (playerChoice == 2 && compChoice == 1) {
                        playerWins++;
                        System.out.println("You won");
                    }
                    else if (playerChoice == 2 && compChoice == 3) {
                        compWins++;
                        System.out.println("The computer won");
                    }
                    else if (playerChoice == 3 && compChoice == 1) {
                        compWins++;
                        System.out.println("The computer won");
                    }
                    else if (playerChoice == 3 && compChoice == 2) {
                        playerWins++;
                        System.out.println("You won");
                    }
                }
            }

            System.out.println("Number of ties: " + ties);
            System.out.println("Number of player wins: " + playerWins);
            System.out.println("Number of computer wins " + compWins);

            if (playerWins == compWins) {
                System.out.println("WE TIED");
            }
            else if (playerWins > compWins) {
                System.out.println("YOU (USER) WON!");
            }
            else {
                System.out.println("THE COMPUTER WON");
            }

            System.out.println("Do you want to play again? (y/n)");
            playAgain = myScanner.next();
            if (playAgain.equals("n") || playAgain.equals("N")) {
                System.out.println("Thanks for playing!");
            }
        }
    }
}
