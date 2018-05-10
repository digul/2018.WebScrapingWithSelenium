package com.coska.lab.selenium.monkey;

import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;

import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.FileUtil;
import com.google.gson.Gson;

public class ScrapingTest {
	static String surveyName = ConfigUtil.getConfig("monkey.surveyName");
	static String filePath = ConfigUtil.getConfig("file.path");
  
	
	@BeforeClass
	public static void loginAndDeclarePage() throws Exception {
		LoginPage loginPage = Pages.loginPage();
		loginPage.goTo();
		loginPage.logIn();	
	}
	
	@Test
	public void testScrapIndividualResponses() {
		gotoAnalizePage();
		Assert.assertTrue(Pages.analyzePage().isAt());
		
		List<Map<String,Object>> resultList = Pages.analyzePage().getIndividualResponses();
		Assert.assertNotNull(resultList);
		
		try {
			FileUtil.makeFile(new Gson().toJson(resultList), filePath);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertTrue(new File(filePath).exists());
		
	}
	
	private void gotoAnalizePage() {
		Pages.allSurveysPage().goTo();
		Pages.allSurveysPage().getSurveyPathPage(surveyName).goTo();
		Pages.analyzePage().goTo();
	}
	
	@AfterClass
	public static void cleanUp() {
		Pages.driver.close();
	} 
}
