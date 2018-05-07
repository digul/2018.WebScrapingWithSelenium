package com.coska.lab.selenium.monkey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coska.lab.selenium.util.ConfigUtil;

public class DashboardPageTest {

	static DashboardPage dashboardPage = null;
  
	
	@BeforeClass
	public static void loginAndDeclarePage() throws Exception {
		LoginPage loginPage = Pages.loginPage();
		loginPage.goTo();
		loginPage.logIn();		
		dashboardPage = Pages.dashboardPage();
	}
	
	@Before
	public void gotoDashBoard() {
		dashboardPage.goTo();
	}
	
	

	//@Test
	public void testFindResultFromAllSurveys() {
		String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");

		Pages.allSurveysPage().goTo();
		
		SurveyPage surveyPage = Pages.allSurveysPage().getSurveyPathPage(defaultSurveyName);
		surveyPage.goTo();
		 
		Assert.assertTrue(surveyPage.isAt());
	}

	
	@Test
	public void testClickDefaultResultLink() {

		// This is request from CKLAB-002 issue. 
		String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");
		
		Pages.dashboardPage().goTo();
		Assert.assertTrue(dashboardPage.isAt());
		
		SurveyPage surveyPage = Pages.dashboardPage().getSurveyPathPage(defaultSurveyName);
		surveyPage.goTo();
		 
		Assert.assertTrue(surveyPage.isAt());
		
	}
	
	//@AfterClass
	public static void cleanUp() {
		Pages.driver.close();
	} 
}
