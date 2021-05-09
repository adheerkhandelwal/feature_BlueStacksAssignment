package com.testUI.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testUI.ExtentReports.ExtentTestManager;
import com.testUI.Utilities.DriverManager;



public abstract class BasePage<T> {

	
	
	protected WebDriver driver;
	
	  

	    public BasePage() {
	        this.driver = DriverManager.getDriver();
	    }

	  
	    
	    
	    public T openPage(Class<T> clazz) {
	        T page = null;
	       
	            driver = DriverManager.getDriver();
	            page = PageFactory.initElements(driver, clazz);
	        return page;
	    }

	    
		public void click(WebElement element, String elementName) {
			
			element.click();
			ExtentTestManager.testReport.get().info("Clicking on : "+elementName);
			
		}
		
		public void type(WebElement element, String value, String elementName) {
			
			element.sendKeys(value);
			ExtentTestManager.testReport.get().info("Typing in : "+elementName+" entered the value as : "+value);
		
		}
		
		public boolean isElementPresent(WebElement element, String elementName)
		{
			ExtentTestManager.testReport.get().info("Checking if Element is present with element Name: "+elementName);
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			if(element.isDisplayed())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public boolean isElementClickable(WebElement element, String elementName)
		{
			ExtentTestManager.testReport.get().info("Checking if Element is clickable with element Name: "+elementName);
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if(element.isEnabled())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	
}
