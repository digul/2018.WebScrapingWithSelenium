package com.coska.lab.selenium.monkey;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageTest {

  static WebDriver driver = new FirefoxDriver();

	@Test
	public void canGoToHomePage() {
		HomePage homePage = new HomePage(driver);
		homePage.goTo();
		Assert.assertTrue(homePage.isAt());
	}
	
	@Test
	public void canGoToLoginPage() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.logIn();		
		Assert.assertTrue(loginPage.isAt());
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.close();
	} 
}
