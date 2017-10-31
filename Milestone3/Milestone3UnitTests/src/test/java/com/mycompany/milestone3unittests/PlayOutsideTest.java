/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone3unittests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
// The children in Cleveland spend most of the day playing outside.
// In particular, they play if the temperature is between 60 and 90   
// (inclusive). Unless it is summer, then the upper limit is 100    
// instead of 90. Given an int temperature and a boolean isSummer,   
// return true if the children play and false otherwise.    
//  
// playOutside(70, false) → true 
// playOutside(95, false) → false
// playOutside(95, true) → true
/*    
	public boolean playOutside(int temp, boolean isSummer) {
		throw new UnsupportedOperationException("Not implemented");
	}
 */
public class PlayOutsideTest {

    public PlayOutsideTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPlayOutside70AndNotSummer() {
        // Arange
        PlayOutside p = new PlayOutside();
        // Act
        boolean result = p.playOutside(70, false);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testPlayOutside95AndNotSummer() {
        PlayOutside p = new PlayOutside();

        boolean result = p.playOutside(95, false);

        assertFalse(result);
    }

    @Test
    public void testPlayOutside95AndSummer() {
        PlayOutside p = new PlayOutside();

        boolean result = p.playOutside(95, true);

        assertTrue(result);
    }

    @Test
    public void testPlayOutsideNegativeNumber() {
        PlayOutside p = new PlayOutside();

        boolean result = p.playOutside(-100, true);

        assertFalse(result);
    }

    @Test
    public void testPlayOutsideHugeNumber() {
        PlayOutside p = new PlayOutside();

        boolean result = p.playOutside(10000, true);

        assertFalse(result);
    }

}
