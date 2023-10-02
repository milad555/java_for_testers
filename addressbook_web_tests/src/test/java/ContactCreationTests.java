
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
  public void canAddContactWithAddressOnly() {
    openAddNewContactPage();
    createContact(new ContactData().withEmailOnly("email@test.com"));
  }

}
