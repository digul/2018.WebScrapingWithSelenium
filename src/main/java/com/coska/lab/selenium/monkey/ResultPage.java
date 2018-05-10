package com.coska.lab.selenium.monkey;

import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.Log;

public class ResultPage extends Page {
	
	static String analyzeResults = "ANALYZE RESULTS";
	static String title = "global-navigation-header-title notranslate  notranslate ";
	protected String surveyName = ConfigUtil.getConfig("monkey.surveyName");
	
	public ResultPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public void goTo() {
		try {
			WebElement resultLink = this.driver.findElement(By.linkText(analyzeResults));
			resultLink.click();
		} catch(NoSuchElementException exc) {
			Log.trace("There is no such element");
		}
	}
	
	@Override
	public boolean isAt() {
		return this.surveyName.equals(surveyTitle());
	}
	
	public String surveyTitle() {
		return driver.findElement(By.className(title)).getText();
	}
}
