package com.addressbook.service;

import com.addressbook.dao.FakeContactDataAccessService;
import com.addressbook.dao.IContactDao;
import com.addressbook.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private IContactDao contactDao;

    @InjectMocks
    private ContactService contactService;

    @Test
    @DisplayName("when there are contacts")
    void testGetUniqContacts() {
        List<Contact> contacts = Arrays.asList(
                new Contact(1, "Allan Steve", 1243524, 1),
                new Contact(2, "Brad Pitt", 654643432, 1)
        );
        when(contactDao.getAllUniqContacts()).thenReturn(contacts);
        assertEquals(contacts, contactService.getAllUniqContacts());
    }
}