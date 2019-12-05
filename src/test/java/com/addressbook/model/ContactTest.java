package com.addressbook.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    @Test
    @DisplayName("when name and phoneNumber are equal and addressBookId is different")
    void equals() {
        Contact c1 = new Contact(1, "aaa", 12345, 1);
        Contact c2 = new Contact(2, "aaa", 12345, 2);

        assertTrue(c1.equals(c2));
    }

    @Test
    @DisplayName("when addressBookId are equal")
    void sameAddressBook() {
        Contact c1 = new Contact(1, "aaa", 12345, 1);
        Contact c2 = new Contact(2, "bbb", 532432, 1);

        assertTrue(c1.sameAddressBook(c2));
    }
}