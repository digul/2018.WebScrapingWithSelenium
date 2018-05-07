package com.coska.lab.selenium.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPageDemo {

	public static void main(String[] args) {

		 System.setProperty("webdriver.chrome.driver", "D:/Coska/drivers/chromedriver/chromedriver.exe");
		//	System.setProperty("webdriver.gecko.driver", "C:/Coska/drivers/geckodriver-v0.20.1-win64/geckodriver.exe");
		
		/* Test to login and submit input data */
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.surveymonkey.com/user/sign-in/");
		
		WebElement loginID = driver.findElement(By.id("username"));
		loginID.sendKeys("*** Enter User ID Here ***");
		WebElement loginPassword = driver.findElement(By.id("password"));
		loginPassword.sendKeys("*** Enter Password Here***");
		loginPassword.submit();

	}

}
