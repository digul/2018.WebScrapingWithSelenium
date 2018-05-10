package com.coska.lab.selenium.monkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.coska.lab.selenium.lib.Page;
import com.coska.lab.selenium.util.Log;

public class AnalyzePage extends Page {

	String surveyName;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div[2]/div/ul[1]/li[5]" )
	WebElement analyzeElement; // analyze link area
	
	@FindBy(how = How.ID, using = "mode_tab_individual_responses")
	WebElement individualElement;
	
	@FindBy(how = How.CSS, using = "a[view-role=respondentMenuBtnView]")
	WebElement respondentElement;
	
	@FindBy(how = How.CSS, using = "form[action=goToRespondent]")
	WebElement respondentFormElement;
	
	@FindBy(how = How.CSS, using = "a[sm-next-btn]")
	WebElement nextBtnElement;
	
	public AnalyzePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void goTo() {
		Log.trace("## goto analyze page by click.. ");
		wait(analyzeElement);
		analyzeElement.click();
	}

	@Override
	public boolean isAt() {
		Log.trace("Analyze selected = " + analyzeElement.getAttribute("class").contains("selected"));
		return analyzeElement.getAttribute("class").contains("selected");
	}
	
	/**
	 * TODO refactoring
	 * @return
	 */
	public List<Map<String,Object>> getIndividualResponses() {
		wait(individualElement);
		individualElement.click();

		wait(respondentElement);
		respondentElement.click();

		wait(respondentFormElement);
		WebElement numberTextBox = respondentFormElement.findElement(By.cssSelector("input[sm-goto-number-text]"));
		numberTextBox.clear();
		numberTextBox.sendKeys("1");
		respondentFormElement.submit();
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		By getResultListSelector = By.cssSelector("div[view-role=questionResponseView]");
		By getKeySelector = By.cssSelector(".response-question-header .question-title");
		By getValueSelector = By.cssSelector("div[response-question-content] li span");
		// TODO find best selector.
		
		while(!nextBtnElement.getAttribute("class").contains("disabled")) {
			wait.until(ExpectedConditions.textToBePresentInElement(respondentElement, "#"+ (resultList.size() + 1)));
			wait(getResultListSelector);
			List<WebElement> responseElements = driver.findElements(getResultListSelector);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			responseElements.stream()
				.filter(question->question.findElement(getKeySelector).getText().length() > 0)
				.forEach(question -> {
					result.put(
						question.findElement(getKeySelector).getText(), 
						question.findElement(getValueSelector).getText()
					);
					Log.trace("## added {"+ question.findElement(getKeySelector).getText() 
								+":"+ question.findElement(getValueSelector).getText() +"} to map.");
				}
			);
			resultList.add(result);
			Log.trace("## added map to list. list size = "+ resultList.size());
			nextBtnElement.click();
		}
		
		return resultList;
		
	}

}
