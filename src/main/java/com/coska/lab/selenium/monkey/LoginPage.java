package com.coska.lab.selenium.monkey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.ConfigUtil;

public class LoginPage extends Page {
	static String url = ConfigUtil.getConfig("monkey.url") + "/user/sign-in/";
	static String title = "Log in to your account";
	
	public LoginPage(WebDriver driver) {
		super(driver);		
	}
	
	public void goTo() {
		driver.get(url);
	}
	
	public void logIn() {
		WebElement loginID = driver.findElement(By.id("username"));
		loginID.sendKeys(ConfigUtil.getConfig("monkey.id"));
    
		WebElement loginPassword = driver.findElement(By.id("password"));
		loginPassword.sendKeys(ConfigUtil.getConfig("monkey.pwd"));
    
		loginPassword.submit();
	}
	
	public boolean isAt() {
		return driver.getTitle().equals(title);
	}
}
