package pl.jsystems.qa.qagui.classic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

@Tags({@Tag("Frontend"), @Tag("Window")})
public class WindowsTest extends ConfigFrontEnd {

    @DisplayName("Window test")
    @Test
    public void windowTest() {

        String firstPageWindow = null;
        String secondWindow = null;
        String urlDiary = "http://www.testdiary.com/training/selenium/selenium-test-page/";
        String openWindow = "Open page in a new window";
        By openWindowLink = By.linkText(openWindow);

        driver.navigate().to(urlDiary);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(openWindowLink));
        WebElement hyperlinkElement = driver.findElement(openWindowLink);
        firstPageWindow = driver.getWindowHandle();

        int hyperlinkElementYCoord = hyperlinkElement.getLocation().getY();
        int hyperlinkElementXCoord = hyperlinkElement.getLocation().getX();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(" + hyperlinkElementXCoord + "," + hyperlinkElementYCoord + ")", "");

        wait.until(ExpectedConditions.elementToBeClickable(openWindowLink));
        hyperlinkElement.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String window: windowHandles) {
            if (!firstPageWindow.equals(window)){
                secondWindow = window;
            }
        }

        driver.switchTo().window(secondWindow);

        System.out.println(secondWindow.toString());
        System.out.println(firstPageWindow.toString());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("testpagelink")));

        driver.switchTo().window(secondWindow).close();
        driver.switchTo().window(firstPageWindow);

        wait.until(ExpectedConditions.visibilityOfElementLocated(openWindowLink));
    }

    @DisplayName("scroll")
    @Test
    public void pageScroll() {

        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.navigate().to(contactUrl);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in the same window")));

        int hyperlinkYCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getY();

        int hyperlinkXCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getX();

        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;

        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in the same window")));

        driver.findElement(By.linkText("Open page in the same window")).click();
    }

    @Test
    public void popupHandler() {

        driver.switchTo().alert();
        driver.findElement(By.id("userId")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().defaultContent();

        String pageId = driver.getWindowHandle();
        driver.switchTo().window(pageId);

        String title = driver.getTitle();
        assertThat(title).isEqualTo("title");
    }
}
