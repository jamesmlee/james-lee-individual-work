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
public class BubbleSort {
    public static void main(String[] args) {
        int[] numbers = { 3, 7, 2, 4, 7, 12 };
// to swap 2 values in the array, need a temporary holding area since computers can only do 1 thing at a time
        int temp;
        
// loop that goes through indexes in the array
        for (int i = 1; i < numbers.length; i++) {
// loop for # of passes; -1 b/c last index doesn't have a value to the right to work with
            for (int j = 0; j < numbers.length - 1; j++) {
// j is the current index in the array. compare the value to the one in the right, j+1
// if(condition), put current element in temp, replace current element with 1 next to it, replace the other with temp
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        System.out.println(numbers);
    }
}
