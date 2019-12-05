package com.addressbook.dao;

import com.addressbook.exception.ApiRequestException;
import com.addressbook.model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeContactDataAccessServiceTest {

    IContactDao contactDao = new FakeContactDataAccessService();

    @Test
    @DisplayName("should get unique contacts list")
    void testGetAllUniqContacts() {
        List<Contact> contacts = contactDao.getAllUniqContacts();

        assertEquals(4, contacts.size());
    }

    @Test
    @DisplayName("when insert new contact")
    void testInsertNewContact() {
        Contact contact = new Contact(0, "new Guy", 6543523, 1);
        Contact createdContact = new Contact(6, "new Guy", 6543523, 1);
        assertEquals(createdContact, contactDao.insertContact(contact));
    }

    @Test
    @DisplayName("when insert a contact that already added in the address book")
    void testInsertSameContactToAddressBook() {
        Contact contact = new Contact(6, "Allan Steve", 1243524, 1);
        assertThrows(ApiRequestException.class, () -> contactDao.insertContact(contact));
    }

    @Test
    @DisplayName("when remove a contact")
    void testRemoveContact() {
        assertEquals(1, contactDao.removeContact(3));
    }

    @Test
    @DisplayName("when remove a contact not exist")
    void testRemoveNonExistContact() {
        assertThrows(ApiRequestException.class, () -> contactDao.removeContact(10));
    }

    @Test
    @DisplayName("when get contacts of a address book")
    void testGetContactsByAddressBookId() {
        assertEquals(3, contactDao.getContactsByAddressBookId(1).size());
    }
}