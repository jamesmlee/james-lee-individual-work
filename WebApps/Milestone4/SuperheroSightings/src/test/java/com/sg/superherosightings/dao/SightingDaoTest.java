/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.helpers.CompareObjects;
import com.sg.superherosightings.helpers.ApplicationContextHelper;
import com.sg.superherosightings.helpers.TearDownHelper;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoTest {

    public SightingDaoTest() {
    }

    private static SightingDao sightingDao;
    private static LocationDao locationDao;
    private static AddressDao addressDao;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);

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

    @Test
    public void addGetDeleteSighting() {
        //arrange
        Address add = new Address();
        add.setCity("Faketown");
        add.setStreet("123 Fake Street");
        add.setState("OX");
        add.setZipcode("12345");
        add = addressDao.createAddress(add);

        Location loc = new Location();
        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add);
        loc.setLatitude("23, 32");
        loc.setLongitude("23, 23");
        loc = locationDao.createLocation(loc);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("20171022", formatter));
        sighting.setLocation(loc);
        sighting.setDescription("SuperPerson was learning to code....");

        //this actually fills the field of sp
        sighting = sightingDao.createSighting(sighting); //check order of sping fields

//        Integer sightingId = sighting.getSightingId();
        Sighting fromDb = sightingDao.getSightingById(sighting.getSightingId());
        String result = c.compareObjects(sighting, fromDb);
        assertEquals(result, "");

        sightingDao.deleteSighting(sightingDao.getSightingById(sighting.getSightingId()));

        assertNull(sightingDao.getSightingById(sighting.getSightingId()));

    }

    @Test
    public void testGetAllSightings() {
        Address add = new Address();
        add.setCity("Faketown");
        add.setStreet("123 Fake Street");
        add.setState("OX");
        add.setZipcode("12345");
        add = addressDao.createAddress(add);

        Location loc = new Location();
        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add);
        loc.setLatitude("23, 32");
        loc.setLongitude("23, 23");
        loc = locationDao.createLocation(loc);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("20171022", formatter));
        sighting.setLocation(loc);
        sighting.setDescription("SuperPerson was learning to code....");

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("20171022", formatter));
        sighting2.setLocation(loc);
        sighting2.setDescription("SuperPerson was learning to code....");

        Sighting createdSighting1 = sightingDao.createSighting(sighting);
        Sighting createdSighting2 = sightingDao.createSighting(sighting2);

        //Integer numInDb = sightingDao.getAllSightinges(0, Integer.MAX_VALUE).size();
        List<Sighting> sightings = sightingDao.getAllSightings(0, 10);

        String result1 = c.compareObjects(createdSighting1, sightings.get(0));
        String result2 = c.compareObjects(createdSighting2, sightings.get(0));
        String result3 = c.compareObjects(createdSighting1, sightings.get(1));
        String result4 = c.compareObjects(createdSighting2, sightings.get(1));

        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));

    }

    @Test
    public void testUpdateSighting() {
        Address add = new Address();
        add.setCity("Faketown");
        add.setStreet("123 Fake Street");
        add.setState("OX");
        add.setZipcode("12345");
        add = addressDao.createAddress(add);

        Location loc = new Location();
        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add);
        loc.setLatitude("23, 32");
        loc.setLongitude("23, 23");
        loc = locationDao.createLocation(loc);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("20171022", formatter));
        sighting.setLocation(loc);
        sighting.setDescription("SuperPerson was learning to code....");
        sighting = sightingDao.createSighting(sighting);

        sighting.setDate(LocalDate.parse("20160121", formatter));
        sighting.setLocation(loc);
        sighting.setDescription("SuperPerson wasn't learnning to code");
        Sighting updated = sightingDao.updateSighting(sighting);

        String result = c.compareObjects(sighting, updated);

        assertEquals(result, "");
    }

    @Test
    public void testGetAllSightingsByDate() {

        Address add1 = new Address();
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");


        Address add2 = new Address();
        add2.setStreet("123 Fake Street");
        add2.setCity("Faketown");
        add2.setState("OX");
        add2.setZipcode("12345");

        Address addedAddress1 = addressDao.createAddress(add1);
        Address addedAddress2 = addressDao.createAddress(add2);

        Location loc1 = new Location();
        loc1.setDescription("Cool place for cool mutants");
        loc1.setName("The Software Guild");
        loc1.setAddress(addedAddress1);
        loc1.setLatitude("23, 23");
        loc1.setLongitude("23, 23");
        //arrange

        Location loc2 = new Location();
        loc2.setLocationId(2);
        loc2.setDescription("The home of Pat Toner");
        loc2.setName("The Software Guild");
        loc2.setAddress(addedAddress2);
        loc2.setLatitude("223, 32");
        loc2.setLongitude("233, 23");

        Location addedLoc1 = locationDao.createLocation(loc1);
        Location addedLoc2 = locationDao.createLocation(loc2);
        
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("20171022", formatter));
        sighting1.setLocation(addedLoc1);
        sighting1.setDescription("SuperPerson was learning to code....");
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("20171022", formatter));
        sighting2.setLocation(addedLoc2);
        sighting2.setDescription("SuperPerson was cruising....");

        Sighting createdSighting1 = sightingDao.createSighting(sighting1);
        Sighting createdSighting2 = sightingDao.createSighting(sighting2);

        List<Sighting> sightingsList = sightingDao.
                getAllSightingsByDate(LocalDate.parse("20171022", formatter), 0, 10);

        assertEquals(2, sightingsList.size());

        for (Sighting currentSighting : sightingsList) {
            
            String result1 = c.compareObjects(createdSighting1, currentSighting);
            String result2 = c.compareObjects(createdSighting2, currentSighting);
            
            assertTrue(result1.equals("") ^ result2.equals(""));
        }
    }

}
