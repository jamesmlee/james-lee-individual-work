/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.random;

/**
 *
 * @author James
 */
import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {
    public static void main(String[] args) {
        Random randomizer = new Random();
        Scanner myScanner = new Scanner(System.in);
        int yourGuess;
        
        int myNumber = randomizer.nextInt(201) - 100;
        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!");
        yourGuess = myScanner.nextInt();
        
        if (yourGuess > myNumber) {
            System.out.println("Too high. Try again");
            yourGuess = myScanner.nextInt();
            if (yourGuess != myNumber) {
                System.out.println("Sorry, wrong again!");
            }
        }
        
        if (yourGuess < myNumber) {
            System.out.println("Too low. Try again");
            if (yourGuess != myNumber) {
            System.out.println("Sorry, wrong again!");
            }
        }
        
        if (yourGuess == myNumber) {
            System.out.println("Wow, nice guess!");
        }
    }
}
