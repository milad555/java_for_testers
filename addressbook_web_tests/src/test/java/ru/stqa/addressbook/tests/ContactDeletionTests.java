package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;


public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().openAddNewContactPage();
            app.hbm().createContact(new ContactData("", "John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canDeleteAllContactAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    void canDeleteContactInGroup() throws InterruptedException {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            app.contacts().openPage("web.baseUrl");
        }

        var group = app.hbm().getGroupList().get(0);
        int contactsInGroup = app.contacts().getContactsInGroup(group);

        if (contactsInGroup == 0) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10));
            app.contacts().createContact(contact, group);
        }
        var oldContactList = app.hbm().getContactsInGroup(group);
        app.contacts().deleteContactFromGroup(group);
        var newContactList = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldContactList.size() - 1, newContactList.size());
    }
}
