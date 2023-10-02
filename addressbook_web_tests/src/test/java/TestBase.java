import model.GroupData;
import model.ContactData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void removeGroup() {
        driver.findElement(By.linkText("groups")).click();
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void createContact(ContactData contact) {
      driver.findElement(By.name("firstname")).click();
      driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
      driver.findElement(By.name("lastname")).click();
      driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
      driver.findElement(By.name("address")).click();
      driver.findElement(By.name("address")).sendKeys(contact.street());
      driver.findElement(By.name("home")).click();
      driver.findElement(By.name("home")).sendKeys(contact.cellPhone());
      driver.findElement(By.name("email")).click();
      driver.findElement(By.name("email")).sendKeys(contact.email());
      driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
    }

    protected static void removeContact() {
      driver.get("http://localhost/addressbook/index.php");
      driver.findElement(By.name("selected[]")).click();
      driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
      driver.switchTo().alert().accept();
      driver.get("http://localhost/addressbook/index.php");
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/index.php");
            driver.manage().window().setSize(new Dimension(1061, 793));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("groups")).click();
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected void openAddNewContactPage() {
        if (!isElementPresent(By.name("submit"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }

    protected boolean isGroupPresent() {
        return !isElementPresent(By.name("selected[]"));
    }

    protected boolean isContactPresent() {
        return !isElementPresent(By.name("selected[]"));
    }
}
