package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {

            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM `addressbook` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL ")) {

            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContactData> getContactList() {
        var contacts = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, firstname, lastname, address, home, email, email2, email3, home, mobile, work, phone2 FROM addressbook")) {

            while (result.next()) {
                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withLastName(result.getString("lastname"))
                        .withAddress(result.getString("address"))
                        .withEmail(result.getString("email"))
                        .withEmail2(result.getString("email2"))
                        .withEmail3(result.getString("email3"))
                        .withHome(result.getString("home"))
                        .withMobile(result.getString("mobile"))
                        .withWork(result.getString("work"))
                        .withPhone2(result.getString("phone2")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
