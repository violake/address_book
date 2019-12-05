package com.addressbook.controller;

import com.addressbook.model.Contact;
import com.addressbook.service.IAddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddressBookControllerTest {

    private MockMvc mockMvc;
    static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                 MediaType.APPLICATION_JSON.getSubtype(),
                                                                 Charset.forName("utf8"));

    @Mock
    private IAddressBookService addressBookService;

    @InjectMocks
    private AddressBookController addressBookController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressBookController)
                .build();
    }

    @Test
    @DisplayName("get contacts from a address book")
    void getContactsFromAddressBook() throws Exception {
        List<Contact> contacts = Arrays.asList(
                new Contact(1, "Brad Pitt", 654643432, 1),
                new Contact(2, "Allan Steve", 1243524, 1)
        );
        when(addressBookService.getContactsByAddressBookId(1)).thenReturn(contacts);

        mockMvc.perform(get("/api/address-book/1/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Brad Pitt")));
    }

//    TODO: fix issue '415 UNSUPPORTED_MEDIA_TYPE'
//    @Test
//    @DisplayName("add a contact to a address book")
//    void addContact() throws Exception {
//        Contact paramContact = new Contact(0, "Tim Berg", 353243, 1);
//        Contact returnContact = new Contact(3, "Tim Berg", 353243, 1);
//
//        String jsonStr = asJsonString(paramContact);
//
//        when(addressBookService.addContactToAddressBook(paramContact, 1)).thenReturn(returnContact);
//        mockMvc.perform(post("/api/address-book/{addressBookId}/contact", 1)
//                                .accept(APPLICATION_JSON_UTF8)
//                                .contentType(APPLICATION_JSON_UTF8)
//                                .content(jsonStr)
//                            )
//                            .andExpect(status().isOk())
//                            .andExpect(jsonPath("$.phoneNumber", is(353243)))
//                            .andExpect(jsonPath("$.name", is("Tim Berg")));
//    }


    @Test
    @DisplayName("remove a contact from a address book")
    void removeContact() throws Exception {
        when(addressBookService.removeContactFromAddressBook(1, 1)).thenReturn(1);
        mockMvc.perform(delete("/api/address-book/{addressBookId}/contact/{contactId}", 1, 1))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", is(1)));
    }


    /*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}