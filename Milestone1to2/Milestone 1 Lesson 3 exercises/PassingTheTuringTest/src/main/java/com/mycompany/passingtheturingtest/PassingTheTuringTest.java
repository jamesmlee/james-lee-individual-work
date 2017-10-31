/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.passingtheturingtest;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class PassingTheTuringTest {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("What is your name?");
        String userName = myScanner.nextLine();
        String aiName = "Bob";
        System.out.println("Hi " + userName + ", my name is " + aiName);
        
        System.out.println("What's your favorite color?");
        String favColor = myScanner.nextLine();
        System.out.println(favColor + " is a nice color");
// stopping here to work on other problems vs. repitition
    }
}
