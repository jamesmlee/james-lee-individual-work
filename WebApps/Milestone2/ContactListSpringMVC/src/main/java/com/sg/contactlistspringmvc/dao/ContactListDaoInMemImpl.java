/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author James
 */
public class ContactListDaoInMemImpl implements ContactListDao {

    private Map<Long, Contact> contactMap = new HashMap<>();

    private static long contactIdCounter = 0;

    @Override
    public Contact addContact(Contact contact) {
        // set new contact to current ID
        contact.setContactId(contactIdCounter);
        // increment the counter for the next contact
        contactIdCounter++;
        // add contact to contact list
        contactMap.put(contact.getContactId(), contact);
        // return contact
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return new ArrayList(c);
    }

    @Override
    public Contact getContactById(long contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    @Override
    public void removeContact(long contactId) {
        contactMap.remove(contactId);
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        
// get all search term values from the map
        String firstNameSearchCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameSearchCriteria = criteria.get(SearchTerm.LAST_NAME);
        String companySearchCriteria = criteria.get(SearchTerm.COMPANY);
        String phoneSearchCriteria = criteria.get(SearchTerm.PHONE);
        String emailSearchCriteria = criteria.get(SearchTerm.EMAIL);
        
// build predicate conditions for each of the criteria
        Predicate<Contact> firstNameSearchPredicate;
        Predicate<Contact> lastNameSearchPredicate;
        Predicate<Contact> companySearchPredicate;
        Predicate<Contact> phoneSearchPredicate;
        Predicate<Contact> emailSearchPredicate;
        
// define our predicates: e.g., find all last name Smith at XYZ corp       
        // default empty value predicate
        Predicate<Contact> truePredicate = (c) -> {
            return true;
        };
        // check each criterion; if empty/null, set to truePredicate; otherwise, lambda expression
        if (firstNameSearchCriteria == null || firstNameSearchCriteria.isEmpty()) {
            firstNameSearchPredicate = truePredicate;
        } else {
            firstNameSearchPredicate = (c) -> c.getFirstName().equalsIgnoreCase(firstNameSearchCriteria);
        }

        if (lastNameSearchCriteria == null || lastNameSearchCriteria.isEmpty()) {
            lastNameSearchPredicate = truePredicate;
        } else {
            lastNameSearchPredicate = (c) -> c.getLastName().equalsIgnoreCase(lastNameSearchCriteria);
        }

        if (companySearchCriteria == null || companySearchCriteria.isEmpty()) {
            companySearchPredicate = truePredicate;
        } else {
            companySearchPredicate = (c) -> c.getCompany().equalsIgnoreCase(companySearchCriteria);
        }

        if (phoneSearchCriteria == null || phoneSearchCriteria.isEmpty()) {
            phoneSearchPredicate = truePredicate;
        } else {
            phoneSearchPredicate = (c) -> c.getPhone().equalsIgnoreCase(phoneSearchCriteria);
        }

        if (emailSearchCriteria == null || emailSearchCriteria.isEmpty()) {
            emailSearchPredicate = truePredicate;
        } else {
            emailSearchPredicate = (c) -> c.getEmail().equalsIgnoreCase(emailSearchCriteria);
        }

// filter map with the predicates
        return contactMap.values().stream()
                .filter(firstNameSearchPredicate
                        .and(lastNameSearchPredicate)
                        .and(companySearchPredicate)
                        .and(phoneSearchPredicate)
                        .and(emailSearchPredicate))
                .collect(Collectors.toList());
    }

}
