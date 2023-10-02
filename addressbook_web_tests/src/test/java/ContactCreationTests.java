
import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void canAddContact() {
    openAddNewContactPage();
    createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
  }

  @Test
  public void canAddContactWithNameOnly() {
    openAddNewContactPage();
    createContact(new ContactData().withFirstAndLastName("Peter", "Parker"));
  }

  @Test
  public void canAddContactWithEmailOnly() {
    openAddNewContactPage();
    createContact(new ContactData().withEmailOnly("email@test.com"));
  }

  @Test
  public void canAddContactWithPhonelOnly() {
    openAddNewContactPage();
    createContact(new ContactData().withPhoneOnly("555-55-55"));
  }

  @Test
  public void canAddContactWithAddresslOnly() {
    openAddNewContactPage();
    createContact(new ContactData().withAddressOnly("100 Las Palmas"));
  }
}
