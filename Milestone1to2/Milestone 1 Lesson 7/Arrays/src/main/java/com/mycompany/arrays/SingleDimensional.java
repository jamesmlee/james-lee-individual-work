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
public class SingleDimensional {
    public static void main(String[] args) {
        int[] teamScores;
        teamScores = new int[5];
        teamScores[0] = 2;
        teamScores[1] = 45;
        teamScores[2] = 4;
        teamScores[3] = 8;
        teamScores[4] = 99;

// shortcut        
        int[] golfScores = { 72, 74, 68, 71 };
            
        int currentGolfScore = golfScores[0];
// change the value at index 2
        golfScores[2] = 70;
        
        for (int i = 0; i < golfScores.length; i++) {
            System.out.println(golfScores[i]);
        }

// pull out all elements of golfScores and put it in currentScore       
        for(int currentScore : golfScores) {
            System.out.println(currentScore);
        }
    }
}
