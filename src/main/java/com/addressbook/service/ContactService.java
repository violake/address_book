package com.addressbook.service;

import com.addressbook.dao.IContactDao;
import com.addressbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeContactService")
public class ContactService implements IContactService{
    private final IContactDao contactDao;

    @Autowired
    public ContactService(@Qualifier("fakeContactDao") IContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<Contact> getAllUniqContacts() {
        return contactDao.getAllUniqContacts();
    }
}
