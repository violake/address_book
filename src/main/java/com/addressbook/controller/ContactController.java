package com.addressbook.controller;

import com.addressbook.model.Contact;
import com.addressbook.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/contacts")
@RestController
public class ContactController {
    private final IContactService contactService;

    @Autowired
    public ContactController(@Qualifier("fakeContactService") IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
//        return contactService.getAllUniqContacts();
        List<Contact> result =  contactService.getAllUniqContacts();
        return result;
    }
}
