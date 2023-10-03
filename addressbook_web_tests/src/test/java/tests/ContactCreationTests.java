package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void canAddContact() {
    app.openAddNewContactPage();
    app.createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
  }

  @Test
  public void canAddContactWithNameOnly() {
    app.openAddNewContactPage();
    app.createContact(new ContactData().withFirstAndLastName("Peter", "Parker"));
  }

  @Test
  public void canAddContactWithEmailOnly() {
    app.openAddNewContactPage();
    app.createContact(new ContactData().withEmailOnly("email@test.com"));
  }

  @Test
  public void canAddContactWithPhonelOnly() {
    app.openAddNewContactPage();
    app.createContact(new ContactData().withPhoneOnly("555-55-55"));
  }

  @Test
  public void canAddContactWithAddresslOnly() {
    app.openAddNewContactPage();
    app.createContact(new ContactData().withAddressOnly("100 Las Palmas"));
  }
}
