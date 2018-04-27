package com.coska.lab.selenium.monkey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.coska.lab.selenium.lib.Page;

public class LoginPage extends Page {
	static String url = "https://www.surveymonkey.com/user/sign-in/";
	static String title = "Log in to your account";
	
	public LoginPage(WebDriver driver) {
		super(driver);		
	}
	
	public void goTo() {
		driver.get(url);
	}
	
	public void logIn() {
		WebElement loginID = driver.findElement(By.id("username"));
		loginID.sendKeys("*** Enter User ID Here ***");
    
		WebElement loginPassword = driver.findElement(By.id("password"));
		loginPassword.sendKeys("*** Enter Password Here***");
    
		loginPassword.submit();
	}
	
	public boolean isAt() {
		return driver.getTitle().equals(title);
	}
}
