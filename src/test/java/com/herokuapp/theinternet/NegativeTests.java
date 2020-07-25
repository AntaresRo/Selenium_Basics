package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
    @Parameters ({"username", "password", "expectedMessage" })
    @Test (priority = 1, groups = {"negativetests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        System.out.println("Starting test");

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

        // sleep for 3seconds
        Sleep(3000);
        // maximize  browser window
        driver.manage().window().maximize();

        // enter username
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);
        // enter password
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);
        // click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));

        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));

        System.out.println("Correct error");

        // close browser
        driver.quit();


    }


    /*@Test (priority = 2, groups = {"negativetests"})
    public void negativePasswordTest() {

        System.out.println("Starting test");

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

        // sleep for 3seconds
        Sleep(3000);
        // maximize  browser window
        driver.manage().window().maximize();

        // enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        // enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword");
        // click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));

        System.out.println("Correct error");

        // close browser
        driver.quit();


    }*/

    private void Sleep( long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
