package com.test.Steps;

import com.aventstack.extentreports.Status;
import com.testUI.ExtentReports.ExtentManager;
import com.testUI.ExtentReports.ExtentTestManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseSteps {
	
	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;

	@Before
	public synchronized void  before(Scenario scenario) {

		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = x + 1;
		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
		log.info("Scenario No : " + x + " : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
		log.info("Scenario started : - " + scenario.getName());
		setUpFramework();
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			log.error("Scenario Failed" +scenario.getName());
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
			log.info("Scenario Passed " +scenario.getName());
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	

}
