/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.M1L3.java;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("1,024 Gigabytes is equal to one what?");
        String ans1 = myScanner.nextLine();
        
        System.out.println("In our solar system which is the only planet that rotates clockwise?");
        String ans2 = myScanner.nextLine();

        System.out.println("The largest volcano ever discovered in our solar system is located on which planet?");
        String ans3 = myScanner.nextLine();
        
        System.out.println("What is the most abundant element in the earth's atmosphere?");
        String ans4 = myScanner.nextLine();
        
        System.out.println("Wow, 1,024 Gigabytes is a " + ans3);
        System.out.println("I didn't know that the largest ever volcano was discovered on " + ans1);
        System.out.println("That's amazing that " + ans2 + " is the most abundant element in the atmosphere");
        System.out.println(ans4 + " is the only planet that rotates clockwise, neat!");
    }
}
