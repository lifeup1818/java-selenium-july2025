package com.lifeup.swaglab.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {
	private final Logger log = LogManager.getLogger(WebUtils.class);

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getName();
		System.out.println("Test Case Start:" + testCaseName);
		Report.createTest(testCaseName);
		Report.TC_NAME = testCaseName;

		log.info("********************************");
		log.info("********************************");
		log.info("****Test Case Start:" + testCaseName + "********");
		log.info("********************************");
		log.info("********************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Case Pass:" + result.getName());
		Report.getScreenshot();
		Report.createResult("pass");
		Report.flush();
		log.info("Test Case Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Fail:" + result.getName());
		Report.getScreenshot();
		Report.createResult("fail");
		Report.flush();
		log.info("Test Case Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case Skip:" + result.getName());
		Report.createResult("skip");
		Report.flush();
		log.info("Test Case Skipped");
	}

}
