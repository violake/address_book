package com.addressbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
    private final long id;
    private final String name;
    private final long phoneNumber;
    @JsonIgnore
    private final long addressBookId;

    public Contact(long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("phoneNumber") long phoneNumber,
                   long addressBookId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressBookId = addressBookId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || !(obj instanceof Contact)) return false;

        Contact other = (Contact)obj;

        boolean equal = this.name.equals(other.name) &&
                this.phoneNumber == other.getPhoneNumber();

        return equal;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + String.valueOf(phoneNumber).hashCode();
        return result;
    }

    public boolean sameAddressBook(Contact contact) {
        return this.getAddressBookId() == contact.getAddressBookId();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getAddressBookId() {
        return addressBookId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
