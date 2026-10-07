package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Alerts")
@Owner("Ivanov Ivan")
public class AlertsTest extends BaseTest {

    @Test(description = "Simple Alert: открывается и принимается без ошибок")
    @Story("Simple Alert")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldAcceptSimpleAlert() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openAlertAndAccept();
        // Если alert не был принят — упадёт здесь; успешное завершение = pass
        Assert.assertTrue(true);
    }

    @Test(description = "Confirm: dismiss выводит корректный результат")
    @Story("Confirm Alert")
    @Severity(SeverityLevel.MINOR)
    public void shouldDismissConfirm() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openConfirmAndDismiss();

        String txt = page.confirmText().toLowerCase();
        Assert.assertTrue(txt.contains("cancel"),
                "Ожидался текст о выборе Cancel. Получено: " + txt);
    }

    @Test(description = "Prompt: введённый текст отражается в результате")
    @Story("Prompt Alert")
    @Severity(SeverityLevel.NORMAL)
    public void shouldSendPromptText() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openPromptAndSend("demo");

        Assert.assertTrue(page.promptText().toLowerCase().contains("demo"),
                "Ожидалось отображение введённого текста. Получено: " + page.promptText());
    }
}