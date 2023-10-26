package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void createContact(ContactData contact) {
        openAddNewContactPage();
        fillContactForm(contact);
        submitContactCreation();
    }

    public void createContact(ContactData contact, GroupData group) {
        openAddNewContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
    }

    public void selectGroup(GroupData group) {
        if (manager.driver.getCurrentUrl().contains("edit.php")) {
            new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
        }else {
            new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
        }

    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.cellPhone());
        type(By.name("email"), contact.email());
        // attach(By.name("photo"), contact.photo());
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    public void removeContact(ContactData contact) {
        openPage("web.baseUrl");
        selectContact(contact);
        removeSelectedContacts();
        openPage("web.baseUrl");
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
        acceptAlert();
    }

    private void acceptAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public void openPage(String propName) {
        manager.driver.get(manager.properties.getProperty(propName));
    }

    public void openAddNewContactPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            openPage("web.contactEdit");
        }
    }


    public void removeAllContacts() {
        openContactPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void openContactPage() {
        if (!manager.isElementPresent(By.name("MainForm"))) {
            openPage("web.baseUrl");
        }
    }

    public int getContactCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name='entry']"));
        for (var tr : trs) {
            var name = tr.findElement(By.xpath(".//td[3]")).getText();
            var checkBox = tr.findElement(By.name("selected[]"));
            var id = checkBox.getAttribute("value");
            // var name = manager.driver.findElement(By.xpath("(//input[@value='" + id + "']/../../td)[last()-7]")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(name));
        }
        return contacts;

    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        openContactPage();
    }

    private void submitContactModification() {
        click(By.xpath("(//input[@value='Update'])[1]"));
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    public int getContactsInGroup(GroupData group) {
        openContactPage();
        selectGroup(group);
        return getNumberOfContactsInGroup();
    }

    private int getNumberOfContactsInGroup() {
        return manager.driver.findElements(By.xpath("//tr[@name='entry']")).size();
    }

    public void deleteContactFromGroup(GroupData group) throws InterruptedException {
        openPage("web.baseUrl");
        selectGroup(group);
        click(By.xpath("(//input[@type='checkbox'])[1]"));
        click(By.xpath("//input[@value='Delete']"));
        acceptAlert();
        Thread.sleep(500);

    }
}
