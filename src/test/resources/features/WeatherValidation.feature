Feature: Weather Validation 

  Scenario: Navigate to Weather website for login
    Given launch browser "chrome"
    When user navigates to the URL "https://weather.com/"
    Then user should be on WeatherHomePage
		And  user search with cityname
		|cityname|
		|Delhi|
		Then user should be on searhResultsPage
		
	
	Scenario: Fetch the Current temerature through getAPI
    Given Get Temperature API payload with city_name
    |city_name|units|
    |Delhi|metric|
    When user calls "GETAPI" with "GET" http request
    Then Success response should get generated with success code 200
   
	
	Scenario: Compare Results of Weather UI with API
		Given Weather Information from UI with browser "chrome" and URL "https://weather.com/"
		And Weather Information through API
		Then Compare UI and API weather Information
		
		
