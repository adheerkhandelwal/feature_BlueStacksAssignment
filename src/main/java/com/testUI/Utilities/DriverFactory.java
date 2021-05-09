package com.testUI.Utilities;

public class DriverFactory {
	
	private static String chromeDriverExePath;
	private static String geckoDriverExePath;
	private static String ieDriverExePath;
	private static String configPropertyFilePath;
	private static String jsonFilePath;
	
	
	public static String getJsonFilePath() {
		return jsonFilePath;
	}
	public static void setJsonFilePath(String jsonFilePath) {
		DriverFactory.jsonFilePath = jsonFilePath;
	}
	
	public static String getConfigPropertyFilePath() {
		return configPropertyFilePath;
	}
	public static void setConfigPropertyFilePath(String configPropertyFilePath) {
		DriverFactory.configPropertyFilePath = configPropertyFilePath;
	}
	
	public static String getChromeDriverExePath() {
		return chromeDriverExePath;
	}
	public static void setChromeDriverExePath(String chromeDriverExePath) {
		DriverFactory.chromeDriverExePath = chromeDriverExePath;
	}
	public static String getGeckoDriverExePath() {
		return geckoDriverExePath;
	}
	public static void setGeckoDriverExePath(String geckoDriverExePath) {
		DriverFactory.geckoDriverExePath = geckoDriverExePath;
	}
	public static String getIeDriverExePath() {
		return ieDriverExePath;
	}
	public static void setIeDriverExePath(String ieDriverExePath) {
		DriverFactory.ieDriverExePath = ieDriverExePath;
	}

	
	

}
