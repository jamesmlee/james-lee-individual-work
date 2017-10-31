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
import java.util.Random;

public class LuckySevens {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int money;
        System.out.println("How many dollars do you have?");
        money = myScanner.nextInt();
        
        int numRolls = 0;
        int maxRolls = 0;
        int max$ = money;
        
        while (money > 0) {
            Random randomizer = new Random();
            int rollResult = randomizer.nextInt(12 - 2 + 1) + 2;
// debugging                System.out.println("non 7 roll is " + rollResult);
            numRolls++;
            if (rollResult == 7) {
// debugging                System.out.println("rolled a 7 " + rollResult);
                money += 4;
                if (money > max$) {
                    max$ = money;
                    maxRolls = numRolls;
                }
            } 
            else {
                money -= 1;
            }
        }
        System.out.println("You are broke after " + numRolls + " rolls");    
        System.out.println("You should've quit after " + maxRolls + " rolls when you had $ " + max$);
    }
}
