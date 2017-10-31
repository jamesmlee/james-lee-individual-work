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
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import com.sg.superherosightings.model.SuperPersonOrganization;
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
public class SuperPersonOrganizationDaoTest {

    private static SuperPersonOrganizationDao superPersonOrganizationDao;
    private static SuperPersonDao superPersonDao;
    private static OrganizationDao organizationDao;
    private static AddressDao addressDao;
    private static LocationDao locationDao;

    private static CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    public SuperPersonOrganizationDaoTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonOrganizationDao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
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
    public void addGetDeleteSuperPersonOrganization() {
        //arrange
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        sp = superPersonDao.createSuperPerson(sp);

        Address add = new Address();
        add.setStreet("123 Fake Street");
        add.setCity("Faketown");
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

        Organization org = new Organization();
        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(loc);
        org = organizationDao.createOrganization(org);

        SuperPersonOrganization spo = new SuperPersonOrganization();
        spo.setSuperPerson(sp);
        spo.setOrganization(org);
        spo = superPersonOrganizationDao.createSuperPersonOrganization(spo);
        SuperPersonOrganization fromDb = superPersonOrganizationDao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId());

        String result = c.compareObjects(spo, fromDb);
        assertEquals(result, "");

        superPersonOrganizationDao.deleteSuperPersonOrganization(superPersonOrganizationDao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId()));
        assertNull(superPersonOrganizationDao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId()));

    }

    @Test
    public void testGetAllSuperPersonOrganizations() {
        SuperPersonOrganization spo1 = new SuperPersonOrganization();
        SuperPersonOrganization spo2 = new SuperPersonOrganization();

        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Saddam Hussein");
        superPerson.setDescription("Super hero");
        superPerson.setIsGood(false);
        superPersonDao.createSuperPerson(superPerson);

        Address address = new Address();
        address.setCity("Cleveland");
        address.setState("OH");
        address.setStreet("123 Hell Road");
        address.setZipcode("42069");
        addressDao.createAddress(address);

        Location location = new Location();
        location.setName("Iraq");
        location.setDescription("A utopian city");
        location.setAddress(address);
        location.setLatitude("100");
        location.setLongitude("100");
        locationDao.createLocation(location);

        Organization organization = new Organization();
        organization.setName("The Guys");
        organization.setDescription("A bunch of real ones");
        organization.setIsGood(false);
        organization.setLocation(location);
        organization.setPhone("3309067777");
        organizationDao.createOrganization(organization);

        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Saddam Hussein");
        superPerson2.setDescription("Super hero");
        superPerson2.setIsGood(false);
        superPersonDao.createSuperPerson(superPerson2);

        Address address2 = new Address();
        address2.setCity("Cleveland");
        address2.setState("OH");
        address2.setStreet("123 Hell Road");
        address2.setZipcode("42069");
        addressDao.createAddress(address2);

        Location location2 = new Location();
        location2.setName("Iraq");
        location2.setDescription("A utopian city");
        location2.setAddress(address2);
        location2.setLatitude("100");
        location2.setLongitude("100");
        locationDao.createLocation(location2);

        Organization organization2 = new Organization();
        organization2.setName("The Guys");
        organization2.setDescription("A bunch of real ones");
        organization2.setIsGood(false);
        organization2.setLocation(location2);
        organization2.setPhone("3309067777");
        organizationDao.createOrganization(organization2);

        spo1.setSuperPerson(superPerson);
        spo1.setOrganization(organization);
        spo1 = superPersonOrganizationDao.createSuperPersonOrganization(spo1);

        spo2.setSuperPerson(superPerson2);
        spo2.setOrganization(organization2);
        spo2 = superPersonOrganizationDao.createSuperPersonOrganization(spo2);

        List<SuperPersonOrganization> superPersonOrganizations = superPersonOrganizationDao.getAllSuperPersonOrganizations(0, 10);

        String result1 = c.compareObjects(spo1, superPersonOrganizations.get(0));
        String result2 = c.compareObjects(spo1, superPersonOrganizations.get(1));
        String result3 = c.compareObjects(spo2, superPersonOrganizations.get(0));
        String result4 = c.compareObjects(spo2, superPersonOrganizations.get(1));

        assertEquals(2, superPersonOrganizations.size());
        assertTrue(result1.equals("") || result3.equals(""));
        assertTrue(result2.equals("") || result4.equals(""));
    }

}
