package com.addressbook.service;

import com.addressbook.dao.IAddressBookDao;
import com.addressbook.dao.IContactDao;
import com.addressbook.exception.ApiRequestException;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("fakeAddressBookService")
public class AddressBookService implements IAddressBookService{
    private final IContactDao contactDao;
    private final IAddressBookDao addressBookDao;

    @Autowired
    public AddressBookService(@Qualifier("fakeContactDao") IContactDao contactDao,
                              @Qualifier("fakeAddressBookDao") IAddressBookDao addressBookDao) {
        this.contactDao = contactDao;
        this.addressBookDao = addressBookDao;
    }

    public List<Contact> getContactsByAddressBookId(long addressBookId) {
        checkAddressBookById(addressBookId);

        return contactDao.getContactsByAddressBookId(addressBookId);
    }

    public Contact addContactToAddressBook(Contact contact, long addressBookId) {
        checkAddressBookById(addressBookId);

        Contact contactWithAddressBook = new Contact(contact.getId(),
                                                     contact.getName(),
                                                     contact.getPhoneNumber(),
                                                     addressBookId);

        return contactDao.insertContact(contactWithAddressBook);
    }

    public int removeContactFromAddressBook(long addressBookId, long contactId) {
        AddressBook addressBook = checkAddressBookById(addressBookId);

        if (!addressBookDao.myContact(addressBook, contactId))
            throw new ApiRequestException("Address book do not have this contact id(" + contactId + ")");

        return contactDao.removeContact(contactId);
    }

    private AddressBook checkAddressBookById(long addressBookId) {
        Optional<AddressBook> addressBook = addressBookDao.getAddressBookById(addressBookId);

        if(addressBook.isEmpty()) throw new ApiRequestException("Address book not exist (id:" + addressBookId + ")");

        return addressBook.get();
    }
}
