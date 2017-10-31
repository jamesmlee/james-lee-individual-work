/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methods;

/**
 *
 * @author James
 */
public class Scoping {
    public static void main(String[] args) {
        int age = 42;
// the sout below is in the same block ... that's fine 
        System.out.println(age);
    
// the loop below can see out to main
        for (int i = 0; i < 5; i++) {
            System.out.println(age);
        }
        if (age < 18) {
            int yearsToWait = age - 18;
/* the for loop and if statement are siblings, so the i below is invalid            
            System.out.println(i);
*/
        }
/* we're inside main; can't look into the if statement        
        System.out.println(yearsToWait);
*/
    }
}
