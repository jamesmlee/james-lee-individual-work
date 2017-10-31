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
public class Calculations{
    
    public void doCalculations(int counter, int number, int numFactors, int factorSum) {
        
        while (counter < number) {
            counter++;
            if (number % counter == 0) {
                numFactors++;
                factorSum += counter;
                System.out.println(counter);
            }
        }
    }
}
