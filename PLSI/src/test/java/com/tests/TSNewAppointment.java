package com.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.pom.AppointmentDetails;
import com.pom.DashBoardPage;
import com.pom.FilterInDashboard;
import com.pom.InterpreterDashboard;
import com.pom.LoginPage;
import com.pom.NavigationPanel;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.SeleniumUIUtils;

public class TSNewAppointment extends BaseClass{
	

	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	
	LoginPage loginPage=new LoginPage();
	NewAppointmentPage newApt=new NewAppointmentPage();
	DashBoardPage dashboard=new DashBoardPage();
	FilterInDashboard filter=new FilterInDashboard();
	AppointmentDetails appDetails=new AppointmentDetails();
	NavigationPanel navPanel =new NavigationPanel();
	InterpreterDashboard interpreterDb = new InterpreterDashboard();
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
		
	@Test(description="This TC will perform valid login and creates a new appointment",enabled=false)
	public void NewAppointment() throws InterruptedException, IOException{

		logger = report.startTest("Verifying creating new appointment");
		
		UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
		logger.log(LogStatus.INFO, "entered email address as : ravi.thota@sstech.us");
		
		UI.sendKeys(loginPage.password(),"Welcome@1");
		logger.log(LogStatus.INFO, "entered password as : Welcome@1");
		
		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Thread.sleep(1000);
		
		UI.click(dashboard.newAppointment());
		logger.log(LogStatus.INFO, "clicked NEW APPOINTMENT button");
		
		//Thread.sleep(2000);
		UI.waitForElementVisibility(newApt.appointmentDate());
		
		UI.sendKeys(newApt.appointmentDate(),"30-12-2022");
		logger.log(LogStatus.INFO, "entered valid future appointment date");
		
		UI.sendKeys(newApt.appointmentStartTime(),"23:28");
		logger.log(LogStatus.INFO, "entered valid future appointment start time");
		
		UI.sendKeys(newApt.appointmentEndTime(),"23:56");
		logger.log(LogStatus.INFO, "entered valid future appointment end time");
		
		UI.sendKeys(newApt.client(),"CHOP");
		UI.sendkeyboardKeysEnter(newApt.client());
		logger.log(LogStatus.INFO, "entered valid CHOP");
		Thread.sleep(1000);
		
		UI.sendKeys(newApt.Facility(),"CHOP Main");
		UI.sendkeyboardKeysEnter(newApt.Facility());
		logger.log(LogStatus.INFO, "entered valid CHOP Main");
		
		Thread.sleep(500);
		
		UI.sendKeys(newApt.appointmentType(),"Fitness");
		UI.sendkeyboardKeysEnter(newApt.appointmentType());
		logger.log(LogStatus.INFO, "entered appointment type: Fitness");
		
		Thread.sleep(500);
		
		UI.sendKeys(newApt.building(),"Abramson Building");
		UI.sendkeyboardKeysEnter(newApt.building());
		logger.log(LogStatus.INFO, "entered building : Abramson Building");
		
		Thread.sleep(500);
		
		UI.sendKeys(newApt.department(),"CS");
		UI.sendkeyboardKeysEnter(newApt.department());
		logger.log(LogStatus.INFO, "entered department : CS");
				
		Thread.sleep(2000);
		
		UI.click(newApt.searchPatient());
		logger.log(LogStatus.INFO, "clicked Search Patient button");
		
		WebElement patientinfoTable = UI.getElement(newApt.patientInfoTable());
		
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
					logger.log(LogStatus.INFO, "iterating through first name list");
					System.out.println(column_First_Name.get(i).getText());
					
					if (first_name.equalsIgnoreCase("Harsha")) {
						
						System.out.println(i);
						column_Actions.get(i).click();
						logger.log(LogStatus.INFO, "selected the corresponding row and clicked actions");	
						break;
															
					}
													
				}
				
				UI.sendKeys(newApt.requestedLanguage(),"spanish");
				UI.sendkeyboardKeysEnter(newApt.requestedLanguage());
				logger.log(LogStatus.INFO, "entered requested lanuguage as spanish");	
				
