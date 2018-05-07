package com.coska.lab.selenium.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	protected WebDriver driver;
	protected WebDriverWait wait;

    public Page(WebDriver driver) 
    {
      this.driver = driver;
      this.wait = new WebDriverWait(driver,10);
    }
    
    public abstract void goTo();
    public abstract boolean isAt();
	
	protected void wait(By by) {
		this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
    
}
