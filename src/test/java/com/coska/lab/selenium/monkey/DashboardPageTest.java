package com.coska.lab.selenium.monkey;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.coska.lab.selenium.util.ConfigUtil;

public class DashboardPageTest {

  static WebDriver driver = null;
  static DashboardPage dashboardPage = null;
  
	
	@BeforeClass
	public static void loginAndDeclarePage() {
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.logIn();		
		dashboardPage = new DashboardPage(driver);
	}
	
	@Before
	public void gotoDashBoard() {
		dashboardPage.goTo();
	}
	
	
	@Test
	public void testDashboardPage() {
		// Dashboard 페이지가 호출되었는지 / 모든 element 가 있는지
		
		Assert.assertTrue(dashboardPage.isAt());
		
		// when can't find result link, find all surveys link
		WebElement allSurveyLink = dashboardPage.getAllSurveysLink();
		Assert.assertNotNull(allSurveyLink);
		
		// find result link with survey name (/src/resources/$ENV/conf.properties)
		WebElement resultLink = dashboardPage.getResultLink();
		Assert.assertNotNull(resultLink);
	}
	
	//@Test
	public void testClickAllSurveysLink() {
		WebElement allSurveyLink = dashboardPage.getAllSurveysLink();
		allSurveyLink.click();

		Assert.assertNotNull(driver.findElement(By.cssSelector("table.surveys")));
		
	}

	//@Test
	public void testFindResultFromAllSurveys() {
		// survey가 dashboard page에는 없고 all surveys page에만 있는 경우에 대한 테스트. 
		// 설문이 더 많아져야 테스트 가능.. 지금은 그냥 해당 survey name이 없는 경우를 테스트. 
		WebElement resultLink = dashboardPage.getResultLink("wrong survey name");
		
		Assert.assertNotNull(driver.findElement(By.cssSelector("table.surveys")));
		// moved to all-surveys page 
		Assert.assertNull(resultLink);	 
		// find result link with given survey name (fail)
	}

	
	@Test
	public void testClickDefaultResultLink() {
		// This is request from CKLAB-002 issue. 
		// conf.properties 에 각자의 정보 입력해야 테스트 성공합니다. 
		String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");
		
		WebElement resultLink = dashboardPage.getResultLink();
		resultLink.click();
		
		// survey name이 여기에 있는 경우 호출 성공 
		Assert.assertTrue(
			defaultSurveyName.equals(
				driver.findElement(By.className("global-navigation-header-title")).getText()
			)
		);
		
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.close();
	} 
}
