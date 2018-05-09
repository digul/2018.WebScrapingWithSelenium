package com.coska.lab.selenium.monkey;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.coska.lab.selenium.util.ConfigUtil;


public class ResultPageTest {
	
	static WebDriver driver = null;
	static DashboardPage dashBoardPage = null;
	static WebElement resultLink = null;
	
	@BeforeClass
	public static void loginAndDashBoard() {
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.logIn();
		dashBoardPage = Pages.dashboardPage();
		dashBoardPage.goTo();
	}
	
	@Before
	public static void getResultLink() {
		//go to all survey page
		String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");
		Pages.allSurveysPage().goTo();
		
		//find the target survey and go to the survey page
		SurveyPage surveyPage = Pages.allSurveysPage().getSurveyPathPage(defaultSurveyName);
		surveyPage.goTo();
	}
	
	@Test
	public void testResultPage() {
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goTo();
		Assert.assertTrue(resultPage.isAt());
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.close();
	}
}
