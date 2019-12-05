package com.addressbook.dao;

import com.addressbook.model.Contact;

import java.util.List;


public interface IContactDao {
    Contact insertContact(Contact contact);

    int removeContact(long id);

    List<Contact> getAllUniqContacts();

    List<Contact> getContactsByAddressBookId(long addressBookId);
}
