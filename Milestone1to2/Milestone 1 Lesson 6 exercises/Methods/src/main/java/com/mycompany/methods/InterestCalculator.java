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

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("How much $ do you want to invest");
        float principal = myScanner.nextFloat();
        System.out.println("What is the annual interest rate");
        float annualRate = myScanner.nextFloat();
        System.out.println("How many years do you want to invest for?");
        int numYears = myScanner.nextInt();
        
        float quarterlyRate = annualRate / 4;
        float interestAccrued = 0;
        float currentBalance = principal;
        int yearCounter = 0;
        
        float currPeriodInt = 0;
        
        for (int quarterCounter = 1; quarterCounter <= numYears * 4; quarterCounter++) {
            currPeriodInt = ((currentBalance * (1 + (quarterlyRate/100))) - currentBalance);
            interestAccrued += currPeriodInt;
            currentBalance += currPeriodInt;
            
            if (quarterCounter % 4 == 0) {
                yearCounter++;
                System.out.println("It is the end of year " + yearCounter);                
                System.out.println("Your balance at the beginning of the year was " + (currentBalance - interestAccrued));
                System.out.println("You've accrued " + interestAccrued + " in interest this year");
                System.out.println("Your current balance is " + currentBalance);
                interestAccrued = 0;
            }
        }
    
    }
}
