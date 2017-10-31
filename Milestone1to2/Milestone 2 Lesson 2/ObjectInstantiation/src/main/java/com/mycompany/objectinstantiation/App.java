/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.objectinstantiation;

/**
 *
 * @author James
 */

/* old way

public class App {
    public static void main(String[] args) {
        int sum = add(5,4);
        System.out.println("The sum is " + sum);
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
}

try moving the functionality for add into a different object, Adder
in App, instantiate Adder object based on Adder class, and call a method on it
*/

public class App {
    public static void main(String[] args) {
        
        
        int sum = Adder.add(5, 4);
        
        
        
        System.out.println("The sum is " + sum);
    }
}