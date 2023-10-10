package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of());
        for (var firstName : List.of("", "Klark")) {
            for (var lastName : List.of("", "Kent")) {
                for (var address : List.of("", "1 Lincoln st")) {
                    for (var cellPhone : List.of("", " 121-32-43")) {
                        for (var email : List.of("", "apple@apple.com")) {
                            result.add((new ContactData(firstName, lastName, address, cellPhone, email)));
                        }
                    }
                }
            }
        }
            for (int i = 0; i < 5; i++) {
                result.add((new ContactData(randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5))));
            }
            return result;
        }
        @ParameterizedTest
        @MethodSource("contactProvider")
        public void canCreateMultipleContacts (ContactData contact){
            int contactCount = app.contacts().getContactCount();

            app.contacts().createContact((contact));

            int newContactCount = app.contacts().getContactCount();
            Assertions.assertEquals(contactCount + 1, newContactCount);
        }
    }
