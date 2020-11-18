package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pl.jsystems.qa.qagui.config.GuiConfig;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ConfigFrontEnd {

    protected WebDriver driver;

    @BeforeEach
    public void setUpEach() {
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("driver/chromedriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader().getResource("driver/geckodriver.exe").toURI()).toFile().getAbsolutePath());
            System.setProperty("webdriver.edge.driver", Paths.get(getClass().getClassLoader().getResource("driver/msedgedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver = setWebDriver();
        setWebDriver();

    }

    private WebDriver setWebDriver() {
        switch (GuiConfig.BROWSER) {
            case "chrome:":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
        }

        return new ChromeDriver();
    }

    private void setRemoteDriver() {
        DesiredCapabilities desiredCapabilities;
        
        switch (GuiConfig.BROWSER) {
            case "chrome:":
                desiredCapabilities = DesiredCapabilities.chrome();
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
            case "edge":
                desiredCapabilities = DesiredCapabilities.edge();
                break;
            default:
                desiredCapabilities = DesiredCapabilities.chrome();
        }
        
        desiredCapabilities.setPlatform(Platform.LINUX);
        desiredCapabilities.setVersion("");

        driver = null;
        try {
            driver = new RemoteWebDriver(new URL(GuiConfig.REMOTE_URL), desiredCapabilities);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}