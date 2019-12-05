package com.addressbook.dao;

import com.addressbook.model.AddressBook;

import java.util.Optional;

public interface IAddressBookDao {
    Optional<AddressBook> getAddressBookById(long id);
    boolean myContact(AddressBook addressBook, long contactId);
}
