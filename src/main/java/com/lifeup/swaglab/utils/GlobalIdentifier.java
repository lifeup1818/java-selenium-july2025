package com.lifeup.swaglab.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class GlobalIdentifier {
	private static String PROJECT_BASE_PATH = System.getProperty("user.dir");
	private static WebDriver myDriver;

	public static void setMyDriver(WebDriver driver) {
		myDriver = driver;
	}

	public static WebDriver getDriver() {
		return myDriver;
	}

	public static String getProjectBasePath() {
		return PROJECT_BASE_PATH;
	}

	public static String getProperty(String key) {
		String filePath = PROJECT_BASE_PATH + "\\src\\main\\resources\\config.properties";
		File file = new File(filePath);
		Properties properties = new Properties();

		try {
			FileInputStream io = new FileInputStream(file);
			properties.load(io);
		} catch (Exception e) {
			throw new Error("File not found:" + filePath);
		}
		return properties.getProperty(key);
	}

}
