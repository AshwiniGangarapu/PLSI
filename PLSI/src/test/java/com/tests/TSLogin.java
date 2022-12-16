package com.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.pom.NewAppointmentPage;
import com.pom.DashBoardPage;
import com.pom.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.ExcelUtils;
import com.utils.SeleniumUIUtils; 

public class TSLogin extends BaseClass{

	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	
	LoginPage loginPage=new LoginPage();
	NewAppointmentPage allApts=new NewAppointmentPage();
	DashBoardPage dashboard=new DashBoardPage();
	//intializing a variable of type ExtentTest class.
	//ExtentTest is used to create the body of the report.
	ExtentTest logger;
	XSSFSheet sheet = null;			

	@Parameters({"browser","URL"})
	@BeforeClass
	public void init(String browser,String URL) throws IOException
	{ 
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get(URL);	
		driver.manage().window().maximize();
		sheet = readSheet("Login");
 	}


	@BeforeMethod
	public void Setup() {
		System.out.println("Before test");
		//data.readExcelDataToArray(sheet);

	}
	
	@Test(description="This will verify valid login")
	public void Login() {
		
		logger = report.startTest("Verifying Logging to the application");
		
		UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,1, 1));
		logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,1, 1));
		
		UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 1));
		logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 1));
		
		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Assert.assertTrue(UI.isDisplayed(dashboard.logOut()));
		logger.log(LogStatus.PASS, "reached Dashboard page as the logout element is displayed");
		
		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");
			
		
	}
	
	@Test(description="This will verify invalid login error message")
	public void InvalidLogin() {
		
		logger = report.startTest("Verifying invalid login error message");
		
		UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,1, 3));
		logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,1, 3));
		
		UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 3));
		logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 3));
		
		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Assert.assertTrue(UI.isDisplayed(loginPage.invalidCredentialsErrorMsg()));
		logger.log(LogStatus.PASS, "error msg is displayed");		
			
		Assert.assertEquals("Error: Invalid User Credentials", UI.getText(loginPage.invalidCredentialsErrorMsg()));
		logger.log(LogStatus.PASS, "error message is asserted for the text displayed");	
		
	}
	
	
	
	
	
	@AfterMethod
	public void signout(ITestResult result) throws InterruptedException {

		if(result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path "+ path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));

			System.out.println("Entered After method");


		}else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "This test skipped");
		}
		
		report.endTest(logger);
		
		
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	

}
