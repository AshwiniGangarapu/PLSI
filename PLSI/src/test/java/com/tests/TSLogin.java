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
import com.pom.AllAppointmentsPage;
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
	AllAppointmentsPage allApts=new AllAppointmentsPage();
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
	
	@Test(description="This TC will perform valid login and creates a new appointment")
	public void NewAppointment() throws InterruptedException, IOException{

		logger = report.startTest("Verifying creating new appointment");
		
		UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
		//UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,"EmailAddress", 2));
		logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,"EmailAddress", 2));

		UI.sendKeys(loginPage.password(),"Welcome@1");
		//UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 2));
		logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 2));

		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Thread.sleep(1000);
		
		UI.click(dashboard.newAppointment());
		logger.log(LogStatus.INFO, "clicked NEW APPOINTMENT button");
		
		Thread.sleep(1000);
		
		UI.sendKeys(allApts.appointmentDate(),"20-11-2022");
		logger.log(LogStatus.INFO, "entered valid future appointment date");
		
		UI.sendKeys(allApts.appointmentStartTime(),"23:28");
		logger.log(LogStatus.INFO, "entered valid future appointment start time");
		
		UI.sendKeys(allApts.appointmentEndTime(),"23:56");
		logger.log(LogStatus.INFO, "entered valid future appointment end time");
		
		UI.sendKeys(allApts.client(),"CHOP");
		UI.sendkeyboardKeysEnter(allApts.client());
		logger.log(LogStatus.INFO, "entered valid CHOP");
		
		UI.sendKeys(allApts.Facility(),"CHOP Main");
		UI.sendkeyboardKeysEnter(allApts.Facility());
		logger.log(LogStatus.INFO, "entered valid CHOP Main");
		
		Thread.sleep(1000);
		
		UI.click(allApts.searchPatient());
		logger.log(LogStatus.INFO, "clicked Search Patient button");
		
		WebElement patientinfoTable = UI.getElement(allApts.patientInfoTable());
		
		        //getting number of rows of that page table
				WebElement TogetRows = driver.findElement(By.xpath("//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table/tbody"));
				List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
				System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
				int rowSize=TotalRowsList.size();
				logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);
				
				//getting number of columns in each row				
				WebElement ToGetColumns = driver.findElement(By.xpath("//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table/tbody/tr"));
				List<WebElement> TotalColsList = ToGetColumns.findElements(By.tagName("td"));
				System.out.println("Total Number of Columns in the table are: "+TotalColsList.size());
				int columnSize=TotalColsList.size();
				logger.log(LogStatus.INFO, "Found number of columns in each row: "+columnSize);
				
				List<WebElement> column_Actions =driver.findElements(By.xpath("//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table//tr/td[5]"));
				logger.log(LogStatus.INFO, "selected the column actions");
				List<WebElement> column_First_Name =driver.findElements(By.xpath("//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table//tr/td[2]"));
				logger.log(LogStatus.INFO, "selected the column first name");
				
				for(int i=0;i<rowSize-1;i++) {

					String first_name=column_First_Name.get(i).getText();
					System.out.println(column_First_Name.get(i).getText());
					
					if (first_name.equalsIgnoreCase("Harsha")) {
						
						System.out.println(i);
						column_Actions.get(i).click();
						logger.log(LogStatus.INFO, "selected the column first name");	
						break;
															
					}
													
				}
				
				UI.sendKeys(allApts.requestedLanguage(),"spanish");
				UI.sendkeyboardKeysEnter(allApts.requestedLanguage());
				logger.log(LogStatus.INFO, "entered requested lanuguage as spanish");	
				
				UI.sendKeys(allApts.requesterName(),"Mayuri");
				logger.log(LogStatus.INFO, "entered requester name as Mayuri");
				
				UI.sendKeys(allApts.requesterPhone(),"9948363539");
				logger.log(LogStatus.INFO, "entered requester phone as 9948363539");
				
				UI.sendKeys(allApts.requesterEmail(),"mayuri.chitgope@sstech.us");
				logger.log(LogStatus.INFO, "entered requester email address");
				
				UI.click(allApts.setAppointment());
				logger.log(LogStatus.INFO, "clicked set appointment");	
											
				Assert.assertTrue(UI.isDisplayed(dashboard.logOut()));
				
				UI.click(dashboard.logOut());
				logger.log(LogStatus.INFO, "clicked Log-out button");
				
				Thread.sleep(1000);
				
				
					
	}
	
	@Test(description="This TC will perform valid login and verified that all appointments tab page is showing only todays appointments")
	public void AllAppointmentsDateVerification() throws InterruptedException, IOException{
		
        logger = report.startTest("Verifying all appointments tab page is showing only todays appointments");
		
		UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
		//UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,"EmailAddress", 2));
		logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,"EmailAddress", 2));

		UI.sendKeys(loginPage.password(),"Welcome@1");
		//UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 2));
		logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 2));

		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Thread.sleep(10000);
						
		WebElement TogetRows = UI.getElement(dashboard.allAppointmentTableBody());
		List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
		int rowSize=TotalRowsList.size();
		logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);
		
		List<WebElement> column_Date = UI.getElements(dashboard.allAppointmentTableBodyRowsDatecolumn());
		logger.log(LogStatus.INFO, "selected the column Date");
		
			
			Date dNow = new Date( );
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			ZoneId usChicago = ZoneId.of("America/Chicago");
			System.out.println(usChicago);
			logger.log(LogStatus.INFO, "found the zone which will be used to convert time into CST "+usChicago);

			TimeZone tzInChicago = TimeZone.getTimeZone(usChicago);

			sdf.setTimeZone(tzInChicago);

			System.out.println("Current CST Date: " + sdf.format(dNow));
			logger.log(LogStatus.INFO, "current CST time is "+sdf.format(dNow));

			String currentDate=sdf.format(dNow);
						
			for(int i=0;i<=rowSize-1;i++) {

				String date=column_Date.get(i).getText();
				System.out.println(column_Date.get(i).getText());
				
				Assert.assertTrue(currentDate.equalsIgnoreCase(date));
				System.out.println("date matches with current date");
			
			}     
			
											
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
		
		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");
		
		Thread.sleep(1000);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	

}
