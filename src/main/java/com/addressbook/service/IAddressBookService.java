package com.addressbook.service;

import com.addressbook.model.Contact;

import java.util.List;

public interface IAddressBookService {
    public List<Contact> getContactsByAddressBookId(long addressBookId);

    public Contact addContactToAddressBook(Contact contact, long addressBookId);

    public int removeContactFromAddressBook(long addressBookId, long contactId);
}
