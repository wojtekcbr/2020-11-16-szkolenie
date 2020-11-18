
package pl.jsystems.qa.qagui.classic.functional;

import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.classic.page.LoginPage;
import pl.jsystems.qa.qagui.classic.page.MainWordpressPage;

import static java.lang.Thread.sleep;

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
        loginPage.loginInput.sendKeys("wkwiatkowski@opi.org.pl");
        loginPage.logginButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginPage.passwordInput.clear();
        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys("3wojTek5ja7!");
        loginPage.passwordButton.click();

    }
}