/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arrays;

/**
 *
 * @author James
 */
import java.util.Random;

public class HiddenNuts {
    public static void main(String[] args) {
        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");

	for (int i = 0; i < hidingSpots.length; i++) {
// was getting a NullPointerException before adding the if statement below
// https://stackoverflow.com/questions/218384/what-is-a-nullpointerexception-and-how-do-i-fix-it
            if (hidingSpots[i] == null) {
                hidingSpots[i] = "nothing here";
            }
            else if (hidingSpots[i].equals("Nut")) {
                System.out.println("Found it! It's in spot # " + i);
            }
        }
    }
}
