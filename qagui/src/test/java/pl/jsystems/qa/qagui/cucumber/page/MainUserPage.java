package pl.jsystems.qa.qagui.cucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.BasePage;

public class MainUserPage extends BasePage {

    public MainUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "empty-content__title")
    public WebElement welcomeText;

    @FindBy(css = ".masterbar__item.masterbar__item-me")
    public WebElement userAvatar;
}
