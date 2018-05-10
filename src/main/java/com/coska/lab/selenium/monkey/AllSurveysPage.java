package com.coska.lab.selenium.monkey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.Log;

public class AllSurveysPage extends Page {
	
	public AllSurveysPage(WebDriver driver) {
		super(driver);
	}

	private static String url = ConfigUtil.getConfig("monkey.url") + "/home/?ut_source=dashboard_survey_list";

	@Override
	public void goTo() {
		Log.trace("## goto "+ url);
		driver.get(url);
	}

	@Override
	public boolean isAt() {
		try {
			return (driver.findElement(By.id("module-active-surveys-new")) != null);
			
		} catch (Exception e) {
			Log.trace("This is re-located another(i.e. login) page.");
			return false;
		}
	}
	
	public SurveyPage getSurveyPathPage(String surveyName) {
		this.wait(By.cssSelector(".main.table")); // survey link text is in here
		return Pages.surveyPage(surveyName);
	}
}
