package com.addressbook.service;

import com.addressbook.model.Contact;

import java.util.List;

public interface IContactService {
    public List<Contact> getAllUniqContacts();
}
