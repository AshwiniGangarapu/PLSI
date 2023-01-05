package com.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.SeleniumUIUtils;
import com.utils.Sravani_SeleniumUIUtils;

public class Sravani_Interpreter_Offer_Recind extends BaseClass {
	Sravani_Interpreter_Details login = new Sravani_Interpreter_Details();
	ExtentTest logger;
	WebDriver driver = null;
	Sravani_SeleniumUIUtils UI = null;
	
	
	@Parameters("browser")
	@BeforeClass
	public void init(String browser)
	{ 
		driver = openBrowser(browser);
		UI = new Sravani_SeleniumUIUtils(driver);
		driver.get("http://qa.ims.client.sstech.us/appointments/appointmentsList");
		driver.manage().window().maximize();
	}
	
	
	@BeforeMethod
	public void preSetup() {
		System.out.println("Before test");
		logger = report.startTest("TC_01");
	}
	
	@Test( description="Scheduler Login"  )
	public void loginToApplication() throws Throwable
	{
		
		// login.userName(driver).sendKeys("test");
		UI.sendKeys(login.userName(driver), "ravi.thota@sstech.us");
		logger.log(LogStatus.INFO, "entered username as : "+ "ravi.thota@sstech.us");
		UI.sendKeys(login.password(driver), "Welcome@1");
		logger.log(LogStatus.INFO, "entered password as : "+ "Welcome@1");
		UI.click(login.loginButton(driver));
		logger.log(LogStatus.INFO, "clicked on Scheduler Login");
		
		Thread.sleep(2000);
		UI.click(login.urgentTab(driver));
		logger.log(LogStatus.INFO, "Click on Urgent Tab");
		
		Thread.sleep(2000);
		UI.click(login.appointment(driver));
		logger.log(LogStatus.INFO, "Click on Appointment ID");
		
		Thread.sleep(2000);
		UI.click(login.Interpreter(driver));
		logger.log(LogStatus.INFO, "Click on InterpreterMatching");
		
		Thread.sleep(2000);
		UI.click(login.findInterpreter(driver));
		logger.log(LogStatus.INFO, "Click on FindInterpreterMatching");
		
		Thread.sleep(2000);
		UI.click(login.makeAnOffer(driver));
		logger.log(LogStatus.INFO, "Click on Make An Offer");
		
		Thread.sleep(2000);
		UI.click(login.RescindOffer(driver));
		logger.log(LogStatus.INFO, "Click on Rescind Offer");
		
	}
	
	
}
	
	

