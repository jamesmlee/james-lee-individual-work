/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arrays;

/**
 *
 * @author James
 */
public class FruitsBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};

        int numOranges = 0;
        int numApples = 0;
        
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].equals("Orange")) {
                numOranges++;
            }
            else if (fruit[i].equals("Apple")) {
                numApples++;
            }
        }
        System.out.println("Number of Apples: " + numApples);
        System.out.println("Number of Oranges: " + numOranges);

// 2nd step        
        String[] apples = new String[numApples];
        String[] oranges = new String[numOranges];
        
        for (int i = 0; i < numOranges; i++) {
            if (fruit[i].equals("Orange")) {
                oranges[i] = "Orange";
            }
        }
        for (int i = 0; i < numApples; i++) {    
            if (fruit[i].equals("Apple")) {
                apples[i] = "Apple";
            }        
        }
// 3rd step
        System.out.println("Total # of fruit: " + (apples.length + oranges.length));
        System.out.println("Total # of oranges: " + oranges.length);
        System.out.println("Total # of apples: " + apples.length);
    }
}
