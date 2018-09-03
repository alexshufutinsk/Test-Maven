package ru.stqa.selenium;

import org.apache.xalan.lib.ExsltDatetime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.selenium.pages.HomePage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  public void testHomePageHasAHeader() {
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Go to Event list')]"));
    System.out.println("Link text: " + element.getText());
            element.click();

    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    element = driver.findElement(By.xpath("//span[contains(text(),'Filters')]"));
    System.out.println("Name of the button: " + element.getText());

    driver.get(baseUrl);
    WebElement loginButton = waitUntilElementIsLoaded(By.xpath("//span[contains(text(),'Login')]"),40,
            "The page with Login is not loaded");
    loginButton.click();

    WebElement inputLogin = waitUntilElementIsLoaded(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/input"),
            40,"The Login form is not loaded");

    List<WebElement> webElements = driver.findElements(By.xpath("//div [@class='mat-input-infix mat-form-field-infix']/input"));
    System.out.println("class: "+inputLogin.getAttribute("class"));
    inputLogin.click();
    inputLogin.sendKeys("alexshufutinsk@gmail.com");

    WebElement inputPassword = webElements.get(1);
    inputPassword.click();
    inputPassword.sendKeys("1234567");

    WebElement buttonLogin = driver.findElement(By.xpath("//span[contains(text(),'Log in')]"));
    buttonLogin.click();

    WebElement filterCities = waitUntilElementIsLoaded(By.xpath("//div[@class='mat-select-value']"),
            40,"Page is not loaded");
    filterCities.click();

    Assert.assertTrue(true);
  }

  WebElement waitUntilElementIsLoaded(By by, int time, String message){
      WebElement element = driver.findElement(by);
      while(!element.isDisplayed()) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        System.out.println(message);
      }
    return  element;
  }
}
