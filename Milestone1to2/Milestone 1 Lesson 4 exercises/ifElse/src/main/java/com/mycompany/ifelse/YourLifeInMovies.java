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

public class YourLifeInMovies {
    public static void main(String[] args) {
        int birthYear;
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("In what year were you born?");
        birthYear = myScanner.nextInt();
        
        if (birthYear < 1965) {
            System.out.println("MASH has been around for almost a half century!");
        }
        if (birthYear < 1975) {
            System.out.println("The original Jurassic Park release is closer to the lunar landing than today.");
        }
        if (birthYear < 1985) {
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        if (birthYear < 1995) {
            System.out.println("The first Harry Potter came out over 15 years ago.");
        }
        if (birthYear < 2005) {
            System.out.println("Pixar's 'Up' came out half a decade ago");
        }
    }
}
