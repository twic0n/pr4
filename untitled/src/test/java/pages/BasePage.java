package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Видимость элемента + прокрутка к центру (спасает от фиксированного футера DemoQA). */
    protected WebElement el(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        return element;
    }

    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        element.click();
    }

    protected void type(By locator, String text) {
        WebElement element = el(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String text(By locator) {
        return el(locator).getText().trim();
    }

    protected boolean isVisible(By locator) {
        try {
            return el(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getAttribute(By locator, String attr) {
        return driver.findElement(locator).getAttribute(attr);
    }
}