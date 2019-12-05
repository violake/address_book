package com.addressbook.dao;

import com.addressbook.exception.ApiRequestException;
import com.addressbook.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository("fakeContactDao")
public class FakeContactDataAccessService implements IContactDao {
    private static long id_sequence = 6;

    private static List<Contact> contacts = new ArrayList<>(
            Arrays.asList(
                    new Contact(1, "Allan Steve", 1243524, 1),
                    new Contact(2, "Brad Pitt", 654643432, 1),
                    new Contact(3, "Sarah Jackson", 1243524, 1),
                    new Contact(4, "Brad Pitt", 654643432, 2),
                    new Contact(5, "Sansa Stark", 1243524, 2)
            )
        );

    @Override
    public Contact insertContact(Contact contact) {
        if (contacts.stream().anyMatch(c -> c.equals(contact) && c.sameAddressBook(contact)))
            throw new ApiRequestException("contact has already been added");

        Contact newContact = new Contact(id_sequence++,
                                         contact.getName(),
                                         contact.getPhoneNumber(),
                                         contact.getAddressBookId());

        contacts.add(newContact);
        return newContact;
    }

    @Override
    public int removeContact(long id) {
        Optional<Contact> contact = getContactById(id);
        if(contact.isEmpty()) {
            throw new ApiRequestException("contact not exist (id:" + id +")");
        }
        contacts.remove(contact.get());
        return 1;
    }

    @Override
    public List<Contact> getAllUniqContacts() {
        return contacts.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactsByAddressBookId(long addressBookId) {
        return contacts.stream().filter(c -> c.getAddressBookId() == addressBookId).collect(Collectors.toList());
    }

    private Optional<Contact> getContactById(long id) {
        return contacts.stream()
                .filter( c -> c.getId() == id)
                .findFirst();
    }

}
