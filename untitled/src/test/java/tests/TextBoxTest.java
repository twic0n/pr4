package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TextBoxPage;

@Epic("Elements")
@Feature("Text Box")
@Owner("Ivanov Ivan")
public class TextBoxTest extends BaseTest {

    @Test(description = "Позитив: валидные данные приводят к отображению блока результата")
    @Story("Submit valid form")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldSubmitValidData() {
        TextBoxPage page = new TextBoxPage(driver, wait).open();
        page.fill("Ivan Petrov", "ivan.petrov@example.com", "Moscow", "SPb")
                .submit();

        Assert.assertTrue(page.isOutputVisible(),
                "Ожидалось появление блока результатов.");
    }

    @Test(description = "Позитив: введённое имя отражается в блоке результата")
    @Story("Output content")
    @Severity(SeverityLevel.NORMAL)
    public void shouldEchoNameInOutput() {
        TextBoxPage page = new TextBoxPage(driver, wait).open();
        page.fill("Petr Sidorov", "petr@example.com", "Kazan", "Omsk")
                .submit();

        Assert.assertTrue(page.outputText().contains("Petr Sidorov"),
                "Ожидалось, что блок результата содержит имя. Получено: " + page.outputText());
    }
}