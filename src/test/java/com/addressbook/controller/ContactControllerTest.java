package com.addressbook.controller;

import com.addressbook.model.Contact;
import com.addressbook.service.IContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(contactController)
                .build();
    }

    @Test
    void getAllContacts() throws Exception {
        List<Contact> contacts = Arrays.asList(
                new Contact(1, "Allan Steve", 1243524, 1),
                new Contact(2, "Brad Pitt", 654643432, 1)
        );
        when(contactService.getAllUniqContacts()).thenReturn(contacts);

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Allan Steve")));

    }
}