package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 8/31/2018.
 */
public class CreateAccountTest extends TestNgTestBase {
    @Test
    public void testCreateAccount(){
        driver.get(baseUrl);
        WebElement createAccountButton = waitUntilElementLoaded(By.xpath("//span[contains(text(),'Create Account')]"),
                40,"Element not Loaded");
        createAccountButton.click();
        waitUntilElementLoaded(By.xpath("//div[@class='mat-input-infix mat-form-field-infix']/input"),
                40, "Element not Loaded");
        List<WebElement> elementsList = driver.findElements(By.xpath(
                "//div[@class='mat-input-infix mat-form-field-infix']/input"));

        WebElement addEmailField = elementsList.get(0);
        addEmailField.click();
        addEmailField.clear();
        addEmailField.sendKeys("aaaaaa@bbb.ccc");

        WebElement addPasswordField = elementsList.get(1);
        addPasswordField.click();
        addPasswordField.clear();
        addPasswordField.sendKeys("111111");

        WebElement repeatPasswordField = elementsList.get(2);
        repeatPasswordField.click();
        repeatPasswordField.clear();
        repeatPasswordField.sendKeys("111111");

        WebElement registrationButton = waitUntilElementLoaded(By.xpath("//span[contains(text(),'Registration')]"),
                10, "Element not Loaded");
        registrationButton.click();

        WebElement firstNameField = waitUntilElementLoaded(By.xpath("//input[@id='inputFirstName']"),
                40, "Element not Loaded");

        Assert.assertTrue(true);
    }

    WebElement waitUntilElementLoaded(By by, int time,String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
