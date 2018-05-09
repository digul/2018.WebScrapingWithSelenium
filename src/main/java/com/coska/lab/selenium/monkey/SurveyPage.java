package com.coska.lab.selenium.monkey;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.Log;

public class SurveyPage extends Page {
	protected String linkName;	// link text name
	
	
	@FindBy(how = How.CLASS_NAME, using = "global-navigation-header-title" )
	WebElement surveyElement; 	// survey page title area
	
	public SurveyPage(WebDriver driver, String surveyName) {
		super(driver);
		this.linkName = surveyName;
	}
	
	@Override
	public void goTo() {
		try {
			WebElement linkElement = this.driver.findElement(By.linkText(this.linkName));
			linkElement.click();
			
			// declare with survey name
		} catch(NoSuchElementException nse) {
			Log.trace("Cannot find '"+ this.linkName +"' link in this page.");
		}
		
	}

	@Override
	public boolean isAt() {
		return this.linkName.equals(surveyName());	
	}
	
	public String surveyName() {
		return this.surveyElement.getText();
	}
	
	public AnalyzePage getAnalyzePage() {
		this.wait(By.className("global-navigation-header-tabs-left"));
		return Pages.analyzePage();
	}

}
