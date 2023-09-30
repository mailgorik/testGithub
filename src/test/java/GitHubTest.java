import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;

public class GitHubTest extends BaseTest {


    @Test
    public void verifyLogoOnTheLoginPageDisplayed() {
        Logger logger = LogManager.getLogger();
        logger.info("verifyLogoOnTheLoginPageDisplayed is starting");
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().getLogo().isDisplayed());
    }

    @Test
    public void verifyLoginIsSuccessful() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.goToLoginPage().loginSuccessful("mailgorik@gmail.com", "Goreckij0")
                .getLogoOnTheMainPage().isDisplayed());
    }

    @Test
    public void verifyFailedLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().negativeLogIn("sdfs@fsdsdf.com", "234432")
                .validateErrorMessage("Incorrect username or password.");
    }

    @Test
    public void verifyCreateAndDeleteIssueFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage()
                .loginSuccessful("mailgorik@gmail.com", "Goreckij0")
                .navigateToRepositoryPage().verifyIssuesTabAccessible()
                .navigateToIssuesPage().verifyNewIssueButtonAvailable()
                .verifyNewIssueSubmission("QA-Test-Tiltle", "QA-Test-body")
                .editCreatedIssue("Edited-Issue-Title", "Edited-Issue-Comment")
                .deleteCreatedIssue()
                .validateSuccessMessageUponDelete("The issue was successfully deleted.");
    }

}
