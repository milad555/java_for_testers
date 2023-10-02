import model.ContactData;
import org.junit.jupiter.api.Test;


public class ContactDeletionGroupTests extends TestBase {

    @Test
    public void canDeleteContact() {
        if (isContactPresent()) {
            openAddNewContactPage();
            createContact(new ContactData("John", "Doe", "1st Street, Irvine, CA", "123-456", "test@test.com"));
        }
        removeContact();
    }

}
