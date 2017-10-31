/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.controller;

import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author James
 */
@Controller
public class ContactController {

    ContactListDao dao;

// controller needs a dao ... use bean in spring-persistence.xml    
    @Inject
    public ContactController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayContactsPage", method = RequestMethod.GET)
    public String displayContactsPage(Model model) {
        // get all contacts
        List<Contact> allContacts = dao.getAllContacts();
        model.addAttribute("contactList", allContacts);
        return "contacts";
    }

    @RequestMapping(value = "/createContact", method = RequestMethod.POST)
    public String createContact(HttpServletRequest request) {
        // create a new instance
        Contact contact = new Contact();
        // set the properties based on what was sent from the form
        contact.setFirstName(request.getParameter("firstName"));
        contact.setLastName(request.getParameter("lastName"));
        contact.setCompany(request.getParameter("company"));
        contact.setEmail(request.getParameter("email"));
        contact.setPhone(request.getParameter("phone"));
        // save the contact - persist it
        dao.addContact(contact);
        // redirect to our contacts page
        return "redirect:displayContactsPage";
    }

    @RequestMapping(value = "/displayContactDetails", method = RequestMethod.GET)
    public String displayContactDetails(HttpServletRequest request, Model model) {
        String contactIdParameter = request.getParameter("contactId");
        int contactId = Integer.parseInt(contactIdParameter);

        Contact contact = dao.getContactById(contactId);

        model.addAttribute("selectedContact", contact);
        return "contactDetails";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        dao.removeContact(contactId);
        return "redirect:displayContactsPage";
    }

    @RequestMapping(value = "/displayEditContactForm", method = RequestMethod.GET)
    public String displayEditContactForm(HttpServletRequest request, Model model) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        Contact contact = dao.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editContactForm";
    }

    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {

        if (result.hasErrors()) {
            return "editContactForm";
        }
        
        dao.updateContact(contact);
// redirect issues a get to display page; last command was a get, not post.
// this means user can't accidentally submit again
        return "redirect:displayContactsPage";
    }
}
