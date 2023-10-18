package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>(List.of());
//        for (var firstName : List.of("", "Klark")) {
//            for (var lastName : List.of("", "Kent")) {
//                for (var address : List.of("", "1 Lincoln st")) {
//                    for (var cellPhone : List.of("", " 121-32-43")) {
//                        for (var email : List.of("", "apple@apple.com")) {
//                            result.add(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address).withPhone(cellPhone).withEmail(email));
//                        }
//                    }
//                }
//            }
//        }
        var json ="";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)
        ){
            var line = breader.readLine();
            while (line != null){
                json = json + line;
                line = breader.readLine();
            }
        }

       // var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();

        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
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
            expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withLastName("").withAddress("").withPhone("").withEmail("").withPhoto(""));
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
