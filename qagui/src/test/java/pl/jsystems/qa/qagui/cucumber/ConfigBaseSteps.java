package pl.jsystems.qa.qagui.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
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

public class ConfigBaseSteps {
    protected WebDriver driver;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    public WebDriver setUpWebDriver() {
        if (GuiConfig.MACHINE.equals("local")) {
            setUpLocalConfiguration();
        } else {
            setUpRemoteConfiguration();
        }

        setUpDriver();
        return driver;
    }

    private void setUpRemoteConfiguration() {
        driver = setRemoteDriver();
    }

    private void setUpLocalConfiguration() {
//        try {
//            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("driver/chromedriver.exe").toURI()).toFile().getAbsolutePath());
//            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader().getResource("driver/geckodriver.exe").toURI()).toFile().getAbsolutePath());
//            System.setProperty("webdriver.edge.driver", Paths.get(getClass().getClassLoader().getResource("driver/msedgedriver.exe").toURI()).toFile().getAbsolutePath());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        driver = setWebDriver();
    }

    private void setUpDriver() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    private WebDriver setWebDriver() {

        switch (GuiConfig.BROWSER) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
        }

        return new ChromeDriver();
    }

    private WebDriver setRemoteDriver() {
        DesiredCapabilities desiredCapabilities = setUpDesCapabilities();
        driver = null;
        try {
            driver = new RemoteWebDriver(new URL(GuiConfig.REMOTE_URL), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    private DesiredCapabilities setUpDesCapabilities() {
        DesiredCapabilities desiredCapabilities;
        desiredCapabilities = setUpRemoteBrowser();

        desiredCapabilities.setPlatform(Platform.LINUX);
        desiredCapabilities.setVersion("");
        return desiredCapabilities;
    }

    private DesiredCapabilities setUpRemoteBrowser() {
        DesiredCapabilities desiredCapabilities;
        switch (GuiConfig.BROWSER) {
            case "chrome":
                desiredCapabilities = DesiredCapabilities.chrome();
                break;
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            case "edge":
                desiredCapabilities = DesiredCapabilities.edge();
                break;
            default:
                desiredCapabilities = DesiredCapabilities.chrome();
        }
        return desiredCapabilities;
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=========================== @After Cucumber Test  =======================================");
        String status;
        if(!scenario.isFailed()) {
            status = "( ͡° ͜ʖ ͡°)";
//            status = "++++++++++";
            scenario.log("Scenario passed");
        } else {
            status = "(✖╭╮✖)";
//            status = "-------------";
            scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"images/png", scenario.getName());
            scenario.log("Scenario failed");
        }
        System.out.println("\n"+status+" End of: " + scenario.getName() + " scenario.");
        driver.quit();
        driver = null;
    }
}