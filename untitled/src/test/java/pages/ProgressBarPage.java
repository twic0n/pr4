package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressBarPage extends BasePage {

    private static final String URL = "https://demoqa.com/progress-bar";

    private final By startStopButton = By.id("startStopButton");
    private final By progressBar     = By.xpath("//div[@role='progressbar']");

    public ProgressBarPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Progress Bar")
    public ProgressBarPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Запустить прогресс")
    public ProgressBarPage start() {
        click(startStopButton);
        return this;
    }

    @Step("Дождаться значения прогресса = {value}")
    public ProgressBarPage waitForValue(int value) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                progressBar, String.valueOf(value) + "%"));
        return this;
    }

    @Step("Получить текущее значение прогресса")
    public String currentValue() {
        return getAttribute(progressBar, "aria-valuenow");
    }

    @Step("Получить отображаемый текст прогресса")
    public String currentText() {
        return text(progressBar);
    }
}