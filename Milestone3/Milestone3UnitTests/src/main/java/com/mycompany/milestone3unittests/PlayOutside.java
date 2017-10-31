/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone3unittests;

/**
 *
 * @author James
 */

// They play if the temperature is between 60 and 90   
// (inclusive). Unless it is summer, then the upper limit is 100    
// instead of 90. Given an int temperature and a boolean isSummer,   
// return true if the children play and false otherwise. 

public class PlayOutside {

    public boolean playOutside(int temp, boolean isSummer) {
        int upperLimit = 90;
        
        if(isSummer) {
            upperLimit = 100;
        }
        
        if (temp >=60 && temp <= upperLimit) {
            return true;
        } else {
            return false;
        }
    }

}
