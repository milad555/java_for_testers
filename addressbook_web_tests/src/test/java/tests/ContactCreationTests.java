package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void canAddContact() {
    app.contacts().openAddNewContactPage();
    app.contacts().createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
  }

  @Test
  public void canAddContactWithNameOnly() {
    app.contacts().openAddNewContactPage();
    app.contacts().createContact(new ContactData().withFirstAndLastName("Peter", "Parker"));
  }

  @Test
  public void canAddContactWithEmailOnly() {
    app.contacts().openAddNewContactPage();
    app.contacts().createContact(new ContactData().withEmail("email@test.com"));
  }

  @Test
  public void canAddContactWithPhoneOnly() {
    app.contacts().openAddNewContactPage();
    app.contacts().createContact(new ContactData().withPhone("555-55-55"));
  }

  @Test
  public void canAddContactWithAddressOnly() {
    app.contacts().openAddNewContactPage();
    app.contacts().createContact(new ContactData().withAddress("100 Las Palmas"));
  }
}
