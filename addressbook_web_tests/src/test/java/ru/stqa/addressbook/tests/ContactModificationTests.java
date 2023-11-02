package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContacts(){
        if (app.hbm().getContactCount() == 0) {
            app.contacts().openAddNewContactPage();
            app.hbm().createContact(new ContactData("", "John", "Doe", "1st Street, Irvine, CA", "123-456", "", "", "test@test.com","", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName(CommonFunctions.randomString(10));
        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}
