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
public class CodeAlong {
    public static void main(String[] args) {
        int[] numbers = { 3, 7, 2, 4, 7, 12 };
        int sum = 0;
        int min = numbers[0];
        int max = numbers[0];
        
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if (numbers[i] < min) {
                min = numbers[i];
            }
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
    }
}