				/*UI.sendKeys(newApt.requesterName(),"Mayuri");
				logger.log(LogStatus.INFO, "entered requester name as Mayuri");
				
				UI.sendKeys(newApt.requesterPhone(),"9948363539");
				logger.log(LogStatus.INFO, "entered requester phone as 9948363539");
				
				UI.sendKeys(newApt.requesterEmail(),"mayuri.chitgope@sstech.us");
				logger.log(LogStatus.INFO, "entered requester email address");*/
				
				UI.click(newApt.setAppointment());
				logger.log(LogStatus.INFO, "clicked set appointment");	
																			
				Assert.assertTrue(UI.isDisplayed(dashboard.logOut()));	
				logger.log(LogStatus.PASS, "Reached Dashboard");
				
				Assert.assertTrue(UI.isDisplayed(dashboard.appointmentCreatedSuccessMsg()));
				logger.log(LogStatus.PASS, "Success toast message displayed");		
										
				
					
	}

	
	@Test(enabled=false)	
	public void NewAppointmentRecordAvailabilityByFilter() throws InterruptedException, IOException{
		
        logger = report.startTest("Verifying the newly created appointment is in All appointments by using filter");
		
		UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
		logger.log(LogStatus.INFO, "entered email address as : ravi.thota@sstech.us");
		
		UI.sendKeys(loginPage.password(),"Welcome@1");
		logger.log(LogStatus.INFO, "entered password as : Welcome@1");
		
		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");
		
		Thread.sleep(5000);
		
		UI.click(dashboard.filter());
		logger.log(LogStatus.INFO, "clicked filter");
		
		Thread.sleep(1000);
		
		UI.sendKeys(filter.language(), "Spanish");
		UI.sendkeyboardKeysEnter(filter.language());
		logger.log(LogStatus.INFO, "entered lanuguage as spanish");	
		
		UI.sendKeys(filter.startDate(), "30-11-2022");
		UI.sendkeyboardKeysEnter(filter.startDate());
		logger.log(LogStatus.INFO, "entered start date: 30-11-2022");
		
		UI.sendKeys(filter.endDate(), "30-11-2022");
		UI.sendkeyboardKeysEnter(filter.endDate());
		logger.log(LogStatus.INFO, "entered end date: 30-11-2022");
		
		UI.click(filter.applyButton());
		logger.log(LogStatus.INFO, "Clicked Apply button");
		
		
	}
	
	@Test	
	public void RejectAppointment() throws InterruptedException, IOException{
		
		 logger = report.startTest("Verifying rejecting an appointment by interpreter");
			
			UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
			//UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,"EmailAddress", 2));
			logger.log(LogStatus.INFO, "entered email address as : ravi.thota@sstech.us");
			//logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,"EmailAddress", 2));

			UI.sendKeys(loginPage.password(),"Welcome@1");
			//UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 2));
			//logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 2));
			logger.log(LogStatus.INFO, "entered password as : Welcome@1");
			UI.click(loginPage.logIn());
			logger.log(LogStatus.INFO, "clicked Log-in button");
			
			Thread.sleep(10000);
			
			logger.log(LogStatus.INFO, "current page is all appointments dashboard");
							
			WebElement TogetRows = UI.getElement(dashboard.allAppointmentTableBody());
			List<WebElement>TotalRowsList = TogetRows.findElements(By.tagName("tr"));
			System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
			int rowSize=TotalRowsList.size();
			logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);
			
			List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
			logger.log(LogStatus.INFO, "selected the column Status");
			
			String view_Text = null;
			
			logger.log(LogStatus.INFO, "looping through all rows in Status column till we find new appointment");
			
               for(int i=0;i<=rowSize-1;i++) {
				
				

				String status=column_status.get(i).getText();
				System.out.println(column_status.get(i).getText());
				
				if (status.equalsIgnoreCase("new")){
					
					logger.log(LogStatus.INFO, "found a new appointment");
					
					List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());
					
					WebElement view_id=column_view.get(i);
					
					 view_Text=view_id.getText();
					
					System.out.println(view_Text);
					
					Thread.sleep(1000);
					
					view_id.click();
					
					
										
					logger.log(LogStatus.PASS, "able to clicked appointment id:"+view_Text);
					
					Thread.sleep(1000);
					String appId=driver.findElement(By.xpath("//input[@name='AppointmentId']")).getAttribute("value");
					Assert.assertEquals(view_Text,appId);
					
					break;
					
				}
				
				}
               
               logger.log(LogStatus.INFO, " navigated to the appointment details page" );
				
				UI.click(appDetails.tabInterpreterMatching());
				
				 logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab" );
				
				Thread.sleep(1000);
				
				UI.click(appDetails.buttonFindInterpreters());
				
				logger.log(LogStatus.INFO, " Clicked the button FIND INTERPRETERS" );
				
				WebElement interpreter_list_table = UI.getElement(appDetails.interpreterListTable());
				
				 //getting number of rows of that page table
				WebElement row_count = driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 MuiGrid-grid-lg-12 css-1484pol']//table/tbody"));
				List<WebElement>row_list = row_count.findElements(By.tagName("tr"));
				System.out.println("Total number of Rows in the table are : "+ TotalRowsList.size());
				int row_size=TotalRowsList.size();
				logger.log(LogStatus.INFO, "Found number of rows in the page: "+rowSize);	
				
				List<WebElement> column_Actions =driver.findElements(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 MuiGrid-grid-lg-12 css-1484pol']//table//tr/td[6]"));
				logger.log(LogStatus.INFO, "selected the column actions");
				List<WebElement> column_Interpreter_Name =driver.findElements(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 MuiGrid-grid-lg-12 css-1484pol']//table//tr/td[1]"));
				logger.log(LogStatus.INFO, "selected the column first name");
				
				for(int j=0;j<row_size-1;j++) {

					String first_name=column_Interpreter_Name.get(j).getText();
					logger.log(LogStatus.INFO, "iterating through first name list");
					System.out.println(column_Interpreter_Name.get(j).getText());
					
					if (first_name.equalsIgnoreCase("david romberger")) {
						
						logger.log(LogStatus.INFO, "selected Davi Romberger to make the offer");
						
						System.out.println(j);
						column_Actions.get(j).click();
						logger.log(LogStatus.INFO, "selected the corresponding row and clicked actions to make offer");	
						Thread.sleep(3000);
						
						UI.click(appDetails.close());
						logger.log(LogStatus.INFO, "Closed the popup");
						break;
															
					}
													
				}
			
			   
				UI.click(dashboard.logOut());
				logger.log(LogStatus.INFO, "logout as scheduler");
				UI.sendKeys(loginPage.emailAddress(),"david.romberger@sstech.us");
				logger.log(LogStatus.INFO, "entered email address as : david.romberger@sstech.us");
				UI.sendKeys(loginPage.password(),"Welcome@1");
				logger.log(LogStatus.INFO, "entered password as : Welcome@1");
				UI.click(loginPage.logIn());
				logger.log(LogStatus.INFO, "clicked Log-in button");
				logger.log(LogStatus.INFO, "logged in as interpreter David Romberger");
				
				Thread.sleep(2000);
				
				
				UI.click(navPanel.Interpreter());
				UI.click(navPanel.SubInterpreter());
				UI.click(interpreterDb.OfferedTab()); 
				logger.log(LogStatus.INFO, "Clicked Interpreter>Offered tab");
			
				
				 //getting number of rows of that page table
				WebElement row_count_offered = driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody"));
				List<WebElement>row_list_offered =row_count_offered.findElements(By.tagName("tr"));
				System.out.println("Total number of Rows in the table are : "+ row_list_offered.size());
				int row_size_offered=row_list_offered.size();
				logger.log(LogStatus.INFO, "Found number of rows in the page in offered tab: "+row_size_offered);	
				
				List<WebElement> column_View =driver.findElements(By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr/td[1]"));
				logger.log(LogStatus.INFO, "selected the column actions");
				
				
				for(int k=0;k<row_size_offered-1;k++) {

					String id=column_View.get(k).getText();
					logger.log(LogStatus.INFO, "iterating through view list");
					System.out.println(column_View.get(k).getText());
					
					if (id.equalsIgnoreCase(view_Text)) {
						
						System.out.println(k);
						column_View.get(k).click();
						logger.log(LogStatus.PASS, "able to click the id in OFFER tab");	
						Thread.sleep(3000);
						String appointment_offer_title=driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-16o4yi7']")).getText();
						System.out.println(appointment_offer_title);
						Assert.assertTrue(appointment_offer_title.contains(view_Text));
						logger.log(LogStatus.PASS, "checked the title to if it has the id "+view_Text);
						driver.findElement(By.xpath("//button[text()='Decline Appointment']")).click();
						logger.log(LogStatus.INFO, "clicked Decline Appointment");
						Thread.sleep(3000);
						break;
															
					}
				
				}
				
				
				UI.click(dashboard.logOut());
				logger.log(LogStatus.INFO, "logged out as interpreter");
				
				logger.log(LogStatus.INFO, "login as scheduler");
				UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
				//UI.sendKeys(loginPage.emailAddress(),data.getDataAsString(sheet,"EmailAddress", 2));
				logger.log(LogStatus.INFO, "entered email address as : ravi.thota@sstech.us");
				//logger.log(LogStatus.INFO, "entered email address as : "+ data.getDataAsString(sheet,"EmailAddress", 2));

				UI.sendKeys(loginPage.password(),"Welcome@1");
				//UI.sendKeys(loginPage.password(),data.getDataAsString(sheet,"Password", 2));
				//logger.log(LogStatus.INFO, "entered password as : "+ data.getDataAsString(sheet,"Password", 2));
				logger.log(LogStatus.INFO, "entered password as : Welcome@1");
				UI.click(loginPage.logIn());
				logger.log(LogStatus.INFO, "clicked Log-in button");
				
				
				UI.sendKeys(dashboard.search(),view_Text);
				logger.log(LogStatus.INFO, "entered id in search box");
				
				List<WebElement> column_status_offer_rejected = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
				logger.log(LogStatus.INFO, "selected the column Status");
				
				System.out.println(column_status_offer_rejected.get(0).getText());			
				
				
				String status_offer_rejected=column_status_offer_rejected.get(0).getText();
				//String status_offer_rejected=driver.findElement(By.xpath("//span[@class='MuiBadge-badge MuiBadge-standard MuiBadge-anchorOriginTopRight MuiBadge-anchorOriginTopRightRectangular MuiBadge-overlapRectangular BaseBadge-badge css-1o4n2ye']"));
				Assert.assertEquals("OFFER REJECTED",status_offer_rejected);
				logger.log(LogStatus.PASS, "verified the status of "+view_Text+" is offered rejected in All appointments list");
				
				List<WebElement> column_view_offer_rejected = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				
				WebElement view_id_offer_rejected=column_view_offer_rejected.get(0);
				
				 view_Text=view_id_offer_rejected.getText();
				
				System.out.println(view_Text);
				
				view_id_offer_rejected.click();
				logger.log(LogStatus.INFO, "clicked the id");
				
				 
	               logger.log(LogStatus.INFO, " navigated to the appointment details page" );
					
					UI.click(appDetails.tabInterpreterMatching());
					
					 logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab" );
				
				
				
				List<WebElement> column_Actions_offer_rejected =driver.findElements(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 MuiGrid-grid-lg-12 css-1484pol']//table//tr/td[6]"));
				//logger.log(LogStatus.INFO, "selected the column actions");
				List<WebElement> column_Interpreter_Name_offer_rejected =driver.findElements(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 MuiGrid-grid-lg-12 css-1484pol']//table//tr/td[1]"));
				//logger.log(LogStatus.INFO, "selected the column first name");
				
				for(int l=0;l<row_size-1;l++) {

					String first_name_offer_rejected=column_Interpreter_Name_offer_rejected.get(l).getText();
					logger.log(LogStatus.INFO, "iterating through first name list");
					System.out.println(column_Interpreter_Name_offer_rejected.get(l).getText());
					
					if (first_name_offer_rejected.equalsIgnoreCase("david romberger")) {
						
						 logger.log(LogStatus.INFO, "Selected the previous interpret David Romberger" );
						
						System.out.println(l);
						
						System.out.println(column_Actions_offer_rejected.get(l).getText());
						
						String offer_status_off_rejected = column_Actions_offer_rejected.get(l).getText();
						
						Assert.assertEquals(" OFFER REJECTED",offer_status_off_rejected);	
						 logger.log(LogStatus.PASS, "checked the status is offer rejected so test case is passed" );
						Thread.sleep(3000);
						
						UI.click(appDetails.close());
						 logger.log(LogStatus.INFO, "Closed the appointment detail page" );
						break;
															
					}
													
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


