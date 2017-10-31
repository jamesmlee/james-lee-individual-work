/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.minimadlibs;

/**
 *
 * @author James
 */
import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("I need a noun");
        String noun1 = myScanner.nextLine();
        System.out.println("An adjective");
        String adj1 = myScanner.nextLine();
        System.out.println("A noun");
        String noun2 = myScanner.nextLine();        
        System.out.println("A number");
        String num1 = myScanner.nextLine();
        System.out.println("An adjective");
        String adj2 = myScanner.nextLine();
        System.out.println("A plural noun");
        String noun3 = myScanner.nextLine();
        System.out.println("Another");
        String noun4 = myScanner.nextLine();
        System.out.println("Another");
        String noun5 = myScanner.nextLine();
        System.out.println("A present tense verb");
        String present = myScanner.nextLine();
        System.out.println("The same verb in past tense");
        String past = myScanner.nextLine();
        
        System.out.println(noun1 + ": the " + adj1 + " frontier. These are the voyages of the starship "
                + noun2 + ". Its " + num1 + "-year mission: to explore strange " + adj2 + " " + noun3 +
                ", to seek out " + adj2 + " " + noun4 + " and " + adj2 + " " + noun5 + ", to boldly " +
                present + " where no one has " + past + " before.");
    }
}
