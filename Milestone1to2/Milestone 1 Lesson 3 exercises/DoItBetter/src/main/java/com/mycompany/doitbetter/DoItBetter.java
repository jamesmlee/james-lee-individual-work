/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doitbetter;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class DoItBetter {
    public static void main(String[] args) {
          Scanner myScanner = new Scanner(System.in);
          int miles;
          int hotdogs;
          int languages;
          
          System.out.println("How many miles can you run?");
          miles = myScanner.nextInt();
          System.out.println("");
          System.out.println("I can run " + (miles * 2 + 1) + " miles");
          
          System.out.println("How many hotdogs can you eat?");
          hotdogs = myScanner.nextInt();
          System.out.println("");
          System.out.println("I can eat " + (hotdogs * 2 + 1) + " hotdogs");
          
          System.out.println("How many languages do you know?");
          languages = myScanner.nextInt();
          System.out.println("");
          System.out.println("I know " + (languages * 2 + 1) + " languages");
    }
    
}
