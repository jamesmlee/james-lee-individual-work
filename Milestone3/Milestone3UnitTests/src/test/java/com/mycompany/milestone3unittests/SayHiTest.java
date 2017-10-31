/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone3unittests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class SayHiTest {
    
    private SayHi sayHi = new SayHi();
    
    public SayHiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHi method, of class SayHi.
     */
    @Test
    public void testBob() {
        String expectedResult = "Hello Bob!";
// assertEquals takes 2 params: 1. expected result 2. what actually came back        
        assertEquals(expectedResult, sayHi.sayHi("Bob"));
    }
    
    @Test
    public void testAlice() {
        String expectedResult = "Hello Alice!";
        assertEquals(expectedResult, sayHi.sayHi("Alice"));
    }
    
    @Test
    public void testX() {
        String expectedResult = "Hello X!";
        assertEquals(expectedResult, sayHi.sayHi("X"));
    }
}
