package com.coska.lab.selenium.monkey;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.ConfigUtil;
import com.coska.lab.selenium.util.Log;

public class DashboardPage extends Page {
	private static String url = ConfigUtil.getConfig("monkey.url") + "/dashboard/";
	private static String defaultSurveyName = ConfigUtil.getConfig("monkey.surveyName");
	
	private WebDriverWait wait = new WebDriverWait(driver,10);

	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo() {
		this.driver.get(url);
	}
	
	/**
	 * 찾는 이름의 설문이 dashboard 페이지에 없을 수 있음. 
	 * 이 경우에는 getAllSurveysLink 를 클릭하여 여기에서 찾아야 합니다. 
	 * @return WebElement reslutLink element
	 */
	public WebElement getResultLink() {
		return getResultLink(defaultSurveyName);
	}
	
	public WebElement getResultLink(String surveyName) {
		if(!isAt()) {
			return null;
		}

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.survey-items-list")));
		
		WebElement resultLink = null;
		try {
			resultLink = driver.findElement(By.linkText(surveyName));
			// link text로 element 못찾는경우 null 반환되지 안하고 걍 selenium exception발생됨. 
		} catch(NoSuchElementException nse) {
			Log.trace("There isn't '"+ surveyName +"' survey in dashboard page. It moves to all-surveys page.. ");
			return getResultLinkFromAllSurveys(surveyName);
		}
		
		return resultLink;
	}
	
	public WebElement getAllSurveysLink() {
		if(!isAt()) {
			return null;
		}

		By allSurveyLinkLocator = By.className("view-all-surveys"); 
		
		wait.until(ExpectedConditions.presenceOfElementLocated(allSurveyLinkLocator));
		
		return driver.findElement(allSurveyLinkLocator);
	}
	
	/**
	 * TODO 하나의 Page 오브젝트 내에서 페이지를 이동해도 설계상 괜찮은지..?
	 * @return
	 */
	private WebElement getResultLinkFromAllSurveys(String surveyName) {
		this.getAllSurveysLink().click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".main.table")));
		
		//TODO survey가 많으면 다른 페이지로 넘어가서 찾아야 함 
		try {
			return driver.findElement(By.linkText(surveyName));
		} catch(NoSuchElementException nse) {
			Log.trace("There isn't '"+ surveyName +"' survey in all-surveys page..");
			return null;
		}
	}
	
	public boolean isAt() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("html")));
		
		if(DashboardPage.url.equals(driver.getCurrentUrl())) {
			return true;
		}
		
		Log.trace("This is re-located another(i.e. login) page.");
		
		return false;
	}
}
