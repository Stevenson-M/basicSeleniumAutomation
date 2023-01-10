package test;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class singUpTest {
    private org.openqa.selenium.WebDriver driver;


    @BeforeTest
    public void setUp (){

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://opencart.abstracta.us/index.php?route=account/register");
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();

    }

    @Test
    public void singUpSuccessful() throws InterruptedException {

        WebElement userNameField = driver.findElement(By.id("input-firstname"));
        WebElement lastNameField = driver.findElement(By.id("input-lastname"));
        WebElement emailField = driver.findElement(By.id("input-email"));
        WebElement phoneNameField = driver.findElement(By.id("input-telephone"));
        WebElement passwordField = driver.findElement(By.id("input-password"));
        WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));

        WebElement newsLetterNoRadioButton = driver.findElement(By.cssSelector("#content > form > fieldset:nth-child(3) > div > div > label:nth-child(2) > input[type=radio]"));
        WebElement policyCheck = driver.findElement(By.cssSelector("#content > form > div > div > input[type=checkbox]:nth-child(2)"));
        WebElement continueButton = driver.findElement(By.cssSelector(".btn.btn-primary "));


        Faker faker = new Faker();
        userNameField.sendKeys(faker.name().firstName());
        lastNameField.sendKeys(faker.name().lastName());
        passwordField.sendKeys("Aneka123");
        confirmPasswordField.sendKeys("Aneka123");
        emailField.sendKeys(faker.internet().emailAddress());
        phoneNameField.sendKeys(faker.phoneNumber().cellPhone());
        newsLetterNoRadioButton.click();
        policyCheck.click();
        continueButton.click();

        Assert.assertEquals("http://opencart.abstracta.us/index.php?route=account/success", driver.getCurrentUrl());

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}


