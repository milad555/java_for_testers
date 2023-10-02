import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static ApplicationManager app;

    protected void removeGroup() {
        ApplicationManager.driver.findElement(By.linkText("groups")).click();
        ApplicationManager.driver.findElement(By.name("selected[]")).click();
        ApplicationManager.driver.findElement(By.name("delete")).click();
        ApplicationManager.driver.findElement(By.linkText("group page")).click();
    }

    @BeforeEach
    public void setUp() {
        if (app == null){
            app = new ApplicationManager();
        }
        init();
    }

    private void init() {
        if (ApplicationManager.driver == null) {
            ApplicationManager.driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(ApplicationManager.driver::quit));
            ApplicationManager.driver.get("http://localhost/addressbook/index.php");
            ApplicationManager.driver.manage().window().setSize(new Dimension(1061, 793));
            ApplicationManager.driver.findElement(By.name("user")).sendKeys("admin");
            ApplicationManager.driver.findElement(By.name("pass")).sendKeys("secret");
            ApplicationManager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            ApplicationManager.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void createGroup(GroupData group) {
        ApplicationManager.driver.findElement(By.name("new")).click();
        ApplicationManager.driver.findElement(By.name("group_name")).click();
        ApplicationManager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        ApplicationManager.driver.findElement(By.name("group_header")).click();
        ApplicationManager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        ApplicationManager.driver.findElement(By.name("group_footer")).click();
        ApplicationManager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        ApplicationManager.driver.findElement(By.name("submit")).click();
        ApplicationManager.driver.findElement(By.linkText("groups")).click();
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            ApplicationManager.driver.findElement(By.linkText("groups")).click();
        }
    }

    protected boolean isGroupPresent() {
        return !isElementPresent(By.name("selected[]"));
    }
}
