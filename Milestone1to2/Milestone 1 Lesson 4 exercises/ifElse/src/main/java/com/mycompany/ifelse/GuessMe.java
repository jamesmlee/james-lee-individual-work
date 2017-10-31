/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ifelse;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int myInt = 36;
        int yourGuess;
        System.out.println("I have chosen a number. Bet you can't guess it!");
        
        yourGuess = myScanner.nextInt();
        
        if (yourGuess == myInt) {
            System.out.println("Wow, nice guess! That was it!");
        }
        else if (yourGuess < myInt) {
            System.out.println("Ha, nice try - too low! I chose " + myInt);
        }
        else {
            System.out.println("Too bad, way too high. I chose " + myInt);
        }
    }
}
