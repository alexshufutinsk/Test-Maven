package ru.stqa.selenium;

import com.sun.org.apache.xalan.internal.xslt.Process;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 8/21/2018.
 */
public class LoginTest extends TestNgTestBase{
    private HomePage homepage;

    @Test
    public void testLogin() {
        driver.get(baseUrl);
        WebElement element = waitUntilElementIsLoadedCustomTime(By.xpath("//span[contains(text(),'Login')]"),
                40, "Error: element is not loaded");
        element.click();

        WebElement loginField = waitUntilElementIsLoadedCustomTime(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/*[@type='email']"),
                40,"Error: element is not loaded");
        loginField.click();
        loginField.clear();
        loginField.sendKeys("alexshufutinsk@gmail.com");

        WebElement passwordField = waitUntilElementIsLoadedCustomTime(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/*[@type='password']"),
                40,"Error: element is not loaded");
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("123456");

        element = driver.findElement(By.xpath("//span[contains(text(),'Log in')]"));
        element.click();

        WebElement menuButton = waitUntilElementIsLoadedCustomTime(By.xpath("//mat-icon[@class='but mat-icon material-icons']"),
                40, "Error: element is not loaded");
        menuButton.click();

        Assert.assertTrue(true);
    }
    @Test
    public void testWrongPasswordLogin(){
        driver.get(baseUrl);
        WebElement element = waitUntilElementIsLoadedCustomTime(By.xpath("//span[contains(text(),'Login')]"),
                40, "Error: element is not loaded");
        element.click();

        WebElement loginField = waitUntilElementIsLoadedCustomTime(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/*[@type='email']"),
                40,"Error: element is not loaded");
        loginField.click();
        loginField.clear();
        loginField.sendKeys("alexshufutinsk@gmail.com");

        WebElement passwordField = waitUntilElementIsLoadedCustomTime(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/*[@type='password']"),
                40,"Error: element is not loaded");
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("777777");

        WebElement loginClick = waitUntilElementIsLoadedCustomTime(By.xpath("//span[contains(text(),'Log in')]"), 30,
                "uuuuups, no element, sorry");
        loginClick.click();

       WebElement errorMessage = waitUntilElementIsLoadedCustomTime(By.xpath("//*[contains(text(),'Wrong authorization, login or password')]"),
               40, "Error: element is not loaded");

        WebElement cancelButton = waitUntilElementIsLoadedCustomTime(By.xpath("//*[contains(text(),'Cancel')]"),
                30, "Error: Cancel button is not found");
        System.out.println("Text: "+ cancelButton.getText());
        cancelButton.click();

        pause(5000);

        WebElement eventsList = waitUntilElementIsLoadedCustomTime(By.xpath("//button [@class='mat-stroked-button']"),
                40, "Error Element not found");
        System.out.println("Button text: " + eventsList.getText());

        eventsList.click();
        WebElement filers = waitUntilElementIsLoadedCustomTime(By.xpath("//button[@class='mat-raised-button']"), 40,
               "YOOOOOOOO, NO Element :(");
        System.out.println("Button text: "+filers.getText());
        filers.click();
        Assert.assertTrue(true);

    }

    @Test
    public void testGToEventsList(){
        driver.get(baseUrl);

        WebElement eventsList = waitUntilElementIsLoadedCustomTime(By.xpath("//*[contains(text(),'Go to Event list')]"),
                40, "Error Element not found");
        System.out.println("Button text: " + eventsList.getText());
        eventsList.click();
        WebElement filters = waitUntilElementIsLoadedCustomTime(By.xpath("//button[@class='mat-raised-button']"), 30, "yoooo");
        System.out.println("Text: "+filters.getText());
        filters.click();

        Assert.assertTrue(true);

    }

    WebElement waitUntilElementIsLoadedCustomTime(By by, int time, String message){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(message);
        //return wait.until(ExpectedConditions.elementToBeClickable(by).);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
