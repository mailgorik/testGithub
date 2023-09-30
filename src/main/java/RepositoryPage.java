import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RepositoryPage extends BasePage{
    private final static String TITLE = "RepositoryPagee";
    private By issuesTab = By.xpath("//a[@id='issues-tab']");
    public RepositoryPage(WebDriver driver) {
        super(driver, TITLE);
    }

    // перевірка доступності таби Issues для наступних маніпуляцій
    public RepositoryPage verifyIssuesTabAccessible() {
        Assertions.assertTrue(driver.findElement(issuesTab).isDisplayed());
        return this;
    }

    // метод переходу на сторінку Issues
    public IssuesPage navigateToIssuesPage() {
        driver.findElement(issuesTab).click();
        return new IssuesPage(driver);
    }


}
