/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestoneassignments;

import java.util.Scanner;

/**
 *
 * @author James
 */
public class HealthyHearts {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int age;
        int maxHR;
    
        System.out.println("What is your age?");
        age = myScanner.nextInt();
        System.out.println("You're " + age);
        
        maxHR = 220 - age;
        System.out.println("Your maximum heart rate should be " + maxHR + " beats per minute");
        System.out.println("Your target heart rate zone is " + Math.round(maxHR*.5) + " - " + Math.round(maxHR*.85) + " beats per minute");
    }
}
