package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;


public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (app.contacts().isContactPresent()) {
            app.contacts().openAddNewContactPage();
            app.contacts().createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
        }
        app.contacts().removeContact();
    }

    @Test
    //deletes contacts one by one
    public void canDeleteAllContact(){
        while (!app.contacts().isContactPresent()) {
            app.contacts().removeContact();
            app.contacts().openAddNewContactPage();
        }
    }

}
