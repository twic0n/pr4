package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AllureAttachments;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Parameters({"browser", "headless"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browser,
                      @Optional("") String headless) {

        // Приоритет: параметры testng.xml -> системные свойства Maven -> значения по умолчанию
        String br = (browser == null || browser.isBlank())
                ? System.getProperty("browser", "chrome")
                : browser;
        String hl = (headless == null || headless.isBlank())
                ? System.getProperty("headless", "false")
                : headless;

        driver = createDriver(br, Boolean.parseBoolean(hl));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Создание драйвера для браузера {browser} (headless={headless})")
    private WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                return new FirefoxDriver(fo);
            }
            case "edge" -> {
                EdgeOptions eo = new EdgeOptions();
                if (headless) eo.addArguments("--headless=new");
                return new EdgeDriver(eo);
            }
            default -> {
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new");
                return new ChromeDriver(co);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null && !result.isSuccess()) {
            AllureAttachments.screenshot(driver, "Failure screenshot");
            AllureAttachments.pageSource(driver, "Page source on failure");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}