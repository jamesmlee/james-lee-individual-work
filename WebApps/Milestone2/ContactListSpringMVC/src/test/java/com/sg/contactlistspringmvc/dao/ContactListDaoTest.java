/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author James
 */
public class ContactListDaoTest {

    private ContactListDao dao;

    public ContactListDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

// now the Contact info is stored in a DB and stays around even when our DAO is destroyed. 
// That means we need to reset our DB to a known good state before every test    
    @Before
    public void setUp() {
        // ask Spring for our DAO
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = ctx.getBean("contactListDao", ContactListDao.class);

        // remove all of the Contacts
        List<Contact> contacts = dao.getAllContacts();
        for (Contact currentContact : contacts) {
            dao.removeContact(currentContact.getContactId());
        }
    }

    @After
    public void tearDown() {
    }

// add a contact, get it, and delete it
    @Test
    public void testAddGetDeleteContact() {
// Arrange - set up your givens
        Contact expectedContact = new Contact();
        expectedContact.setFirstName("Steve");
        expectedContact.setLastName("Smith");
        expectedContact.setCompany("Dev IQ");
        expectedContact.setEmail("steve@deviq.com");
        expectedContact.setPhone("3305551212");

        Contact expectedContact2 = new Contact();
        expectedContact2.setFirstName("Ken");
        expectedContact2.setLastName("Smith");
        expectedContact2.setCompany("Dev IQ");
        expectedContact2.setEmail("ken@deviq.com");
        expectedContact2.setPhone("3305559999");

// Act - call the method you're testing
        dao.addContact(expectedContact);
        Contact actualContact = dao.getContactById(expectedContact.getContactId());

// Assert - assert that the actual matches the expected
        assertEquals(actualContact, expectedContact);

        dao.removeContact(expectedContact.getContactId());
        assertNull(dao.getContactById(expectedContact.getContactId()));
    }

    /**
     * Test of getAllContacts method, of class ContactListDao.
     */
    @Test
    public void testGetAllContacts() {
    }

    /**
     * Test of updateContact method, of class ContactListDao.
     */
    @Test
    public void testUpdateContact() {
    }

    /**
     * Test of searchContacts method, of class ContactListDao.
     */
    @Test
    public void testSearchContacts() {
// Arrange
        Contact expectedContact = new Contact();
        expectedContact.setFirstName("Steve");
        expectedContact.setLastName("Smith");
        expectedContact.setCompany("Dev IQ");
        expectedContact.setEmail("steve@deviq.com");
        expectedContact.setPhone("3305551212");

        Contact expectedContact2 = new Contact();
        expectedContact2.setFirstName("Ken");
        expectedContact2.setLastName("Smith");
        expectedContact2.setCompany("Dev IQ");
        expectedContact2.setEmail("ken@deviq.com");
        expectedContact2.setPhone("3305559999");

        Contact expectedContact3 = new Contact();
        expectedContact3.setFirstName("Steve");
        expectedContact3.setLastName("Smith");
        expectedContact3.setCompany("SQL Guy");
        expectedContact3.setEmail("steve@sqlrocks.com");
        expectedContact3.setPhone("3305551313");

        Contact expectedContact4 = new Contact();
        expectedContact4.setFirstName("Sally");
        expectedContact4.setLastName("Smith");
        expectedContact4.setCompany("TSG");
        expectedContact4.setEmail("sally.smith@tsg.com");
        expectedContact4.setPhone("3305551414");
// save contacts
        dao.addContact(expectedContact);
        dao.addContact(expectedContact2);
        dao.addContact(expectedContact3);
        dao.addContact(expectedContact4);
// Test: Smiths at Dev IQ        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "Smith");
        criteria.put(SearchTerm.COMPANY, "Dev IQ");
        List<Contact> smithsAtDevIQ = dao.searchContacts(criteria);
        assertEquals(2, smithsAtDevIQ.size());
        assertEquals(expectedContact, smithsAtDevIQ.get(0));
// Test: Steve Smiths
        criteria.remove(SearchTerm.COMPANY);
        criteria.put(SearchTerm.FIRST_NAME, "Steve");
        List<Contact> steveSmiths = dao.searchContacts(criteria);
        assertEquals(2, steveSmiths.size());
        assertEquals(expectedContact, steveSmiths.get(0));
    }

}
