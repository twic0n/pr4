package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBoxPage extends BasePage {

    private static final String URL = "https://demoqa.com/text-box";

    private final By userName         = By.id("userName");
    private final By userEmail        = By.id("userEmail");
    private final By currentAddress   = By.id("currentAddress");
    private final By permanentAddress = By.id("permanentAddress");
    private final By submit           = By.id("submit");
    private final By output           = By.id("output");

    public TextBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Text Box")
    public TextBoxPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Заполнить форму: name={name}, email={email}, current={current}, permanent={permanent}")
    public TextBoxPage fill(String name, String email, String current, String permanent) {
        type(userName, name);
        type(userEmail, email);
        type(currentAddress, current);
        type(permanentAddress, permanent);
        return this;
    }

    @Step("Нажать Submit")
    public TextBoxPage submit() {
        click(submit);
        return this;
    }

    @Step("Проверить, что блок результата отображается")
    public boolean isOutputVisible() {
        return isVisible(output);
    }

    @Step("Получить текст блока результата")
    public String outputText() {
        return text(output);
    }

    @Step("Получить значение атрибута class у поля Email")
    public String emailFieldClass() {
        return getAttribute(userEmail, "class");
    }
}