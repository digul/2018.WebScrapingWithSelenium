package com.coska.lab.selenium.monkey;

import org.openqa.selenium.WebDriver;

import com.coska.lab.selenium.lib.Page;

public class HomePage extends Page {

	static String url = "https://www.surveymonkey.com";
	static String title = "SurveyMonkey: The World’s Most Popular Free Online Survey Tool";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo() {
		driver.get(url);
	}
	
	public void goToLogin() {
		driver.findElement(By.linkText("LOG IN")).click();
	}
	
	public boolean isAt() {

		return driver.getTitle().equals(title);
	}

}
