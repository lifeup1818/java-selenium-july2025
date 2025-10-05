package com.lifeup.swaglab.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {

	@Override
    public void onTestStart(ITestResult result) {
		System.out.println("Test Case Start:"+result.getName());		
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	System.out.println("Test Case Pass:"+result.getName());	
    	PageManager.report().getScreenshot( result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("Test Case Fail:"+result.getName());
    	PageManager.report().getScreenshot( result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	System.out.println("Test Case Skip:"+result.getName());	
    }

}
