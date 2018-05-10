package com.coska.lab.selenium.monkey;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.Log;

public class DashboardPage extends Page {
	private static String url = ConfigUtil.getConfig("monkey.url") + "/dashboard/";

	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	public void goTo() {
		Log.trace("## goto "+ url);
		this.driver.get(url);
	}

	public boolean isAt() {

		try {
			return (driver.findElement(By.id("dashweb-scope")) != null);
			
		} catch (Exception e) {
			Log.trace("This is re-located another(i.e. login) page.");
			return false;
		}
	}
	

	public SurveyPage getSurveyPathPage(String surveyName) {
		this.wait(By.cssSelector("ul.survey-items-list")); // survey link text is in here
		return Pages.surveyPage(surveyName);
	}
}
