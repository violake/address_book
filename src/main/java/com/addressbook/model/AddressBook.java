package com.addressbook.model;

public class AddressBook {
    private final long id;
    private final String name;

    public AddressBook(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
