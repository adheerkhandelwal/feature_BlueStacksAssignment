package com.test.Steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import com.testUI.ExtentReports.ExtentTestManager;
import com.testUI.PageObjects.SearchResultPage;
import com.testUI.PageObjects.WeatherHomePage;
import com.testUI.Utilities.JsonReaderUtils;

import POJO.LocationObjects;

import static io.restassured.RestAssured.given;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherHomePageSteps extends BaseSteps {

	WeatherHomePage login;
	SearchResultPage home;
	public static int temperature_ui ;
	RequestSpecification request;
	Response response;
	HashMap<String, Integer> hmap_UI = new HashMap<String, Integer>();
	HashMap<String, Integer> hmap_API = new HashMap<String, Integer>();
	LocationObjects obj;
	
	
	
	@Given("^launch browser \"([^\"]*)\"$")
	public void launch_browser(String browser) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    openBrowser(browser);
	    ExtentTestManager.logInfo("Browser Opened :"+browser);
	    log.info("Browser Opened :"+browser);
	}

	@When("^user navigates to the URL \"([^\"]*)\"$")
	public void user_navigates_to_the_URL(String url) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    login = new WeatherHomePage().open(url);
	    log.info("Weather HomePage openend with url" +url);
	    
	}
	
	@Then("^user should be on WeatherHomePage$")
	public void user_should_be_on_WeatherHomePage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	    log.info("Check user should be on WeatherHomePage");
		Assert.assertEquals(login.isSearchBtnPresent(), true);
	    
	}


	@Then("^user should be on searhResultsPage$")
	public void user_should_be_on_searhResultsPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		Assert.assertEquals(home.checkSearchResultPages(), true);
		temperature_ui = Integer.parseInt(home.getTemperatureValue().replace("°", ""));
		System.out.println(temperature_ui);
	}
	

@Then("^user search with cityname$")
public void user_search_with_cityname(DataTable testData) throws Throwable {
    
	
    List<String> data = testData.asList(String.class);
	
	login.enterSearchCity(data.get(1));
	
	home = login.selectCityFromListbox(data.get(1));
	
}


@Given("^Get Temperature API payload with city_name$")
public void get_Temperature_API_payload_with_city_name(DataTable dataTable) throws Throwable {
   
	List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	
	
	request = given().log().all().contentType("application/json").baseUri("http://api.openweathermap.org")
	.queryParam("q", data.get(0).get("city_name"))
	.queryParam("appid", "ad76ed0a1789b65e7f18657a919d4593")
	.queryParam("units", data.get(0).get("units"));
	
}


@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
public void user_calls_with_http_request(String method_name, String httpmethod) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   response = request.log().all().when().get("/data/2.5/weather").then()
		   .log().all().extract().response();
		    
   
	
}

@Then("^Success response should get generated with success code (\\d+)$")
public void success_response_should_get_generated_with_success_code(int statuscode) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
 
	Assert.assertEquals(response.statusCode(), statuscode);
	
	
	
}


@Given("^Weather Information from UI with browser \"([^\"]*)\" and URL \"([^\"]*)\"$")
public void weather_Information_from_UI_with_browser_and_URL(String browser, String url) throws Throwable {
   
   obj = JsonReaderUtils.convertToObject();

for(int i =0 ; i< obj.getCities().size();i++)
{
	
	launch_browser(browser);
	user_navigates_to_the_URL(url);
	user_should_be_on_WeatherHomePage();
	login.enterSearchCity(obj.getCities().get(i));
	log.info("Enter City "+obj.getCities().get(i));
	home = login.selectCityFromListbox(obj.getCities().get(i));
	Assert.assertEquals(home.checkSearchResultPages(), true);
	temperature_ui = Integer.parseInt(home.getTemperatureValue().replace("°", ""));
	log.info("Temperature value for city"+obj.getCities().get(i)+ " is"+temperature_ui);
	hmap_UI.put(obj.getCities().get(i), temperature_ui);
	quit();
	
}

System.out.println(hmap_UI);
	
}


@Given("^Weather Information through API$")
public void weather_Information_through_API() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

	  
	obj = JsonReaderUtils.convertToObject();

	for(int i =0 ; i< obj.getCities().size();i++)
	{
		
		log.info("Verify API response for "+obj.getCities().get(i));
		 ExtentTestManager.logInfo("Verify API response for "+obj.getCities().get(i));
		response = given().log().all().contentType("application/json").baseUri("http://api.openweathermap.org")
				.queryParam("q", obj.getCities().get(i))
				.queryParam("appid", "ad76ed0a1789b65e7f18657a919d4593")
				.queryParam("units", "metric")
				.when().log().all().get("/data/2.5/weather").then()
				   .log().all().extract().response();
		
	    String temp_val = JsonReaderUtils.getJSONObjectResponse(response, "main", "temp");
	    
	    int temp_vl = (int) Math.round(Double.parseDouble(temp_val));

	    hmap_API.put(obj.getCities().get(i), temp_vl);

	   	    		
	}


	
}

@Then("^Compare UI and API weather Information$")
public void compare_UI_and_API_weather_Information() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	
	
	for(int i = 0; i<obj.getCities().size();i++)
	{
	
	int ui_val	= hmap_UI.get(obj.getCities().get(i));
	int api_val = hmap_API.get(obj.getCities().get(i));
	
	boolean flg = home.compareMapObjects(ui_val, api_val, obj.getVariance());
	
	if(flg)
	{
		log.info("API and UI value matches with variance for city "+obj.getCities().get(i));
		 ExtentTestManager.logInfo("API and UI value matches with variance for city "+obj.getCities().get(i));
	}
	
	

}





}	
	


}
