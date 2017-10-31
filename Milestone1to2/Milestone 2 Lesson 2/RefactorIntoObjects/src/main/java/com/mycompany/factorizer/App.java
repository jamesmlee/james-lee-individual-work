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
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        int number;
        System.out.println("Give me a # to factor");
        number = myScanner.nextInt();
        System.out.println("Here are the factors of " + number + " :");
        int counter = 0;
        int numFactors = 0;
        int factorSum = 0;
        
        Calculations myCalculations = new Calculations();
        myCalculations.doCalculations(counter, number, numFactors, factorSum);
        
        Results myResults = new Results();
        myResults.printResults(factorSum, number, numFactors);
    }
}