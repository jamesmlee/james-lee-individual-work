/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.statecapitals2;

/**
 *
 * @author James
 */
import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String, Capitals> stateCapitals = new HashMap<>();
        
        Capitals montgomery = new Capitals("Montgomery", 205764, 155.4f);
        Capitals juneau = new Capitals("Juneau", 31275, 2716.7f);
        Capitals phoenix = new Capitals("Phoenix", 1445632, 474.9f);
        Capitals littlerock = new Capitals("Little Rock", 193524, 116.2f);
        Capitals sacramento = new Capitals("Sacramento", 466488, 97.2f);
        
        stateCapitals.put("Alabama", montgomery);
        stateCapitals.put("Alaska", juneau);
        stateCapitals.put("Arizona", phoenix);
        stateCapitals.put("Arkansas", littlerock);
        stateCapitals.put("California", sacramento);
        
        System.out.println("These are the states, capitals, and pop and sq ft of the capitals: ");
        Set<String> keys = stateCapitals.keySet();
        for (String k : keys) {
            System.out.println(k + " - " + stateCapitals.get(k).getCapName() + " " 
                + stateCapitals.get(k).getPopulation() + " " + stateCapitals.get(k).getSqFootage());
        }

        System.out.println("");
        System.out.println("I can give you the states with capitals that have a population over a certain #");
        System.out.println("What is the minimum population do you want to look up?");
        Scanner myScanner = new Scanner(System.in);
        int minPop = myScanner.nextInt();
        myScanner.nextLine();
        
        System.out.println("These are the states with capitals that have a population over " + minPop + ": ");
        for (String k : keys) {
            if (stateCapitals.get(k).getPopulation() > minPop) {
                System.out.println(k);
            }
        }
/*     
        Collection<Object> capitals = stateCapitals.values();
        System.out.println("These are all the capitals: ");
        for (String v: capitals) {
            System.out.println(v);
        }
*/
    }
}
