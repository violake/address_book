package com.addressbook.dao;

import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("fakeAddressBookDao")
public class FakeAddressBookDataAccessService implements IAddressBookDao {

    // id sequence for this fake address book
    // currently is not used as no crud api for address book
    private static long id_sequence = 3;

    private static List<AddressBook> addressBooks = new ArrayList<>(
            Arrays.asList(
                    new AddressBook(1, "Tony Stark"),
                    new AddressBook(2, "Steve Rogers")
            )
    );

    @Override
    public Optional<AddressBook> getAddressBookById(long id) {
        return addressBooks.stream().filter( a -> a.getId() == id)
                .findFirst();
    }

    @Override
    public boolean myContact(AddressBook addressBook, long contactId) {
        IContactDao contactDao = new FakeContactDataAccessService();
        List<Contact> l = contactDao.getContactsByAddressBookId(addressBook.getId());
        boolean contain = l.stream().anyMatch(c -> c.getId() == contactId);
        return contain;
//        return contactDao.getContactsByAddressBookId(addressBook.getId())
//                .stream()
//                .anyMatch(c -> c.getId() == contactId);
    }
}
