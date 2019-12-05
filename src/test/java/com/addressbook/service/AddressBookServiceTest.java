package com.addressbook.service;

import com.addressbook.dao.IAddressBookDao;
import com.addressbook.dao.IContactDao;
import com.addressbook.exception.ApiRequestException;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressBookServiceTest {

    @Mock
    private IContactDao contactDao;

    @Mock
    private IAddressBookDao addressBookDao;

    @InjectMocks
    private AddressBookService addressBookService;

    private Contact contact1 = new Contact(1, "Allan Steve", 1243524, 1);
    private Contact contact2 = new Contact(2, "Brad Pitt", 654643432, 1);
    private Contact newContact = new Contact(3, "Harry Potter", 654643432, 1);
    private List<Contact> contacts = Arrays.asList(contact1,  contact2);

    private AddressBook addressBook = new AddressBook(1, "name1");

    @Test
    @DisplayName("when address book has contacts")
    void getContactsByAddressBookId() {
        when(contactDao.getContactsByAddressBookId(1)).thenReturn(contacts);
        when(addressBookDao.getAddressBookById(1)).thenReturn(java.util.Optional.ofNullable(addressBook));

        assertEquals(contacts, addressBookService.getContactsByAddressBookId(1));
    }

    @Test
    @DisplayName("add new contact when address book exist")
    void addContactToAddressBook() {
        Contact paramContact = new Contact(0, newContact.getName(), newContact.getPhoneNumber(), 1);

        when(contactDao.insertContact(paramContact)).thenReturn(newContact);
        when(addressBookDao.getAddressBookById(1)).thenReturn(java.util.Optional.ofNullable(addressBook));

        assertEquals(newContact, addressBookService.addContactToAddressBook(paramContact, 1));
    }

    @Test
    @DisplayName("add new contact when address book not exist")
    void addContactToNonExistAddressBook() {
        Contact paramContact = new Contact(0, newContact.getName(), newContact.getPhoneNumber(), 2);

        when(addressBookDao.getAddressBookById(2)).thenReturn(Optional.empty());

        assertThrows(ApiRequestException.class, () -> addressBookService.addContactToAddressBook(paramContact, 2));
    }

    @Test
    @DisplayName("remove contact when address book exist")
    void removeContactFromAddressBook() {
        when(addressBookDao.getAddressBookById(1)).thenReturn(java.util.Optional.ofNullable(addressBook));
        when(addressBookDao.myContact(addressBook,1)).thenReturn(true);
        when(contactDao.removeContact(1)).thenReturn(1);

        assertEquals(1, addressBookService.removeContactFromAddressBook(1,1));
    }

    @Test
    @DisplayName("remove contact when address book not exist")
    void removeOtherContactFromAddressBook() {
        when(addressBookDao.getAddressBookById(1)).thenReturn(java.util.Optional.ofNullable(addressBook));
        when(addressBookDao.myContact(addressBook,1)).thenReturn(false);

        assertThrows(ApiRequestException.class, () -> addressBookService.removeContactFromAddressBook(1,1));
    }

    @Test
    @DisplayName("remove contact when address book not exist")
    void removeContactFromNonExistAddressBook() {
        when(addressBookDao.getAddressBookById(2)).thenReturn(Optional.empty());

        assertThrows(ApiRequestException.class, () -> addressBookService.removeContactFromAddressBook(2,1));
    }
}