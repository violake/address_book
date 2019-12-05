package com.addressbook.dao;

import com.addressbook.model.AddressBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.convert.DataSizeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class FakeAddressBookDataAccessServiceTest {

    IAddressBookDao addressBookDao = new FakeAddressBookDataAccessService();

    @Test
    @DisplayName("when address book id exist")
    void getAddressBookById() {
        assertEquals("Tony Stark", addressBookDao.getAddressBookById(1).get().getName());
    }

    @Test
    @DisplayName("when address book id not exist")
    void getAddressBookByInvalidId() {
        assertTrue(addressBookDao.getAddressBookById(3).isEmpty());
    }

    @Test
    @DisplayName("when is my contact")
    void myContact() {
        AddressBook addressBook = addressBookDao.getAddressBookById(1).get();
        assertTrue(addressBookDao.myContact(addressBook, 1));
    }

    @Test
    @DisplayName("when is not my contact")
    void notMyContact() {
        AddressBook addressBook = addressBookDao.getAddressBookById(1).get();
        assertFalse(addressBookDao.myContact(addressBook, 4));
    }
}