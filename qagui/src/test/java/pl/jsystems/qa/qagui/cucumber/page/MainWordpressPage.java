package pl.jsystems.qa.qagui.cucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.BasePage;

public class MainWordpressPage extends BasePage {

    public MainWordpressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".x-nav-item.x-nav-item--wide.x-nav-item--logged-in")
    public WebElement loginButton;

    @FindBy(css = "#lpc-picture\\.3 > div")
    public WebElement picture;

    @FindBy(id = "lpc-button")
    public WebElement startYourWebsite;

}
