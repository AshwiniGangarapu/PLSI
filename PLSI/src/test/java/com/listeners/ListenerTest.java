package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class ListenerTest implements ITestListener {


	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub

	}


	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub

	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// logger.log(LogStatus.FAIL, "this test is Failed due to : " + result.getThrowable());
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
       // logger.log(LogStatus.SKIP, "this test is skipped"+ result.getThrowable());
	}


	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}


	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
