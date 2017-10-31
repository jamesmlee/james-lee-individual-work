/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.AddressDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperPersonDao;
import com.sg.superherosightings.dao.SuperPersonOrganizationDao;
import com.sg.superherosightings.dao.SuperPersonPowerDao;
import com.sg.superherosightings.dao.SuperPersonSightingDao;
import com.sg.superherosightings.helpers.ApplicationContextHelper;
import com.sg.superherosightings.helpers.CompareObjects;
import com.sg.superherosightings.helpers.CreateAndAddObjects;
import com.sg.superherosightings.helpers.TearDownHelper;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonOrganization;
import com.sg.superherosightings.model.SuperPersonPower;
import com.sg.superherosightings.model.SuperPersonSighting;
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
public class SuperPersonServiceTest {

    private static SuperPersonService superPersonService;
    private static OrganizationService organizationService;
    private static PowerService powerService;
    private static SightingService sightingService;
    private static LocationService locationService;
    private static AddressService addressService;
    private static SuperPersonOrganizationDao superPersonOrganizationDao;
    private static SuperPersonPowerDao superPersonPowerDao;
    private static SuperPersonSightingDao superPersonSightingDao;

    private static CreateAndAddObjects create = new CreateAndAddObjects();
    private static CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public SuperPersonServiceTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonService = ctx.getBean("superPersonService", SuperPersonService.class);
        organizationService = ctx.getBean("organizationService", OrganizationService.class);
        powerService = ctx.getBean("powerService", PowerService.class);
        locationService = ctx.getBean("locationService", LocationService.class);
        addressService = ctx.getBean("addressService", AddressService.class);
        sightingService = ctx.getBean("sightingService", SightingService.class);
        superPersonOrganizationDao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonSightingDao = ctx.getBean("superPersonSightingDao", SuperPersonSightingDao.class);

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
    public void testAddGetDeleteSuperPerson() {
// Arrange - set up your givens
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);

// Act - call the method you're testing
        sp = superPersonService.createSuperPerson(sp);
        SuperPerson fromDb = superPersonService.getSuperPersonById(sp.getSuperPersonId());
        String result = c.compareObjects(sp, fromDb);

// Assert - assert that the actual matches the expected
        assertEquals(result, "");

        superPersonService.deleteSuperPerson(superPersonService.getSuperPersonById(sp.getSuperPersonId()));
        assertNull(superPersonService.getSuperPersonById(sp.getSuperPersonId()));

    }

    @Test
    public void updateSuperPerson() {
// Arrange 
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        SuperPerson added = superPersonService.createSuperPerson(sp);

        added.setName("Trumpus");
        added.setDescription("builds walls");
        added.setIsGood(false);
// Act
        SuperPerson updated = superPersonService.updateSuperPerson(added);
        String result = c.compareObjects(added, updated);
// Assert
        assertEquals(result, "");

    }

    @Test
    public void getAllSuperPersons() {
// Arrange  
        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);

        SuperPerson sp2 = new SuperPerson();
        sp2.setName("Trumpus");
        sp2.setDescription("builds walls");
        sp2.setIsGood(false);

// next 2 rows commented out b/c we removed dummy data from DB        
// want number of SuperPersones in DB, then use as OFFSET in Act        
//        Integer numInDb = superPersonService.getAllSuperPersones(0, Integer.MAX_VALUE).size();
        SuperPerson createdSP1 = superPersonService.createSuperPerson(sp1);
        SuperPerson createdSP2 = superPersonService.createSuperPerson(sp2);
// Act
        List<SuperPerson> superPersons = superPersonService.getAllSuperPersons(0, 100);
