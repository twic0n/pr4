package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage extends BasePage {

    private static final String URL = "https://demoqa.com/buttons";

    private final By doubleClickBtn    = By.id("doubleClickBtn");
    private final By rightClickBtn     = By.id("rightClickBtn");
    private final By doubleClickMsg    = By.id("doubleClickMessage");
    private final By rightClickMsg     = By.id("rightClickMessage");
    private final By dynamicClickMsg   = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Buttons")
    public ButtonsPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Двойной клик по кнопке")
    public ButtonsPage doubleClick() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn));
        new Actions(driver).doubleClick(el).perform();
        return this;
    }

    @Step("Правый клик по кнопке")
    public ButtonsPage rightClick() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn));
        new Actions(driver).contextClick(el).perform();
        return this;
    }

    @Step("Одиночный клик по кнопке двойного клика")
    public ButtonsPage singleClickOnDoubleClickButton() {
        click(doubleClickBtn);
        return this;
    }

    @Step("Проверить сообщение о двойном клике")
    public boolean isDoubleClickMessageVisible() {
        return isVisible(doubleClickMsg);
    }

    @Step("Проверить сообщение о правом клике")
    public boolean isRightClickMessageVisible() {
        return isVisible(rightClickMsg);
    }

    @Step("Проверить, что сообщение о двойном клике НЕ появилось (негатив)")
    public boolean isDoubleClickMessageAbsent() {
        return !isVisible(doubleClickMsg);
    }

    public String doubleClickMessageText() {
        return text(doubleClickMsg);
    }

    public String rightClickMessageText() {
        return text(rightClickMsg);
    }
}