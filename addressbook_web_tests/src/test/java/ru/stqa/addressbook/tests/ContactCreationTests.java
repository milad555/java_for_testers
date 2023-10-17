package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of());
        for (var firstName : List.of("", "Klark")) {
            for (var lastName : List.of("", "Kent")) {
                for (var address : List.of("", "1 Lincoln st")) {
                    for (var cellPhone : List.of("", " 121-32-43")) {
                        for (var email : List.of("", "apple@apple.com")) {
                            result.add(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address).withPhone(cellPhone).withEmail(email));
                        }
                    }
                }
            }
        }
            for (int i = 0; i < 5; i++) {
                result.add(new ContactData()
                        .withFirstName(CommonFunctions.randomString(i * 5))
                        .withLastName(CommonFunctions.randomString(i * 5))
                        .withAddress(CommonFunctions.randomString(i * 5))
                        .withPhone(CommonFunctions.randomString(i * 5))
                        .withEmail(CommonFunctions.randomString(i * 5)));
            }
            return result;
        }
        @ParameterizedTest
        @MethodSource("contactProvider")
        public void canCreateMultipleContacts (ContactData contact){
            var oldContacts = app.contacts().getContactList();
            app.contacts().createContact(contact);
            var newContacts = app.contacts().getContactList();
            Comparator<ContactData> compareById = (o1, o2) -> {
                return   Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            newContacts.sort(compareById);
            var expectedList = new ArrayList<>(oldContacts);
            expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withLastName("").withAddress("").withPhone("").withEmail(""));
            expectedList.sort(compareById);
            Assertions.assertEquals(newContacts, expectedList);
        }

        @Test
        void canCreateContact(){
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
        }
    }
