package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().getContactCount() == 0) {
            app.contacts().openAddNewContactPage();
            app.contacts().createContact(new ContactData("", "John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com",""));
        }
        var oldContacts = app.contacts().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canDeleteAllContactAtOnce(){
        if (app.groups().getCount() == 0){
            app.contacts().createContact(new ContactData("", "John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getContactCount());

    }

}
