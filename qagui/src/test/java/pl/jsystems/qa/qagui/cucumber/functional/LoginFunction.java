
package pl.jsystems.qa.qagui.cucumber.functional;

import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.classic.page.LoginPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;
import pl.jsystems.qa.qagui.config.GuiConfig;

public class LoginFunction {

    private WebDriver driver;

    public LoginFunction(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        MainWordpressPage mainWordpressPage = new MainWordpressPage(driver);
        mainWordpressPage.loginButton.click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInput.clear();
        loginPage.loginInput.click();
        loginPage.loginInput.sendKeys(GuiConfig.LOGIN);
        loginPage.logginButton.click();

        loginPage.passwordInput.clear();
        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys(GuiConfig.PASSWORD);
        loginPage.passwordButton.click();

    }
}