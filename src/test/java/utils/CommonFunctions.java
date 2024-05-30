package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CommonFunctions {

    WebDriver driver = Driver.getDriverInstance();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriverInstance();
    Actions action = new Actions(Driver.getDriverInstance());


    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public static void waitFor(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public void enterTextWhenVisible(By by, String text){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.clear();
        element.sendKeys(text);
    }

    public void clickWhenVisible(By by){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.click();
    }

    public void forceClickWhenVisible(By by){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        js.executeScript("arguments[0].click();", element);
    }

    public void actionClickWhenVisible(By by){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        action.moveToElement(element).click().perform();
    }

    public String getTextWhenVisible(By by){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text;
        try {
            if (!element.getText().isEmpty()){
                text = element.getText();
            }else{
                text = element.getAttribute("value");
            }
        }catch (Exception e){
            throw new NullPointerException();
        }
        return text;
    }
}
