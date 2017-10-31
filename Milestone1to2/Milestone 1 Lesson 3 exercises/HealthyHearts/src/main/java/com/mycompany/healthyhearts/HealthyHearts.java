/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.healthyhearts;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int age;
        int maxHR;
    
        System.out.println("What is your age?");
        age = myScanner.nextInt();
        System.out.println(age);
        
        maxHR = 220 - age;
        System.out.println("Your maximum heart rate should be " + maxHR + " beats per minute");
        
        System.out.println("Your target heart rate zone is " + (maxHR*.5) + " - " + (maxHR*.85) + " beats per minute");
    }
}
