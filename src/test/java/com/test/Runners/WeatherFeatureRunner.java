package com.test.Runners;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features="src/test/resources/features/WeatherValidation.feature",
		glue="com.test.Steps",
		dryRun=false
		)



public class WeatherFeatureRunner {
	
	
	@Test
	public void runCukes() {
		
		new TestNGCucumberRunner(getClass()).runCukes();
		
	}

}
