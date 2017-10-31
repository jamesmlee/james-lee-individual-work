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
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonPower;
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
public class SuperPersonPowerDaoTest {

    private static SuperPersonPowerDao superPersonPowerDao;
    private static SuperPersonDao superPersonDao;
    private static PowerDao powerDao;
    private static CompareObjects compare = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    public SuperPersonPowerDaoTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        powerDao = ctx.getBean("powerDao", PowerDao.class);
        tdh.clearTables();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        tdh.clearTables();
    }

    /**
     * Test of createSuperPersonPower method, of class SuperPersonPowerDao.
     */
    @Test
    public void addGetDeleteSuperPersonPower() {
        //arrange

        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        sp = superPersonDao.createSuperPerson(sp);

        Power power = new Power();
        power.setName("Super Sneezing");
        power = powerDao.createPower(power);

        SuperPersonPower spp = new SuperPersonPower();
        spp.setSuperPerson(sp);
        spp.setPower(power);
        spp = superPersonPowerDao.createSuperPersonPower(spp);
        SuperPersonPower fromDb = superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId());

        String result = compare.compareObjects(spp, fromDb);
        assertEquals(result, "");

        superPersonPowerDao.deleteSuperPersonPower(superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId()));
        assertNull(superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId()));
    }

    /**
     * Test of getAllSuperPersonPoweres method, of class SuperPersonPowerDao.
     */
    @Test
    public void testGetAllSuperPersonPowers() {
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        sp = superPersonDao.createSuperPerson(sp);

        Power power = new Power();
        power.setName("Super Sneezing");
        power = powerDao.createPower(power);

        SuperPersonPower spp = new SuperPersonPower();
        spp.setSuperPerson(sp);
        spp.setPower(power);
        spp = superPersonPowerDao.createSuperPersonPower(spp);
        SuperPerson sp2 = new SuperPerson();
        sp2.setName("Ratman");
        sp2.setDescription("some rich dude with Radgets");
        sp2.setIsGood(true);
        sp2 = superPersonDao.createSuperPerson(sp2);

        Power power2 = new Power();
        power2.setName("Super Sleezing");
        power2 = powerDao.createPower(power2);

        SuperPersonPower spp2 = new SuperPersonPower();
        spp2.setSuperPerson(sp2);
        spp2.setPower(power2);
        spp2 = superPersonPowerDao.createSuperPersonPower(spp2);

        List<SuperPersonPower> superPersonPowers = superPersonPowerDao.getAllSuperPersonPowers(0, 10);
        assertEquals(2, superPersonPowers.size());

        String result1 = compare.compareObjects(spp, superPersonPowers.get(0));
        String result2 = compare.compareObjects(spp2, superPersonPowers.get(0));
        String result3 = compare.compareObjects(spp, superPersonPowers.get(1));
        String result4 = compare.compareObjects(spp2, superPersonPowers.get(1));
        // don't know what order they'll be in
        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));
    }

}
