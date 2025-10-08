package com.lifeup.swaglab.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.lifeup.swaglab.utils.GlobalIdentifier;
import com.lifeup.swaglab.utils.Report;
import com.lifeup.swaglab.utils.WebUtils;

public class BaseClassTest {

	public WebDriver driver;

	@BeforeSuite
	public void setUp() {
		try {
			Report.createReportPath();
			Report.createReport();
		} catch (Exception e) {
			System.out.println("Extent Report Not Created");
			throw new Error("Extent Report Not Created");
		}
	}

	@BeforeMethod
	public void init() {
		String browser = GlobalIdentifier.getProperty("browser");
		if (browser.trim().toLowerCase().equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			// Disable password manager
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
			options.setExperimentalOption("prefs", prefs);

			// Optional: Start in incognito to avoid prompts
			options.addArguments("--incognito");

			driver = new ChromeDriver(options);
		} else if (browser.trim().toLowerCase().equals("edge")) {
			driver = new EdgeDriver();
		}

		GlobalIdentifier.setMyDriver(driver);

		driver.get(GlobalIdentifier.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		/**
		 * verify application launch or not
		 */
		String actualApplicationTitle = driver.getTitle();
		Assert.assertEquals(actualApplicationTitle, GlobalIdentifier.getProperty("applicationTitle"));
		System.out.println("application launch successfully");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
