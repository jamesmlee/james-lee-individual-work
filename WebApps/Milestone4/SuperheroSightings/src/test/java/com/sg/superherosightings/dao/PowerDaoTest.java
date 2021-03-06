/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.helpers.CompareObjects;
import com.sg.superherosightings.helpers.ApplicationContextHelper;
import com.sg.superherosightings.helpers.TearDownHelper;
import com.sg.superherosightings.model.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author James
 */
public class PowerDaoTest {

    private static TearDownHelper tearDown = new TearDownHelper();

    CompareObjects compare = new CompareObjects();

    private static PowerDao dao;

    public PowerDaoTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        tearDown.clearTables();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // ask Spring for our Dao
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        dao = ctx.getBean("powerDao", PowerDao.class);

    }

    @After
    public void tearDown() {
        tearDown.clearTables();
    }

    @Test
    public void addGetDeletePower() {
        // arrange Street, City, State, Zipcode
        Power pow = new Power();
        pow.setName("Super Sneezing");
        // act
        dao.createPower(pow);
        Power actualPower = dao.getPowerById(pow.getPowerId());
        // assert

        String result = compare.compareObjects(pow, actualPower);
        assertEquals(result, "");
        // act
        dao.deletePower(dao.getPowerById(pow.getPowerId()));
        // assert
        assertNull(dao.getPowerById(pow.getPowerId()));
    }

    @Test
    public void updatePower() {
        // arrange Street, City, State, Zipcode
        Power pow = new Power();
        pow.setName("Super Sneezing");
        Power added = dao.createPower(pow);
        added.setName("Super Sleezing");
        //act
        Power updated = dao.updatePower(added);
        String result = compare.compareObjects(added, updated);
        //assert
        assertEquals("", result);
    }

    @Test
    public void getAllPowers() {

        // arrange Street, City, State, Zipcode
        Power pow1 = new Power();
        pow1.setName("Super Sneezing");
        Power pow2 = new Power();
        pow2.setName("Super Sleezing");

        //Integer numInDb = dao.getAllPoweres(0, Integer.MAX_VALUE).size();
        Power createdPow1 = dao.createPower(pow1);
        Power createdPow2 = dao.createPower(pow2);

        //act
        List<Power> powers = dao.getAllPowers(0, 10);

        //assert
        assertEquals(2, powers.size());
        String result1 = compare.compareObjects(createdPow1, powers.get(0));
        String result2 = compare.compareObjects(createdPow2, powers.get(0));
        String result3 = compare.compareObjects(createdPow1, powers.get(1));
        String result4 = compare.compareObjects(createdPow2, powers.get(1));
        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));

    }

}
