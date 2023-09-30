import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final static String TITLE = "Login page";
    // перелік локаторів для сторінки LoginPage
    private By loginFieldLocator = By.id("login_field");

    private By passwordFieldLocator = By.id("password");


    private By logInButtonLocator = By.name("commit");

    private By logoLocator = By.className("header-logo");

    private By errorTextLocator = By.xpath("//div[contains(text(), \"Incorrect username or password.\")]");


    public LoginPage(WebDriver driver) {
        super(driver, TITLE);
    }

    //метод, який повертає WebElement логотипу
    public WebElement getLogo() {
        return driver.findElement(logoLocator);
    }

    // метод успішного логіна
    public MainPage loginSuccessful(String login, String password) {
        log.info("Login is successful");
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(logInButtonLocator).click();
        return new MainPage(driver);
    }

    // метод неуспішного логіна
    public LoginPage negativeLogIn(String login, String password) {
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(logInButtonLocator).click();
        return this; // SAME AS return new LoginPage(driver);
    }

    // метод валідує error message
    public LoginPage validateErrorMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, driver.findElement(errorTextLocator).getText(),
                "Actual error text doesn't match expected message");
        return this;
    }

    // перевірка доступності елементів для наступних маніпуляцій
    public LoginPage validateAuthFieldsDisplayed() {
        Assertions.assertTrue(driver.findElement(loginFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(passwordFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(logInButtonLocator).isDisplayed());
        return this;
    }


}
