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
public class AddressDaoTest {

    //address dao declaration
    private static AddressDao dao;
//    private static LogManager logger = LogManager.getLogManager();
    private static CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    public AddressDaoTest() {
    }

    @Transactional
    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        dao = ctx.getBean("addressDao", AddressDao.class);
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
     * Test of createAddress method, of class AddressDao.
     */
    @Test
    public void addGetDeleteAddress() {
        //arrange
        Address add = new Address();
        add.setCity("Faketown");
        add.setStreet("123 Fake Street");
        add.setState("OX");
        add.setZipcode("12345");

        //this actually fills the field of add
        add = dao.createAddress(add); //check order of adding fields

//        Integer addressId = add.getAddressId();
        Address fromDb = dao.getAddressById(add.getAddressId());

        String result = c.compareObjects(add, fromDb);
        assertEquals(result, "");

//        assertEquals(add, fromDb);
        dao.deleteAddress(dao.getAddressById(add.getAddressId()));
        assertNull(dao.getAddressById(add.getAddressId()));

    }

    /**
     * Test of getAllAddresses method, of class AddressDao.
     */
    @Test
    public void testGetAllAddresses() {
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

        Address createdAddress1 = dao.createAddress(add1);
        Address createdAddress2 = dao.createAddress(add2);

        //Integer numInDb = dao.getAllAddresses(0, Integer.MAX_VALUE).size();
        List<Address> addresses = dao.getAllAddresses(0, 10);

        assertEquals(2, addresses.size());

        String result1 = c.compareObjects(createdAddress1, addresses.get(0));
        String result2 = c.compareObjects(createdAddress2, addresses.get(0));
        String result3 = c.compareObjects(createdAddress1, addresses.get(1));
        String result4 = c.compareObjects(createdAddress2, addresses.get(1));

        assertTrue(result1.equals("") || result2.equals(""));

        assertTrue(result3.equals("") || result4.equals(""));

    }

    /**
     * Test of updateAddress method, of class AddressDao.
     */
    @Test
    public void testUpdateAddress() {

        Address add = new Address();
        add.setStreet("123 Fake Street");
        add.setCity("Faketown");
        add.setState("OX");
        add.setZipcode("12345");
        Address added = dao.createAddress(add);

        add.setStreet("321 Not Fake Street");
        add.setCity("Not Faketown");
        add.setState("XO");
        add.setZipcode("54321");

        //'added' is updated with setters
        //then the update method changes the original db entry
        //the 'added' should be the same as the db entry
        Address updated = dao.updateAddress(added);

        String result = c.compareObjects(added, updated);

        assertEquals(result, "");

    }

}
