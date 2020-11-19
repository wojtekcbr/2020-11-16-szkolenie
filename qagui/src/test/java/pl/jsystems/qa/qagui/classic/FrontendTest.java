package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.jsystems.qa.qagui.classic.page.*;
import pl.jsystems.qa.qagui.classic.functional.LoginFunction;
import pl.jsystems.qa.qagui.classic.page.modules.CommentModule;
import pl.jsystems.qa.qagui.config.GuiConfig;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("frontend")
@DisplayName("Frontend test")
public class FrontendTest {

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeAll
    public static void setUpBefore() throws Exception {
        System.setProperty("webdriver.chrome.driver", Paths.get(FrontendTest.class.getClassLoader().getResource("driver/chromedriver.exe").toURI()).toFile().getAbsolutePath());
        System.setProperty("webdriver.gecko.driver", Paths.get(FrontendTest.class.getClassLoader().getResource("driver/geckodriver.exe").toURI()).toFile().getAbsolutePath());
    }

    @BeforeEach
    public void setUpBeforeEach(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @DisplayName("login test")
    @Test
    public void loginTest() {
        driver.get("https://wordpress.com");
        driver.findElement(By.cssSelector(".x-nav-item.x-nav-item--wide.x-nav-item--logged-in")).click();
        driver.findElement(By.id("usernameOrEmail")).clear();
        driver.findElement(By.id("usernameOrEmail")).click();
        driver.findElement(By.id("usernameOrEmail")).sendKeys("wkwiatkowski@opi.org.pl");
        driver.findElement(By.cssSelector(".button.form-button.is-primary")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("3wojTek5ja7!");
        driver.findElement(By.cssSelector(".button.form-button.is-primary")).click();

        String welcomeText = driver.findElement(By.cssSelector(".formatted-header__title.wp-brand-font")).getText();

        assertThat(welcomeText).isEqualTo("Moja strona główna");
    }

    @DisplayName("Check user")
    @Test
    public void checkUser() {
        driver.get("https://wordpress.com/");

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);

        mainUserPage.waitForElementToBeClickable(mainUserPage.userAvatar);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        String userName = userProfilePage.userNamePanel.getText();

        assertThat(userName).isEqualTo("wojtekcbr");

    }


    @DisplayName("Checkselected element")
    @Test
    public void sellectedElementTest(){
        driver.get("https://wordpress.com/");
        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.notificationsLabel.click();

        NotificationUserPage notificationUserPage = new NotificationUserPage(driver);
        notificationUserPage.commentsLabel.click();
        String headerPageComments = notificationUserPage.headerComments.getText();
        assertThat(headerPageComments).isEqualTo("Komentarze na innych stronach");
    }

    @RepeatedTest(10)
    @DisplayName("Check selected element.")
    @Test
    public void selectedElement() throws InterruptedException {
        driver.get(GuiConfig.BASE_URL);

        LoginFunction loginFunction = new LoginFunction(driver);
        loginFunction.login();

        MainUserPage mainUserPage = new MainUserPage(driver);

        mainUserPage.waitForElementToBeClickable(mainUserPage.userAvatar);
        mainUserPage.userAvatar.click();

        UserProfilePage userProfilePage = new UserProfilePage(driver);

        int j = 0;
        boolean displayed = false;
        while (!displayed) {
            System.out.println("++++++++++" + j++);
            try {
                userProfilePage.notificationsLabel.click();
                displayed = true;
            } catch (Exception e) {
                displayed = false;
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
                mainUserPage.userAvatar.click();
            }
        }

        NotificationUserPage notificationUserPage = new NotificationUserPage(driver);
        notificationUserPage.getCommentsLabel().click();

        CommentModule commentModule = notificationUserPage.getCommentModule();

        int counter = 0;
        boolean selected = false;
        while (!selected & counter < 20) {
            sleep(500);
            if (commentModule.likeRingCheckbox.isSelected()) {
                selected = true;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        assertTrue(commentModule.likeRingCheckbox.isSelected());
        commentModule.likeRingCheckbox.click();

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        counter = 0;
        boolean selected2 = true;
        while (selected2 & counter < 20) {
            sleep(500);
            if (!commentModule.likeRingCheckbox.isSelected()) {
                selected2 = false;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        commentModule.saveSettingsButton.click();

        assertFalse(commentModule.likeRingCheckbox.isSelected());

        commentModule.likeRingCheckbox.click();

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        counter = 0;
        boolean selected3 = false;
        while (!selected3 & counter < 20) {
            sleep(500);
            if (commentModule.likeRingCheckbox.isSelected()) {
                selected3 = true;
            } else {
                commentModule.likeRingCheckbox.click();
            }
            counter++;
        }

        if (commentModule.saveSettingsButton.isEnabled()) {
            commentModule.saveSettingsButton.click();
        }

        assertTrue(commentModule.likeRingCheckbox.isSelected());

    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }
}
