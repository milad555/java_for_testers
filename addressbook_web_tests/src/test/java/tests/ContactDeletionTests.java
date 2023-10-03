package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;


public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.isContactPresent()) {
            app.openAddNewContactPage();
            app.createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
        }
        app.removeContact();
    }

}
