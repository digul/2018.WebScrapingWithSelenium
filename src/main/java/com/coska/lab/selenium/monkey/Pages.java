package com.coska.lab.selenium.monkey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import com.coska.lab.selenium.util.ConfigUtil;

public class Pages {

	public static WebDriver driver = null;
	
	static {
		switch(ConfigUtil.getConfig("browser")) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "ie" : driver = new InternetExplorerDriver(); break;
		}
	}
	
	public static HomePage homePage() {
		return new HomePage(driver);
	}
	
	public static LoginPage loginPage() {
		return new LoginPage(driver);
	}
	
	public static DashboardPage dashboardPage() {
		return new DashboardPage(driver);
	}
	
	public static AllSurveysPage allSurveysPage() {
		return new AllSurveysPage(driver);
	}
	
	public static SurveyPage surveyPage(String surveyName) {
		SurveyPage surveyPage = new SurveyPage(driver, surveyName);
		PageFactory.initElements(driver, surveyPage);
		return surveyPage;
	}

	public static AnalyzePage analyzePage() {
		AnalyzePage analyzePage = new AnalyzePage(driver);
		PageFactory.initElements(driver, analyzePage);
		return analyzePage;
	}
}
