package com.coska.lab.selenium.monkey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;

import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.Log;

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
	public void testGrepIndividualResponses() {

		// This is request from CKLAB-002 issue. 
		String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");
		
		Pages.dashboardPage().goTo();
		Assert.assertTrue(dashboardPage.isAt());
		
		SurveyPage surveyPage = Pages.dashboardPage().getSurveyPathPage(defaultSurveyName);
		surveyPage.goTo();
		 
		Assert.assertTrue(surveyPage.isAt());

		Pages.analyzePage().goTo();
		Assert.assertTrue(Pages.analyzePage().isAt());
		
		List<Map<String,Object>> resultList = Pages.analyzePage().getIndividualResponses();
		Assert.assertNotNull(resultList);

		Log.trace("\n ## trace result ##");
		resultList.stream().forEach(result -> {
			Log.trace("[Next respondent]===============");
			result.entrySet().stream().forEach(entry -> {
				Log.trace(entry.getKey() +" = "+ entry.getValue());
			});
			
		});
		Log.trace("total : "+ resultList.size() +" responses in this survey.");
		
	}
	
	//@AfterClass
	public static void cleanUp() {
		Pages.driver.close();
	} 
}
