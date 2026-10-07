package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProgressBarPage;

@Epic("Widgets")
@Feature("Progress Bar")
@Owner("Ivanov Ivan")
public class ProgressBarTest extends BaseTest {

    @Test(description = "Позитив: прогресс доходит до 100%")
    @Story("Start progress bar")
    @Severity(SeverityLevel.NORMAL)
    public void shouldCompleteTo100Percent() {
        ProgressBarPage page = new ProgressBarPage(driver, wait).open().start();
        page.waitForValue(100);

        Assert.assertTrue(page.currentText().contains("100"),
                "Ожидалось значение 100%. Получено: " + page.currentText());
    }
}