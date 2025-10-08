package com.lifeup.swaglab.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {
	static String REPORT_FOLDER_PATH;
	static String SCREENSHOT_FOLDER_PATH;
	static String SCREENSHOT_FILE_PATH;
	static String TC_NAME;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest writeExtentReport;

	public static void createReport() {
		String reportPath = REPORT_FOLDER_PATH + "/report.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
		extentSparkReporter.config().setDocumentTitle("Life Up");
		extentSparkReporter.config().setReportName("Swag Lab");

		extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		extent.setSystemInfo("Env", "UAT");
		extent.setSystemInfo("User", "Bhagwan");
		extent.setSystemInfo("URL", GlobalIdentifier.getProperty("url"));
	}

	public static void flush() {
		extent.flush();
	}

	public static void createTest(String TC_Name) {
		test = extent.createTest(TC_Name);
	}

	public static void info(String info) {
		test.info(info);
	}

	public static void createResult(String result) {
		try {
			getScreenshot();
			Media media = MediaEntityBuilder.createScreenCaptureFromPath(SCREENSHOT_FILE_PATH).build();

			if (result.trim().toLowerCase().equals("pass")) {
				test.log(Status.PASS, "Test Case Passed", media);
			} else if (result.trim().toLowerCase().equals("fail")) {
				test.log(Status.FAIL, "Test Case Failed", media);
			} else if (result.trim().toLowerCase().equals("skip")) {
				test.log(Status.SKIP, "Test Case Skip");
			}
		} catch (Exception e) {
			writeExtentReport.log(Status.WARNING, "Screenshot could not be attached");
		}
	}

	public static void getScreenshot() {
		getScreenshotPath();

		TakesScreenshot ts = (TakesScreenshot) GlobalIdentifier.getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		SCREENSHOT_FILE_PATH = SCREENSHOT_FOLDER_PATH + "/" + new Date().getHours() + "_" + new Date().getMinutes()
				+ "_" + new Date().getSeconds() + ".png";
		File desFile = new File(SCREENSHOT_FILE_PATH);

		try {
			FileUtils.copyFile(srcFile, desFile);
		} catch (Exception e) {
			System.out.println("screenshot not added to " + desFile);
		}
	}

	public static void getScreenshot(String screenshotName) {
		getScreenshotPath();

		TakesScreenshot ts = (TakesScreenshot) GlobalIdentifier.getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		SCREENSHOT_FILE_PATH = SCREENSHOT_FOLDER_PATH + "/" + screenshotName + ".png";
		File desFile = new File(SCREENSHOT_FILE_PATH);

		try {
			FileUtils.copyFile(srcFile, desFile);
		} catch (Exception e) {
			System.out.println("screenshot not added to " + desFile);
		}
	}

	public static void createReportPath() {
		String path = GlobalIdentifier.getProjectBasePath() + "/report";
		File mkDir = new File(path);

		if (!mkDir.exists()) {
			mkDir.mkdir();
		}
		path = path + "/Report" + "_" + new Date().getHours() + "_" + new Date().getMinutes() + "_"
				+ new Date().getSeconds();
		mkDir = new File(path);
		if (!mkDir.exists()) {
			mkDir.mkdir();
		}
		REPORT_FOLDER_PATH = mkDir.getAbsolutePath();

		System.out.println("Report path=" + REPORT_FOLDER_PATH);
	}

	public static void getScreenshotPath() {
		String path = REPORT_FOLDER_PATH + "/Screenshots";
		File mkDir = new File(path);
		if (!mkDir.exists()) {
			mkDir.mkdir();
		}
		if (!TC_NAME.isEmpty()) {
			path = path + "/" + TC_NAME;
			mkDir = new File(path);
		}
		SCREENSHOT_FOLDER_PATH = mkDir.getAbsolutePath();
		System.out.println("Screenshot file path=" + SCREENSHOT_FOLDER_PATH);
	}

}
