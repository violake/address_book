# Address Book
This is a restful api server base on SpringBoot 2.2

## Api description
There are 4 apis implemented:
1. add a contact to a address book
2. remove a contact from a address book
3. get all unique contacts
4. get all contacts of a address book

## Data
Currently data are stored in memory.
There are two address book initialized.(id: [1, 2])
Each address book has some contacts already.(id: [1, 2, 3, 4, 5, 6])

## Start Server

### Local IntelliJ env
import the project by file `<app_folder>/build.gradle`

run 'AddressBookApplication'

or

```bash
#run command in terminal
./gradlew bootRun

```

### docker env
```bash
# build project
docker-compose build

# start server
docker-compose up address-book
```

## Using api

### postman

```
# add new contact to a address book
POST localhost:8080/api/address-book/1/contact
CONTENT-TYPE: application/json
REQUEST BODY: {"name":"Tim Berg","phoneNumber":353243,"id":1}

# remove a contact from a address book
DELETE localhost:8080/api/address-book/1/contact/1

# get all contacts of a address book
GET localhost:8080/api/address-book/1/contacts

# get all contacts (no duplication)
GET localhost:8080/api/contacts

```