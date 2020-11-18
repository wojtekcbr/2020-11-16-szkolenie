package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "usernameOrEmail")
    public WebElement loginInput; //= driver.findElement(By.id("usernameOrEmail"));

    @FindBy(css = ".button.form-button.is-primary")
    public WebElement logginButton; // = driver.findElement(By.cssSelector(".button.form-button.is-primary"));

    @FindBy(id = "password")
    public WebElement passwordInput; //= driver.findElement(By.id("password"));

    @FindBy(css = ".button.form-button.is-primary")
    public WebElement passwordButton;
}
