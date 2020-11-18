package pl.jsystems.qa.qagui.classic.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage extends BasePage {

    public UserProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "profile-gravatar__user-display-name")
    public WebElement userNamePanel;


    @FindBy(css = ".button.form-button.is-primary")
    public WebElement saveButton;

    @FindBy(css = "a[href='/me/notifications'] .sidebar__menu-link-text.menu-link-text")
    public WebElement notificationsLabel;
}
