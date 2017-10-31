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

public class BirthStones {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int number;
        
        System.out.println("What month's birthstone do you want to know? Tell me the month as a number from 1-12");
        number = myScanner.nextInt();
        
        if (number == 1) {
            System.out.println("January - Garnet");
        } else if (number == 2) {
            System.out.println("February - Amethyst");
        } else if (number == 3) {
            System.out.println("March - Aquamarine");
        } else if (number == 4) {
            System.out.println("April - Diamond");
        } else if (number == 5) {
            System.out.println("May - Emerald");
        } else if (number == 6) {
            System.out.println("June - Pearl");
        } else if (number == 7) {
            System.out.println("July - Ruby");
        } else if (number == 8) {
            System.out.println("August - Peridot");
        } else if (number == 9) {
            System.out.println("September - Sapphire");
        } else if (number == 10) {
            System.out.println("October - Opal");
        } else if (number == 11) {
            System.out.println("November - Topaz");
        } else if (number == 12) {
            System.out.println("December - Turquoise");
        } else {
            System.out.println("ERROR; did not provide a number between 1 and 12");
        }
    }
}
