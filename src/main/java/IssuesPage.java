import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesPage extends BasePage {
    private final static String TITLE = "Issues Page";
    private By newIssueButton = By.xpath("//span[contains(text(), \"New issue\")]");
    private By issueTitle = By.xpath("//input[@id=\"issue_title\"]");
    private By issueBody = By.name("issue[body]");
    private By submitNewIssueButton = By.xpath("//button[@class=\"btn-primary btn ml-2\"]");
    private By openIssueStatusLabel = By.xpath("(//span[@title=\"Status: Open\"])[1]");
    private By editIssue = By.xpath("(//span[contains(text(), \"Edit\")])[1]");
    protected By commentField = By.id("new_comment_field");
    protected By submitEditedIssueButton = By.xpath("//span[contains(text(), \"Save\")]");
    protected By deleteIssue = By.xpath("//*[contains(text(), \"Delete issue\")]");
    protected By verifyDeleteIssue = By.name("verify_delete");
    protected By deletionSuccessMessage = By.xpath("//*[contains(text(), \"The issue was successfully deleted.\")]");
    protected WebDriverWait webDriverWait;


    public IssuesPage(WebDriver driver) {
        super(driver, TITLE);
    }

    // перевірка доступності кнопки New Issue
    public IssuesPage verifyNewIssueButtonAvailable() {
        Assertions.assertTrue(driver.findElement(newIssueButton).isDisplayed());
        return this;
    }

    // метод успішного створення Issue
    public IssuesPage verifyNewIssueSubmission(String title, String body) {
        driver.findElement(newIssueButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assertions.assertTrue(driver.findElement(issueTitle).isDisplayed());
        driver.findElement(issueTitle).sendKeys(title);
        driver.findElement(issueBody).sendKeys(body);
        driver.findElement(submitNewIssueButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assertions.assertTrue(driver.findElement(openIssueStatusLabel).isDisplayed());
        return this;
    }

    // метод редагування Issue
    public IssuesPage editCreatedIssue(String title, String body) {
        driver.findElement(editIssue).click();
        driver.findElement(issueTitle).clear();
        driver.findElement(issueTitle).sendKeys(title);
        driver.findElement(commentField).sendKeys(body);
        driver.findElement(submitEditedIssueButton).click();
        return this;
    }

    // метод видалення Issue
    public IssuesPage deleteCreatedIssue() {
        log.info("Start deleting issue");
        driver.findElement(deleteIssue).click();
        driver.findElement(verifyDeleteIssue).click();
        log.info("Finish deleting issue");
        return this;
    }

    // метод валідує success message видалення
    public IssuesPage validateSuccessMessageUponDelete (String expectedMessage) {
        Assertions.assertEquals(expectedMessage, driver.findElement(deletionSuccessMessage).getText(),
                "Actual success text upon issue deletion doesn't match expected message");
        return this;
    }
}
