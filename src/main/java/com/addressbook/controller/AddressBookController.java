package com.addressbook.controller;

import com.addressbook.model.Contact;
import com.addressbook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/address-book")
@RestController
public class AddressBookController {
    private final IAddressBookService addressBookService;

    @Autowired
    public AddressBookController(@Qualifier("fakeAddressBookService") IAddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping(value = "/{addressBookId}/contacts")
    public List<Contact> getContactsFromAddressBook(@PathVariable("addressBookId")long addressBookId){
        return addressBookService.getContactsByAddressBookId(addressBookId);
    }

    @PostMapping(value = "/{addressBookId}/contact", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Contact addContact(@Valid @NonNull @RequestBody Contact contact,
                              @PathVariable("addressBookId") long addressBookId ){

        return addressBookService.addContactToAddressBook(contact, addressBookId);
    }

    @DeleteMapping(value = "/{addressBookId}/contact/{contactId}")
    public int removeContactById(@PathVariable("addressBookId") long addressBookId ,
                                 @PathVariable("contactId") long contactId ){

        return addressBookService.removeContactFromAddressBook(addressBookId, contactId);
    }
}
