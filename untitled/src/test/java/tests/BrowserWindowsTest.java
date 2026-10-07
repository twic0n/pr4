package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Browser Windows")
@Owner("Ivanov Ivan")
public class BrowserWindowsTest extends BaseTest {

    @Test(description = "Позитив: New Tab открывает новую вкладку с заголовком")
    @Story("New Tab")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldOpenNewTabWithHeading() {
        BrowserWindowsPage page = new BrowserWindowsPage(driver, wait)
                .open()
                .openNewTabAndSwitch();

        Assert.assertTrue(page.sampleHeadingText().contains("This is a sample page"),
                "Ожидался заголовок новой вкладки. Получено: " + page.sampleHeadingText());
    }
}