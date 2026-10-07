package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class BrowserWindowsPage extends BasePage {

    private static final String URL = "https://demoqa.com/browser-windows";

    private final By tabButton     = By.id("tabButton");
    private final By sampleHeading = By.id("sampleHeading");

    public BrowserWindowsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Browser Windows")
    public BrowserWindowsPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Кликнуть 'New Tab' и переключиться на новую вкладку")
    public BrowserWindowsPage openNewTabAndSwitch() {
        String original = driver.getWindowHandle();
        Set<String> before = driver.getWindowHandles();

        click(tabButton);

        wait.until(d -> d.getWindowHandles().size() > before.size());
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        for (String h : handles) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(sampleHeading));
        return this;
    }

    @Step("Получить текст заголовка новой вкладки")
    public String sampleHeadingText() {
        return text(sampleHeading);
    }

    @Step("Закрыть текущую вкладку и вернуться на исходную")
    public BrowserWindowsPage closeCurrentAndSwitchBack() {
        driver.close();
        for (String h : driver.getWindowHandles()) {
            driver.switchTo().window(h);
            break;
        }
        return this;
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }
}