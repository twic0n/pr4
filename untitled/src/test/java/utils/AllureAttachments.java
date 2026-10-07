package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public final class AllureAttachments {

    private AllureAttachments() {}

    public static void screenshot(WebDriver driver, String name) {
        if (driver == null) return;
        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, "image/png", new ByteArrayInputStream(bytes), ".png");
        } catch (Exception ignored) {
            // драйвер уже закрыт и т.п.
        }
    }

    public static void pageSource(WebDriver driver, String name) {
        if (driver == null) return;
        try {
            Allure.addAttachment(name, "text/html", driver.getPageSource(), ".html");
        } catch (Exception ignored) {
        }
    }

    public static void text(String name, String content) {
        Allure.addAttachment(name, "text/plain", content, ".txt");
    }
}