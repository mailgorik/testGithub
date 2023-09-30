import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage extends BasePage{
    private final static String TITLE = "Main page";
    private By profileImgLocator = By.xpath("(//img[@class='avatar circle'])[1]");
    private By repositoryLink = By.xpath("(//a[@class='color-fg-default lh-0 mb-2 markdown-title'])[1]");
    public MainPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public WebElement getLogoOnTheMainPage(){
        return driver.findElement(profileImgLocator);
    }
    // метод переходу на вкладку Issues репозиторію
    public RepositoryPage navigateToRepositoryPage () {
        driver.findElement(repositoryLink).click();
        return new RepositoryPage(driver);
    }
}
