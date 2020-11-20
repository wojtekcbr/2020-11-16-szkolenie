package pl.jsystems.qa.qagui.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pl.jsystems.qa.qagui.classic.page.MainUserPage;
import pl.jsystems.qa.qagui.config.GuiConfig;
import pl.jsystems.qa.qagui.cucumber.ConfigBaseSteps;
import pl.jsystems.qa.qagui.cucumber.functional.LoginFunction;

import static com.google.common.truth.Truth.assertThat;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(ConfigBaseSteps configBaseSteps) {
        this.driver = configBaseSteps.setUpWebDriver();
    }
    @Given("User start on main page")
    public void userStartOnMainPage() {
        driver.navigate().to(GuiConfig.BASE_URL);
    }

    @When("User logs to user panel")
    public void userLogsToUserPanel() {
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();
    }

    @Then("User can modify user profile")
    public void userCanModifyUserProfile() {
        MainUserPage mainUserPage = new MainUserPage(driver);
        String welcomeText = mainUserPage.welcomeText.getText();
        assertThat(welcomeText).isEqualTo("Witaj w Czytniku");
    }
}
