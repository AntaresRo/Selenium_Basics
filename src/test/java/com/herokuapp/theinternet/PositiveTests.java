package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() {

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
        password.sendKeys("SuperSecretPassword!");
        // click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        // verifications:
        // new url
        String expectedUrl ="https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "actual page url is not the same as expected");


        // logout button is visible;
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "button is not visible");
        // succesfull login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSucessMessage = successMessage.getText();
        Assert.assertTrue(actualSucessMessage.contains(expectedSuccessMessage), "actual message is not as expected.\nActual Message: " + actualSucessMessage);

        // close browser
        driver.quit();
    }

    private void Sleep( long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
