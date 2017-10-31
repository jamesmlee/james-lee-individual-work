/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizer;

/**
 *
 * @author James
 */
public class Results {
    public void printResults (int factorSum, int number, int numFactors) {
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
