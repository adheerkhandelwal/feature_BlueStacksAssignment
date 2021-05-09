package com.testUI.PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testUI.Utilities.DriverManager;

@SuppressWarnings("rawtypes")
public class WeatherHomePage extends BasePage
{

	@FindBy(xpath="//input[@id='LocationSearch_input']")
	protected WebElement searchWeatherbtn;
	
	

	@FindBy(xpath="//div[@id ='LocationSearch_listbox']/button")
	protected List<WebElement> search_option_lst;
	

	
	@SuppressWarnings("unchecked")
	public WeatherHomePage open(String url)
	{
		System.out.println("Page Opened !");
		DriverManager.getDriver().navigate().to(url);
		return (WeatherHomePage)openPage(WeatherHomePage.class);
	}
	
	
	public boolean isSearchBtnPresent()
	{
		
		boolean flg = isElementPresent(searchWeatherbtn, "SearchButton");
		return flg;
	}
	
	public void enterSearchCity(String value)
	
	{   
		boolean flg = isElementClickable(searchWeatherbtn, "SearchButton");
		
		if (flg)
		{
		click(searchWeatherbtn, "SearchButton");
		type(searchWeatherbtn, value, "SearchButton");
		}
	}

	
	@SuppressWarnings("unchecked")
	public SearchResultPage selectCityFromListbox(String value)
	{
		boolean flg = isElementPresent(search_option_lst.get(0), "Search List Box");
		
		if(flg)
		{
			
			List<WebElement> lst = search_option_lst;
			
			for(WebElement ele:lst)
			{
				
				if(ele.getText().contains(value))
				{
					ele.click();
					
					return (SearchResultPage)openPage(SearchResultPage.class);
				}
			}
			
	
			
			
		}
		
		return null;
		
	}
	
	
	
	
}
