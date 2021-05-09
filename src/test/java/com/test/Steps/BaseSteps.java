package com.test.Steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.testUI.ExtentReports.ExtentTestManager;
import com.testUI.Utilities.DriverFactory;
import com.testUI.Utilities.DriverManager;



public class BaseSteps {
	
	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseSteps.class);
	public boolean grid=true;
	
	
	


	public void setUpFramework() {

		configureLogging();
		
		DriverFactory.setConfigPropertyFilePath(System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		DriverFactory.setJsonFilePath(System.getProperty("user.dir") + "//src//test//resources//LocationJSONFile//location.json");
	
		
		/*DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
*/
        
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

	
	
	public void logInfo(String message) {
		
		ExtentTestManager.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
				+ "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void destroyFramework() {

	}

	public void openBrowser(String browser) {
		
		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			log.info("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver",
					DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} 
		else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			log.info("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver",
					DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}

		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}

}
