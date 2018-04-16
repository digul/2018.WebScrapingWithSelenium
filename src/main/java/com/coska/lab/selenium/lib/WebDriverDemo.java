package com.coska.lab.selenium.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/Coska/drivers/chromedriver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:/Coska/drivers/geckodriver-v0.20.1-win64/geckodriver.exe");
		
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.naver.com/");
	}

}
