package com.lifeup.swaglab.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {
	String projectPath=System.getProperty("user.dir");
	String SCREENSHOT_FOLDER_PATH;
	String TC_PATH;
	String Report_Path;
	
	
	public static void createReport() {	
		String reportPath=GlobalIdentifier.getProjectBasePath()+"/report/report.html";		
		ExtentSparkReporter extentSparkReporter= new ExtentSparkReporter(reportPath);
		extentSparkReporter.config().setDocumentTitle("Life Up");
		extentSparkReporter.config().setReportName("Swag Lab");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		extent.setSystemInfo("Env", "UAT");
		extent.setSystemInfo("Uer", "Bhagwan");
		extent.setSystemInfo("URL", GlobalIdentifier.getProperty("url"));	
	}
	
	public static void createReportPath() {
		
	}
	
		
	public void getScreenshot(String TC_Name) {
		TakesScreenshot ts= (TakesScreenshot) GlobalIdentifier.getDriver();	
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		
		createReportPath(TC_Name);	
		String filePath=TC_PATH+"/"+new Date().getHours()+"_"+new Date().getMinutes()+"_"+ new Date().getSeconds()+".png";
		File desFile= new File(filePath);
		
		try {
			FileUtils.copyFile(srcFile, desFile);
		} catch (Exception e) {
			System.out.println("screenshot not added to "+desFile);
		}	
	}
	
	public void getScreenshot(String screenshotName, String TC_Name) {
		TakesScreenshot ts= (TakesScreenshot) GlobalIdentifier.getDriver();	
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		
		createReportPath(TC_Name);	
		
		String filePath=Report_Path+"/"+screenshotName+".png";
		File desFile= new File(filePath);
		
		try {
			FileUtils.copyFile(srcFile, desFile);
		} catch (Exception e) {
			System.out.println("screenshot not added to "+desFile);
		}	
	}
	
	public void createReportPath(String testCaseName) {
		String path=GlobalIdentifier.getProjectBasePath()+"/report";		
		File mkDir= new File(path);
		
		if (!mkDir.exists()) {
			mkDir.mkdir();
		}
		
		Report_Path=path;
		TC_PATH=path;
		
		if (testCaseName.isEmpty()) {
			TC_PATH=path;
		} else {
			path=path+"/"+testCaseName;
			mkDir= new File(path);
			if (!mkDir.exists()) {
				mkDir.mkdir();
			}
			TC_PATH=path;
		}
	}
	
	

}
