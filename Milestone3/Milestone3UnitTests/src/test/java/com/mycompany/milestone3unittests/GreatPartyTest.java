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
public class GreatPartyTest {
    
    GreatParty party = new GreatParty();
    
    public GreatPartyTest() {
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

// below isn't sufficient; a proper list of test cases is in the reading    
    @Test
    public void test30False() {
        assertFalse(party.greatParty(30, false));
    }
    
    @Test
    public void test50False() {
        assertTrue(party.greatParty(50, true));
    }
    
    @Test
    public void test70True() {
        assertTrue(party.greatParty(70, true));
    }
    
}
