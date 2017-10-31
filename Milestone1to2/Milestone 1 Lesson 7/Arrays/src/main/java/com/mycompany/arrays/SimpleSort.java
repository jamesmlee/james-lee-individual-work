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
public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];

        for (int i = 0; i < 12; i++) {
            wholeNumbers[2*i] = firstHalf[i];
        }
        
        // + 1 to populate odd # indices
        for (int i = 0; i < 12; i++) {
            wholeNumbers[2*i + 1] = secondHalf[i];
        }
        
        for (int i = 0; i < wholeNumbers.length; i++) {
            System.out.println(wholeNumbers[i]);
        }
        
        // do bubble sort to arrange  them in order; 5:06 of "Using Arrays in Code"
    }
}
