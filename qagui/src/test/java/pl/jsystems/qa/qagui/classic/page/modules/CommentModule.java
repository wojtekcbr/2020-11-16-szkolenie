
package pl.jsystems.qa.qagui.classic.page.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.jsystems.qa.qagui.classic.page.BasePage;

public class CommentModule extends BasePage {

    public CommentModule(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".button.form-button.is-primary")
    public WebElement saveSettingsButton;

    @FindBy(css = ".notification-settings-form-stream:nth-child(2) ul li:nth-child(1) input")
    public WebElement likeRingCheckbox;
}