/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public interface ContactListDao {
    // C: Create - Add
    public Contact addContact(Contact contact);
    // R: Read - GetAll, GetBy
    public List<Contact> getAllContacts();
    public Contact getContactById(long contactId);
    // U: Update - Edit ... can return Contact to make more testable
    public void updateContact(Contact contact);
    // D: Delete - Remove ... can return Contact to make more testable
    public void removeContact(long contactId);
    // S: Search
    public List<Contact> searchContacts(Map<SearchTerm,String> criteria);
}
