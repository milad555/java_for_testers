package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper  extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openAddNewContactPage();
        fillContactForm(contact);
        submitContactCreation();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"),contact.firstName());
        type(By.name("lastname"),contact.lastName());
        type(By.name("address"),contact.address());
        type(By.name("home"),contact.cellPhone());
        type(By.name("email"),contact.email());
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    public void removeContact() {
        openPage("http://localhost/addressbook/index.php");
        selectContact();
        removeSelectedContacts();
        openPage("http://localhost/addressbook/index.php");
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
        acceptAlert();
    }

    private void acceptAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void openPage(String page) {
        manager.driver.get(page);
    }

    public void openAddNewContactPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            openPage("http://localhost/addressbook/edit.php");
        }
    }


    public void removeAllContacts() {
        openContactPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }
    }

    private void openContactPage() {
        if (!manager.isElementPresent(By.name("MainForm"))){
            openPage("http://localhost/addressbook/index.php");
        }
    }

    public int getContactCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
