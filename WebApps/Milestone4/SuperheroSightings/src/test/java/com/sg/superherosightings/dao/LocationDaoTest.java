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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author James
 */
public class LocationDaoTest {

    private static LocationDao locationDao;
    private static AddressDao addressDao;
    private static CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    public LocationDaoTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
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
    public void addGetDeleteLocation() {
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

        Location actualLoc = locationDao.getLocationById(loc.getLocationId());

        String result = c.compareObjects(loc, actualLoc);
        assertEquals(result, "");

        locationDao.deleteLocation(locationDao.getLocationById(loc.getLocationId()));

        assertNull(locationDao.getLocationById(loc.getLocationId()));
    }

    @Test
    public void updateLocation() {
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
        Location added = locationDao.createLocation(loc);

        loc.setDescription("Terrible place for awful coders and no lives!");
        loc.setName("The Software Guild");
        loc.setAddress(add);
        loc.setLatitude("25, 32");
        loc.setLongitude("39, 23");

        Location updated = locationDao.updateLocation(loc);

        String result = c.compareObjects(added, updated);
        assertEquals(result, "");

    }

    @Test
    public void getAllLocations() {
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

        Location createdLoc1 = locationDao.createLocation(loc);

        Location loc2 = new Location();
        loc2.setDescription("The home of Pat Toner");
        loc2.setName("The Software Guild");
        loc2.setAddress(add);
        loc2.setLatitude("223, 32");
        loc2.setLongitude("233, 23");

        Location createdLoc2 = locationDao.createLocation(loc2);

        List<Location> locations = locationDao.getAllLocations(0, 10);
        assertEquals(2, locations.size());

        String result1 = c.compareObjects(createdLoc1, locations.get(0));
        String result2 = c.compareObjects(createdLoc2, locations.get(0));
        String result3 = c.compareObjects(createdLoc1, locations.get(1));
        String result4 = c.compareObjects(createdLoc2, locations.get(1));

        assertTrue(result1.equals("") || result2.equals(""));

        assertTrue(result3.equals("") || result4.equals(""));

    }

}