// Assert
        assertEquals(2, superPersons.size());

        String result1 = c.compareObjects(createdSP1, superPersons.get(0));
        String result2 = c.compareObjects(createdSP2, superPersons.get(0));
        String result3 = c.compareObjects(createdSP1, superPersons.get(1));
        String result4 = c.compareObjects(createdSP2, superPersons.get(1));
        // don't know what order they'll be in
        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));
    }

    @Test
    public void testGetAllSuperPersonsBySighting() {
        Address add1 = new Address();
        add1.setCity("Faketown");
        add1.setStreet("123 Fake Street");
        add1.setState("OX");
        add1.setZipcode("12345");
        add1 = addressService.createAddress(add1);

        Location loc1 = new Location();
        loc1.setDescription("Cool place for cool mutants!");
        loc1.setName("The Software Guild");
        loc1.setAddress(add1);
        loc1.setLatitude(" 23, 23");
        loc1.setLongitude(" 23, 23");
        loc1 = locationService.createLocation(loc1);

        Address add2 = new Address();
        add2.setCity("Akron");
        add2.setStreet("123 Quaran Rd");
        add2.setState("OX");
        add2.setZipcode("12345");
        add2 = addressService.createAddress(add1);

        Location loc2 = new Location();
        loc2.setDescription("Cool place for cool mutants!");
        loc2.setName("Taliban HQ");
        loc2.setAddress(add2);
        loc2.setLatitude(" 23, 23");
        loc2.setLongitude(" 23, 23");
        loc2 = locationService.createLocation(loc1);

        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Mario Lemieux");
        sp1.setDescription("The Best");
        sp1.setIsGood(true);

        sp1 = superPersonService.createSuperPerson(sp1);

        SuperPerson sp2 = new SuperPerson();
        sp2.setName("Jaromir Jagr");
        sp2.setDescription("The Next Best");
        sp2.setIsGood(true);

        sp2 = superPersonService.createSuperPerson(sp2);
        SuperPerson sp3 = new SuperPerson();
        sp3.setName("Wayne Gretzky");
        sp3.setDescription("The Great One");
        sp3.setIsGood(true);

        sp3 = superPersonService.createSuperPerson(sp3);

        Sighting s1 = new Sighting();
        s1.setDate(LocalDate.parse("1992-01-01"));
        s1.setLocation(loc1);
        s1.setDescription("Penguins");
        s1 = sightingService.createSighting(s1);

        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("1986-01-01"));
        s2.setLocation(loc2);
        s2.setDescription("Oilers");

        s2 = sightingService.createSighting(s2);

        SuperPersonSighting sps1 = new SuperPersonSighting();
        sps1.setSuperPerson(sp1);
        sps1.setSighting(s1);
        sps1 = superPersonSightingDao.createSuperPersonSighting(sps1);

        SuperPersonSighting sps2 = new SuperPersonSighting();
        sps2.setSuperPerson(sp2);
        sps2.setSighting(s1);
        sps2 = superPersonSightingDao.createSuperPersonSighting(sps2);

        SuperPersonSighting sps3 = new SuperPersonSighting();
        sps3.setSuperPerson(sp3);
        sps3.setSighting(s2);
        sps3 = superPersonSightingDao.createSuperPersonSighting(sps3);

        assertEquals(2, superPersonService.getAllSuperPersonsBySighting(s1, 0, 10).size());
        assertEquals(1, superPersonService.getAllSuperPersonsBySighting(s2, 0, 10).size());
    }

    /**
     * Test of getAllSuperPersonsBySightingLocation method, of class
     * SuperPersonService.
     */
    @Test
    public void testGetAllSuperPersonsBySightingLocation() {
        //create some super persons
        SuperPerson sp1 = create.createAndAddSuperPerson();
        SuperPerson sp2 = create.createAndAddSuperPerson();
        SuperPerson sp3 = create.createAndAddSuperPerson();

        // create some sightings
        Sighting s1 = create.createAndAddSighting();
        Sighting s2 = create.createAndAddSighting();

        //associate them in bridge
        superPersonService.addSuperPersonToSighting(sp1, s1);
        superPersonService.addSuperPersonToSighting(sp2, s1);
        superPersonService.addSuperPersonToSighting(sp3, s2);

        //run the method to get them back as lists
        List<SuperPerson> superPersonsBySightingLocation1 = superPersonService.
                getAllSuperPersonsBySightingLocation(s1.getLocation(), 0, 10);
        List<SuperPerson> superPersonsBySightingLocation2 = superPersonService.
                getAllSuperPersonsBySightingLocation(s2.getLocation(), 0, 10);

        assertEquals(2, superPersonsBySightingLocation1.size());
        assertEquals(1, superPersonsBySightingLocation2.size());

        for (SuperPerson currentSuperPerson : superPersonsBySightingLocation1) {

            String result1 = c.compareObjects(sp1, currentSuperPerson);
            String result2 = c.compareObjects(sp2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(sp3, superPersonsBySightingLocation2.get(0));
        assertEquals("", result3);
    }

    /**
     * Test of getAllSuperPersonsByOrganization method, of class
     * SuperPersonService.
     */
    @Test
    public void testGetAllSuperPersonsByOrganization() {
        //make some people
        SuperPerson sp1 = create.createAndAddSuperPerson();
        SuperPerson sp2 = create.createAndAddSuperPerson();
        SuperPerson sp3 = create.createAndAddSuperPerson();

        //make some orgs
        Organization o1 = create.createAndAddOrganization();
        Organization o2 = create.createAndAddOrganization();

        //associate them 
        superPersonService.addSuperPersonToOrganization(sp1, o1);
        superPersonService.addSuperPersonToOrganization(sp2, o1);
        superPersonService.addSuperPersonToOrganization(sp3, o2);

        //pull them out lists
        List<SuperPerson> listOfSP1 = superPersonService.getAllSuperPersonsByOrganization(o1, 0, 10);
        List<SuperPerson> listOfSP2 = superPersonService.getAllSuperPersonsByOrganization(o2, 0, 10);

        //assertions
        assertEquals(2, listOfSP1.size());
        assertEquals(1, listOfSP2.size());

        for (SuperPerson currentSuperPerson : listOfSP1) {

            String result1 = c.compareObjects(sp1, currentSuperPerson);
            String result2 = c.compareObjects(sp2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(sp3, listOfSP2.get(0));
        assertEquals("", result3);
    }

    /**
     * Test of addSuperPersonToPower method, of class SuperPersonService.
     */
    @Test
    public void testAddSuperPersonToPower_SuperPerson_Power() {
        SuperPerson sp = create.createAndAddSuperPerson();

        Power pow = new Power();
        pow.setName("Super Sneezing");
        powerService.createPower(pow);

        SuperPersonPower spp = superPersonService.addSuperPersonToPower(sp, pow);
        SuperPersonPower fromDao = superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId());

        String result = c.compareObjects(spp, fromDao);
        assertEquals("", result);
    }

    /**
     * Test of deleteSuperPersonFromPower method, of class SuperPersonService.
     */
    @Test
    public void testDeleteSuperPersonFromPower_SuperPerson_Power() {
        SuperPerson sp = create.createAndAddSuperPerson();
        Power pow = create.createAndAddPower();

        SuperPersonPower spp = superPersonService.addSuperPersonToPower(sp, pow);
        Integer sppId = spp.getSuperPersonPowerId();
        superPersonService.deletePowerFromSuperPerson(sp, pow);

        assertNull(superPersonPowerDao.getSuperPersonPowerById(sppId));
    }

    @Test
    public void testAddSuperPersonToOrganization_SuperPerson_Organization() {
        SuperPerson sp = create.createAndAddSuperPerson();

        Address add = new Address();
        add.setStreet("123 Fake Street");
        add.setCity("Faketown");
        add.setState("OX");
        add.setZipcode("12345");
        add = addressService.createAddress(add);

        Location loc = new Location();
        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add);
        loc.setLatitude(" 23, 23");
        loc.setLongitude(" 23, 23");
        loc = locationService.createLocation(loc);
        Organization org = new Organization();
        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(loc);
        org = organizationService.createOrganization(org);

        SuperPersonOrganization spo = superPersonService.addSuperPersonToOrganization(sp, org);
        SuperPersonOrganization fromDao = superPersonOrganizationDao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId());

        String result = c.compareObjects(spo, fromDao);
        assertEquals("", result);
    }

    /**
     * Test of deleteSuperPersonFromOrganization method, of class
     * SuperPersonService.
     */
    @Test
    public void testDeleteSuperPersonFromOrganization_SuperPerson_Organization() {
        SuperPerson sp = create.createAndAddSuperPerson();
        Organization org = create.createAndAddOrganization();

        SuperPersonOrganization spo = superPersonService.addSuperPersonToOrganization(sp, org);
        Integer spoId = spo.getSuperPersonOrganizationId();
        superPersonService.deleteOrganizationFromSuperPerson(sp, org);

        assertNull(superPersonOrganizationDao.getSuperPersonOrganizationById(spoId));
    }

    /**
     * Test of addSuperPersonToSighting method, of class SuperPersonService.
     */
    @Test
    public void testAddSuperPersonToSighting_SuperPerson_Sighting() {
        SuperPerson sp = create.createAndAddSuperPerson();
        Sighting s = create.createAndAddSighting();
        SuperPersonSighting sps = superPersonService.addSuperPersonToSighting(sp, s);

        SuperPersonSighting fromDb = superPersonSightingDao.
                getSuperPersonSightingById(sps.getSuperPersonSightingId());

        String result = c.compareObjects(sps, fromDb);
        assertEquals("", result);
    }

    /**
     * Test of deleteSuperPersonFromSighting method, of class
     * SuperPersonService.
     */
    @Test
    public void testDeleteSuperPersonFromSighting_SuperPerson_Sighting() {
        SuperPerson sp = create.createAndAddSuperPerson();
        Sighting si = create.createAndAddSighting();

        SuperPersonSighting sps = superPersonService.addSuperPersonToSighting(sp, si);
        Integer spsId = sps.getSuperPersonSightingId();
        superPersonService.deleteSightingFromSuperPerson(sp, si);

        assertNull(superPersonOrganizationDao.getSuperPersonOrganizationById(spsId));
    }

}
