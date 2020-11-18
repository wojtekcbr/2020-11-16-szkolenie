package pl.jsystems.qa.qagui.cucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "usernameOrEmail")
    public WebElement loginInput;

    @FindBy(css = ".button.form-button.is-primary")
    public WebElement logginButton;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(css = ".button.form-button.is-primary")
    public WebElement passwordButton;
}
