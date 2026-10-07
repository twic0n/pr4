package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {

    private static final String URL = "https://demoqa.com/alerts";

    private final By alertButton   = By.id("alertButton");
    private final By confirmButton = By.id("confirmButton");
    private final By promptButton  = By.id("promtButton");   // именно так в DemoQA
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult  = By.id("promptResult");

    public AlertsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Alerts")
    public AlertsPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Открыть простой alert и принять его")
    public AlertsPage openAlertAndAccept() {
        click(alertButton);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        a.accept();
        return this;
    }

    @Step("Открыть confirm и нажать Cancel (dismiss)")
    public AlertsPage openConfirmAndDismiss() {
        click(confirmButton);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        a.dismiss();
        return this;
    }

    @Step("Открыть prompt, ввести '{text}' и нажать OK")
    public AlertsPage openPromptAndSend(String text) {
        click(promptButton);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        a.sendKeys(text);
        a.accept();
        return this;
    }

    @Step("Получить текст confirm-результата")
    public String confirmText() {
        return text(confirmResult);
    }

    @Step("Получить текст prompt-результата")
    public String promptText() {
        return text(promptResult);
    }
}