/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methods;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class Factorizer {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int number;
        System.out.println("Give me a # to factor");
        number = myScanner.nextInt();
        System.out.println("Here are the factors of " + number + " :");
        int counter = 0;
        int numFactors = 0;
        int factorSum = 0;
        
        while (counter < number) {
            counter++;
            if (number % counter == 0) {
                numFactors++;
                factorSum += counter;
                System.out.println(counter);
            }
        }
        if ((factorSum - number) == number) {
            System.out.println(number + " is a perfect #");
        } else {
            System.out.println(number + " is not a perfect #");
        }
        if (numFactors == 2) {
            System.out.println(number + " is prime");
        } else {
            System.out.println(number + " is not prime");
        }
    }
}
