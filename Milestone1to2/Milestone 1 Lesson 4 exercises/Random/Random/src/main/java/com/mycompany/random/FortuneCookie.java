/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.random;

/**
 *
 * @author James
 */
import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        String str1 = "Those aren’t the droids you’re looking for.";
        String str2 = "Never go in against a Sicilian when death is on the line!";
        String str3 = "Goonies never say die.";
        String str4 = "With great power there must also come — great responsibility.";
        String str5 = "Never argue with the data.";
        String str6 = "Try not. Do, or do not. There is no try.";
        String str7 = "You are a leaf on the wind, watch how you soar.";
        String str8 = "Do absolutely nothing, and it will be everything that you thought it could be.";
        String str9 = "Kneel before Zod.";
        String str10 = "Make it so.";
        
        Random randomizer = new Random();
        int x = randomizer.nextInt(10) + 1;
        
        if (x == 1) {
            System.out.println(str1);
        } else if (x == 2) {
            System.out.println(str2);
        } else if (x == 3) {
            System.out.println(str3);
        } else if (x == 4) {
            System.out.println(str4);
        } else if (x == 5) {
            System.out.println(str5);
        } else if (x == 6) {
            System.out.println(str6);
        } else if (x == 7) {
            System.out.println(str7);
        } else if (x == 8) {
            System.out.println(str8);
        } else if (x == 9) {
            System.out.println(str9);
        } else if (x == 10) {
            System.out.println(str10);
        } 
    }
}
