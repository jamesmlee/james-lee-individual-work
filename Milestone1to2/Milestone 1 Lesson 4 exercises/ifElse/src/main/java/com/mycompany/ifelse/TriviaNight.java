/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ifelse;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int answer1;
        int answer2;
        int answer3;
        int correctAnswers = 0;
        
        System.out.println("When did the Liberty Bell get its name?" +
            " 1. when it was made, in 1701 " + " 2. when it rang on July 4, 1776 "
            + " 3. in the 19th century " + " 4. none of the above ");
        answer1 = myScanner.nextInt();
        if (answer1 == 3) {
            correctAnswers++;
        }
        
        System.out.println("The Daniel Boon museum at the home where he died can best be described how?"
                + " 1. log cabin in Kentucky " + " clapboard house in Tennessee " +
                " Georgian-style home in Missouri " + " brick house in Arkansas ");
        answer2 = myScanner.nextInt();
        if (answer2 == 3) {
            correctAnswers++;
        }
        
        System.out.println("Which of these characters turned 40 years old in 1990?");
        answer3 = myScanner.nextInt();
        if (answer3 == 1) {
            correctAnswers++;
        }
        System.out.println("You got " + correctAnswers + " correct");
    }
}
