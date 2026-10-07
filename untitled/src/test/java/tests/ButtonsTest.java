package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ButtonsPage;

@Epic("Elements")
@Feature("Buttons")
@Owner("Ivanov Ivan")
public class ButtonsTest extends BaseTest {

    @Test(description = "Позитив: двойной клик приводит к появлению сообщения")
    @Story("Double click")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReactToDoubleClick() {
        ButtonsPage page = new ButtonsPage(driver, wait).open().doubleClick();

        Assert.assertTrue(page.isDoubleClickMessageVisible(),
                "Ожидалось сообщение о двойном клике.");
    }

    @Test(description = "Негатив: одиночный клик НЕ вызывает сообщение о двойном клике")
    @Story("Single click does not equal double click")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNotTriggerDoubleClickOnSingleClick() {
        ButtonsPage page = new ButtonsPage(driver, wait).open()
                .singleClickOnDoubleClickButton();

        Assert.assertTrue(page.isDoubleClickMessageAbsent(),
                "Сообщение о двойном клике НЕ должно появляться после одиночного клика.");
    }
}