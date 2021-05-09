package com.testUI.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.testUI.ExtentReports.ExtentTestManager;
import com.testUI.Utilities.MatcherException;

import POJO.LocationObjects;

@SuppressWarnings("rawtypes")
public class SearchResultPage extends BasePage {

	@FindBy(xpath="//div[contains(@class, 'CurrentConditions--primary')]//span")
	protected WebElement tempearture_val;
	
	
	public boolean checkSearchResultPages()
	{
		boolean flg = isElementPresent(tempearture_val, "SearchResultsPage");
		return flg;
	}
	
	public String getTemperatureValue()
	{
		
		return tempearture_val.getText();
	}
	
	
	public boolean compareMapObjects(int ui_val, int api_val, int variance) throws MatcherException
	
	{
		
		
		int diff = (ui_val - api_val);
			
		if(diff<=variance)
		{
			return true;
			
		}
		else
		{	
			throw new MatcherException("API value and UI value is not matching");
			
		}
		
		}
		
		
	
		
	
}
