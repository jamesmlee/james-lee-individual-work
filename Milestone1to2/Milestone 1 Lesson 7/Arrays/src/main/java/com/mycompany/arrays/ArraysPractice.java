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
public class ArraysPractice {
    public static void main(String[] args) {
        int[] myArray = new int[10];
        
        myArray[0] = 1;
        myArray[1] = 5;
        myArray[2] = 3;
        myArray[3] = 7;
        myArray[4] = 9;
        myArray[5] = 4;
        myArray[6] = 16;
        myArray[7] = 50;
        myArray[8] = 2;
        myArray[9] = 8;
/* myArray[10] = 11; below results in an error because it's outside of the array length        
        myArray[10] = 11;
*/

// take the value at index 7 and assign to variable aValue        
        int aValue = myArray[7];
        System.out.println(aValue);
        int sum = 0;
        
        for (int i = 0; i < myArray.length; i++) {
            int currentValue = myArray[i];
            System.out.println(currentValue);
            sum += currentValue;
        }
        
// line below equals first 2 lines of for loop above
// can be useful when only running through beginning to end
        for (int currentValue : myArray) {
            System.out.println(currentValue);
        }
    }
}
