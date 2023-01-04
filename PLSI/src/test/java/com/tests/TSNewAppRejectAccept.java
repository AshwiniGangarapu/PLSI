package com.tests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.pom.AppointmentDetails;
import com.pom.DashBoardPage;
import com.pom.FilterInDashboard;
import com.pom.InterpreterDashboard;
import com.pom.LoginPage;
import com.pom.NavigationPanel;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.CommonUtils;
import com.utils.SeleniumUIUtils;

public class TSNewAppRejectAccept extends BaseClass {

	/* creating an appointment for today and verifying the test cases */

	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	CommonUtils CU = null;

	LoginPage loginPage = new LoginPage();
	NewAppointmentPage newApt = new NewAppointmentPage();
	DashBoardPage dashboard = new DashBoardPage();
	FilterInDashboard filter = new FilterInDashboard();
	AppointmentDetails appDetails = new AppointmentDetails();
	NavigationPanel navPanel = new NavigationPanel();
	InterpreterDashboard interpreterDb = new InterpreterDashboard();

	// intializing a variable of type ExtentTest class.
	// ExtentTest is used to create the body of the report.
	ExtentTest logger;
	XSSFSheet sheet = null;
	XSSFSheet sheet_Reject = null;

	@Parameters({ "browser", "URL" })
	// @BeforeClass
	@BeforeMethod
	public void init(String browser, String URL) throws IOException {
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		CU = new CommonUtils(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		sheet = readSheet("New appointment");
		sheet_Reject = readSheet("Reject-Accept app");

	}

	/*
	 * @BeforeMethod public void Setup() { System.out.println("Before test"); //
	 * data.readExcelDataToArray(sheet);
	 * 
	 * }
	 */

	@Test(description = "This TC will perform valid login and creates a new appointment", enabled = false)
	public void NewAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying creating new appointment");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		UI.sendKeys(loginPage.emailAddress(), data.getDataAsString(sheet, "Email Address", 1));
		// UI.sendKeys(loginPage.emailAddress(),"ravi.thota@sstech.us");
		logger.log(LogStatus.INFO, "entered email address as : ravi.thota@sstech.us");

		UI.sendKeys(loginPage.password(), data.getDataAsString(sheet, "Password", 1));
		// UI.sendKeys(loginPage.password(),"Welcome@1");
		logger.log(LogStatus.INFO, "entered password as : Welcome@1");

		// to scroll down
		js.executeScript("window.scrollBy(0,350)", "");

		UI.click(loginPage.logIn());
		logger.log(LogStatus.INFO, "clicked Log-in button");

		// Thread.sleep(1000);

		UI.waitForElementVisibility(dashboard.newAppointment());

		UI.click(dashboard.newAppointment());
		logger.log(LogStatus.INFO, "clicked NEW APPOINTMENT button");

		// Thread.sleep(2000);
		UI.waitForElementVisibility(newApt.appointmentDate());

		UI.sendKeys(newApt.appointmentDate(), data.getDataAsString(sheet, "Appointment Date", 1));
		// UI.sendKeys(newApt.appointmentDate(),"30-12-2022");
		logger.log(LogStatus.INFO, "entered valid future appointment date");

		UI.sendKeys(newApt.appointmentStartTime(), data.getDataAsString(sheet, "App Start time", 1));
		// UI.sendKeys(newApt.appointmentStartTime(),"23:28");
		logger.log(LogStatus.INFO, "entered valid future appointment start time");

		UI.sendKeys(newApt.appointmentEndTime(), data.getDataAsString(sheet, "App End Time", 1));
		// UI.sendKeys(newApt.appointmentEndTime(),"23:56");
		logger.log(LogStatus.INFO, "entered valid future appointment end time");

		// input[contains(@id,"react-select")]

		List<WebElement> dropdown = driver.findElements(By.xpath("//input[contains(@id,'react-select')]"));

		for (int z = 1; z <= dropdown.size(); z++) {

			dropdown.get(1).sendKeys(data.getDataAsString(sheet, "Client", 1));
			dropdown.get(1).sendKeys(Keys.ENTER);

			dropdown.get(2).sendKeys(data.getDataAsString(sheet, "Facility", 1));
			dropdown.get(2).sendKeys(Keys.ENTER);

		}
		System.out.println(dropdown.size());

		UI.sendKeys(newApt.client(), data.getDataAsString(sheet, "Client", 1));
		// UI.sendKeys(newApt.client(),"CHOP");
		UI.sendkeyboardKeysEnter(newApt.client());
		logger.log(LogStatus.INFO, "entered valid CHOP");
		Thread.sleep(1000);

		UI.sendKeys(newApt.Facility(), data.getDataAsString(sheet, "Facility", 1));
		// UI.sendKeys(newApt.Facility(),"CHOP Main");
		UI.sendkeyboardKeysEnter(newApt.Facility());
		logger.log(LogStatus.INFO, "entered valid CHOP Main");

		Thread.sleep(500);

		UI.sendKeys(newApt.appointmentType(), data.getDataAsString(sheet, "App Type", 1));
		// UI.sendKeys(newApt.appointmentType(),"Fitness");
		UI.sendkeyboardKeysEnter(newApt.appointmentType());
		logger.log(LogStatus.INFO, "entered appointment type: Fitness");

		Thread.sleep(500);

		UI.sendKeys(newApt.building(), data.getDataAsString(sheet, "Building", 1));
		// UI.sendKeys(newApt.building(),"Abramson Building");
		UI.sendkeyboardKeysEnter(newApt.building());
		logger.log(LogStatus.INFO, "entered building : Abramson Building");

		Thread.sleep(500);

		UI.sendKeys(newApt.department(), data.getDataAsString(sheet, "Department", 1));
		// UI.sendKeys(newApt.department(),"CS");
		UI.sendkeyboardKeysEnter(newApt.department());
		logger.log(LogStatus.INFO, "entered department : CS");

		Thread.sleep(2000);

		UI.click(newApt.searchPatient());
		logger.log(LogStatus.INFO, "clicked Search Patient button");

		WebElement patientinfoTable = UI.getElement(newApt.patientInfoTable());

		// getting number of rows of that page table
		WebElement TogetRows = driver.findElement(By.xpath(
				"//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table/tbody"));
		List<WebElement> TotalRowsList = TogetRows.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : " + TotalRowsList.size());
		int rowSize = TotalRowsList.size();
		logger.log(LogStatus.INFO, "Found number of rows in the page: " + rowSize);

		// getting number of columns in each row
		WebElement ToGetColumns = driver.findElement(By.xpath(
				"//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table/tbody/tr"));
		List<WebElement> TotalColsList = ToGetColumns.findElements(By.tagName("td"));
		System.out.println("Total Number of Columns in the table are: " + TotalColsList.size());
		int columnSize = TotalColsList.size();
		logger.log(LogStatus.INFO, "Found number of columns in each row: " + columnSize);

		List<WebElement> column_Actions = driver.findElements(By.xpath(
				"//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table//tr/td[5]"));
		logger.log(LogStatus.INFO, "selected the column actions");
		List<WebElement> column_First_Name = driver.findElements(By.xpath(
				"//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table//tr/td[2]"));
		logger.log(LogStatus.INFO, "selected the column first name");

		for (int i = 0; i < rowSize - 1; i++) {

			String first_name = column_First_Name.get(i).getText();
			logger.log(LogStatus.INFO, "iterating through first name list");
			System.out.println(column_First_Name.get(i).getText());

			if (first_name.equalsIgnoreCase("Harsha")) {

				System.out.println(i);
				column_Actions.get(i).click();
				logger.log(LogStatus.INFO, "selected the corresponding row and clicked actions");
				break;

			}

		}

		UI.sendKeys(newApt.requestedLanguage(), data.getDataAsString(sheet, "Requested Language", 1));
		// UI.sendKeys(newApt.requestedLanguage(),"spanish");
		UI.sendkeyboardKeysEnter(newApt.requestedLanguage());
		logger.log(LogStatus.INFO, "entered requested lanuguage as spanish");

		js.executeScript("window.scrollBy(0,550)", "");

		UI.click(newApt.setAppointment());
		logger.log(LogStatus.INFO, "clicked set appointment");

		Assert.assertTrue(UI.isDisplayed(dashboard.logOut()));
		logger.log(LogStatus.PASS, "Reached Dashboard");

		Assert.assertTrue(UI.isDisplayed(dashboard.appointmentCreatedSuccessMsg()));
		logger.log(LogStatus.PASS, "Success toast message displayed");

	}

	@Test(priority = 1, enabled = false)
	public void RejectAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying rejecting an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "new");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find new appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("new")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a new appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				Thread.sleep(1000);

				view_id.click();
				logger.log(LogStatus.INFO, "able to click appointment id:" + view_Text);
				Thread.sleep(3000);
				UI.waitForElementVisibility(appDetails.appointmentId());
				logger.log(LogStatus.INFO, " navigated to the appointment details page");
				String appId = driver.findElement(appDetails.appointmentId()).getAttribute("value");
				Assert.assertEquals(view_Text, appId);
				logger.log(LogStatus.PASS,
						"clicked appointment id:" + view_Text + " and verified same appointment is being displayed");

				break;
			}

			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(appDetails.tabInterpreterMatching());

		logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab");

		Thread.sleep(1000);
		UI.waitForElementVisibility(appDetails.buttonFindInterpreters());

		UI.click(appDetails.buttonFindInterpreters());

		logger.log(LogStatus.INFO, " Clicked the button FIND INTERPRETERS");

		UI.waitForElementVisibility(appDetails.interpreterListTableBody());
		Thread.sleep(1000);

		int interpreterListRowsSize = CU.readNumberOfRowsInTable(appDetails.interpreterListTableBody());

		List<WebElement> column_Actions = driver.findElements(appDetails.interpreterListTableActionsCol());
		logger.log(LogStatus.INFO, "selected the column actions");

		List<WebElement> column_Interpreter_Name = driver.findElements(appDetails.interpreterListTableInterpreterCol());
		logger.log(LogStatus.INFO, "selected the column Interpreter name");

		logger.log(LogStatus.INFO, "iterating through first name list");

		for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

			String first_name = column_Interpreter_Name.get(j).getText();

			System.out.println(column_Interpreter_Name.get(j).getText());

			if (first_name.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Interpreter Name", 1))) {

				logger.log(LogStatus.INFO,
						"selected " + data.getDataAsString(sheet_Reject, "Interpreter Name", 1) + " to make the offer");

				column_Actions.get(j).click();
				logger.log(LogStatus.INFO, "selected the corresponding row and clicked actions to make offer");
				Thread.sleep(3000);

				UI.click(appDetails.close());
				logger.log(LogStatus.INFO, "Closed the popup");

				break;

			}

		}

		UI.waitForElementVisibility(dashboard.logOut());

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logout as scheduler");

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);
		UI.click(interpreterDb.OfferedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Offered tab");
		Thread.sleep(1000);

		int count = Integer.parseInt(UI.getText(interpreterDb.OfferedTabCount()));
		System.out.println(count);

		Thread.sleep(3000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTable());

		int interpreterDashboardAppointmentListRowsSize = CU
				.readNumberOfRowsInTable(interpreterDb.InterpreterDashboardAppointmentTable());

		Assert.assertEquals(count, interpreterDashboardAppointmentListRowsSize);
		logger.log(LogStatus.PASS, "The count beside Offered tab is verified with number of records displayed.");

		List<WebElement> column_View = driver.findElements(interpreterDb.InterpreterDashboardAppointmentTableColView());
		logger.log(LogStatus.INFO, "selected the column View ie appointment id");

		for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

			String id = column_View.get(k).getText();
			logger.log(LogStatus.INFO, "iterating through view column list");
			System.out.println(column_View.get(k).getText());

			if (id.equalsIgnoreCase(view_Text)) {

				System.out.println(k);
				column_View.get(k).click();
				logger.log(LogStatus.PASS, "able to click the id " + view_Text + " in OFFER tab page");

				Thread.sleep(3000);
				UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentClickTitle());

				String appointment_offer_title = driver
						.findElement(interpreterDb.InterpreterDashboardAppointmentClickTitle()).getText();
				System.out.println(appointment_offer_title);
				Assert.assertTrue(appointment_offer_title.contains(view_Text));
				logger.log(LogStatus.PASS, "Verified the title has the view id selected i.e." + view_Text);

				UI.click(interpreterDb.DeclineButton());
				logger.log(LogStatus.PASS, "Clciked Decline Button");

				Thread.sleep(10000);
				break;

			}

		}

		UI.waitForElementVisibility(dashboard.logOut());

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logged out as interpreter");

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		Thread.sleep(2000);

		UI.sendKeys(dashboard.search(), view_Text);
		logger.log(LogStatus.INFO, "entered id in search box");

		Thread.sleep(2000);

		List<WebElement> column_status_offer_rejected = UI
				.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		System.out.println(column_status_offer_rejected.get(0).getText());

		String status_offer_rejected = column_status_offer_rejected.get(0).getText();

		Assert.assertEquals("OFFER REJECTED", status_offer_rejected);
		logger.log(LogStatus.PASS,
				"verified the status of " + view_Text + " is offered rejected in All appointments list");

		List<WebElement> column_view_offer_rejected = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

		WebElement view_id_offer_rejected = column_view_offer_rejected.get(0);

		view_Text = view_id_offer_rejected.getText();

		System.out.println(view_Text);

		view_id_offer_rejected.click();
		logger.log(LogStatus.INFO, "clicked the id");

		Thread.sleep(2000);
		logger.log(LogStatus.INFO, " navigated to the appointment details page");

		UI.click(appDetails.tabInterpreterMatching());

		logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab");

		Thread.sleep(2000);

		List<WebElement> column_Actions_offer_rejected = driver
				.findElements(appDetails.interpreterListTableActionsCol());
		logger.log(LogStatus.INFO, "selected the column actions");
		List<WebElement> column_Interpreter_Name_offer_rejected = driver
				.findElements(appDetails.interpreterListTableInterpreterCol());
		logger.log(LogStatus.INFO, "selected the column first name");
		logger.log(LogStatus.INFO, "iterating through first name list");

		for (int l = 0; l <= interpreterListRowsSize - 1; l++) {

			System.out.println(interpreterListRowsSize);
			String first_name_offer_rejected = column_Interpreter_Name_offer_rejected.get(l).getText();

			System.out.println(column_Interpreter_Name_offer_rejected.get(l).getText());
			System.out.println(first_name_offer_rejected);

			if (first_name_offer_rejected.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Interpreter Name", 1))) {

				logger.log(LogStatus.INFO,
						"Selected the previous interpret " + data.getDataAsString(sheet_Reject, "Interpreter Name", 1));
				System.out.println(column_Actions_offer_rejected.get(l).getText());

				String offer_status_off_rejected = column_Actions_offer_rejected.get(l).getText();

				Assert.assertEquals(" OFFER REJECTED", offer_status_off_rejected);
				logger.log(LogStatus.PASS, "checked the status is offer rejected so test case is passed");
				Thread.sleep(3000);

				UI.click(appDetails.close());
				logger.log(LogStatus.INFO, "Closed the appointment detail page");
				break;

			}

		}

	}

	@Test(priority = 2,enabled=false)
	public void AcceptAppointment() throws InterruptedException, IOException {
		logger = report.startTest("Verifying accepting an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "new");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find new appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("new")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a new appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				Thread.sleep(1000);

				view_id.click();
				logger.log(LogStatus.INFO, "able to click appointment id:" + view_Text);
				Thread.sleep(3000);
				UI.waitForElementVisibility(appDetails.appointmentId());
				logger.log(LogStatus.INFO, " navigated to the appointment details page");
				String appId = driver.findElement(appDetails.appointmentId()).getAttribute("value");
				Assert.assertEquals(view_Text, appId);
				logger.log(LogStatus.PASS,
						"clicked appointment id:" + view_Text + " and verified same appointment is being displayed");

				break;
			}

			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(appDetails.tabInterpreterMatching());

		logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab");

		Thread.sleep(1000);
		UI.waitForElementVisibility(appDetails.buttonFindInterpreters());

		UI.click(appDetails.buttonFindInterpreters());

		logger.log(LogStatus.INFO, " Clicked the button FIND INTERPRETERS");

		UI.waitForElementVisibility(appDetails.interpreterListTableBody());
		Thread.sleep(1000);

		int interpreterListRowsSize = CU.readNumberOfRowsInTable(appDetails.interpreterListTableBody());

		List<WebElement> column_Actions = driver.findElements(appDetails.interpreterListTableActionsCol());
		logger.log(LogStatus.INFO, "selected the column actions");

		List<WebElement> column_Interpreter_Name = driver.findElements(appDetails.interpreterListTableInterpreterCol());
		logger.log(LogStatus.INFO, "selected the column Interpreter name");

		logger.log(LogStatus.INFO, "iterating through first name list");

		for (int j = 0; j <= interpreterListRowsSize - 1; j++) {

			String first_name = column_Interpreter_Name.get(j).getText();

			System.out.println(column_Interpreter_Name.get(j).getText());

			if (first_name.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Interpreter Name", 1))) {

				logger.log(LogStatus.INFO,
						"selected " + data.getDataAsString(sheet_Reject, "Interpreter Name", 1) + " to make the offer");

				column_Actions.get(j).click();
				logger.log(LogStatus.INFO, "selected the corresponding row and clicked actions to make offer");
				Thread.sleep(3000);

				UI.click(appDetails.close());
				logger.log(LogStatus.INFO, "Closed the popup");

				break;

			}

		}

		UI.waitForElementVisibility(dashboard.logOut());

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logout as scheduler");

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);
		UI.click(interpreterDb.OfferedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Offered tab");
		Thread.sleep(1000);

		int count = Integer.parseInt(UI.getText(interpreterDb.OfferedTabCount()));
		System.out.println(count);

		Thread.sleep(3000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTable());

		int interpreterDashboardAppointmentListRowsSize = CU
				.readNumberOfRowsInTable(interpreterDb.InterpreterDashboardAppointmentTable());

		Assert.assertEquals(count, interpreterDashboardAppointmentListRowsSize);
		logger.log(LogStatus.PASS, "The count beside Offered tab is verified with number of records displayed.");

		List<WebElement> column_View = driver.findElements(interpreterDb.InterpreterDashboardAppointmentTableColView());
		logger.log(LogStatus.INFO, "selected the column View ie appointment id");

		for (int k = 0; k <= interpreterDashboardAppointmentListRowsSize - 1; k++) {

			String id = column_View.get(k).getText();
			logger.log(LogStatus.INFO, "iterating through view column list");
			System.out.println(column_View.get(k).getText());

			if (id.equalsIgnoreCase(view_Text)) {

				System.out.println(k);
				column_View.get(k).click();
				logger.log(LogStatus.PASS, "able to click the id " + view_Text + " in OFFER tab page");

				Thread.sleep(3000);
				UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentClickTitle());

				String appointment_offer_title = driver
						.findElement(interpreterDb.InterpreterDashboardAppointmentClickTitle()).getText();
				System.out.println(appointment_offer_title);
				Assert.assertTrue(appointment_offer_title.contains(view_Text));
				logger.log(LogStatus.PASS, "Verified the title has the view id selected i.e." + view_Text);

				UI.click(interpreterDb.AcceptButton());

				logger.log(LogStatus.PASS, "could click Accept Appointment");

				Thread.sleep(10000);
				UI.waitForElementVisibility(interpreterDb.AcceptedTab());
				break;

			}
		}
		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");

		Thread.sleep(1000);
	}

	@Test(priority = 3,enabled=false)
	public void ReturnAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying returning an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "Confirmed");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find confirmed appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("Confirmed")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a confirmed appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				break;
			}
			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");

		Thread.sleep(1000);

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);

		UI.click(interpreterDb.AcceptedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Accepted tab");

		Thread.sleep(3000);

		UI.sendKeys(interpreterDb.Search(), view_Text);
		logger.log(LogStatus.INFO, "entered id " + view_Text + " in search box");

		Thread.sleep(2000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

		WebElement View_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());

		String acceptedId = View_accepted.getText();
		Assert.assertEquals(view_Text, acceptedId);
		logger.log(LogStatus.PASS, "Accepted appointment is avaialable in Accepted tab list");

		WebElement Status_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColStatus());
		String acceptedStatus = Status_accepted.getText();
		Assert.assertEquals("Confirmed", acceptedStatus);
		logger.log(LogStatus.PASS, "The status is confirmed in the list");

		View_accepted.click();
		logger.log(LogStatus.INFO, "Clicked the Appointment id in view column");

		UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

		Thread.sleep(10000);

		Actions actions = new Actions(driver);
		WebElement return_Appointment = driver.findElement(interpreterDb.ReturnAppointmentButton());
		actions.moveToElement(return_Appointment).perform();

		if (driver.findElement(interpreterDb.HoverTextOnReurnAppointmentButton()).isDisplayed()) {
			String hoverText = driver.findElement(interpreterDb.HoverTextOnReurnAppointmentButton()).getText();
			Assert.assertEquals(hoverText,
					"It is less than 24 Hours before the appointment start time. Please reach out to a Scheduler for immediate assistance");
			logger.log(LogStatus.PASS, "Hover Text " + hoverText + " is displayed");

			UI.click(interpreterDb.AppointmentCrossButton());

			UI.waitForElementVisibility(dashboard.logOut());

			UI.click(dashboard.logOut());

		} else {
			UI.click(interpreterDb.ReturnAppointmentButton());
			Thread.sleep(10000);
			UI.waitForElementVisibility(dashboard.logOut());

			UI.click(dashboard.logOut());
		}

	}

	@Test(priority = 4,enabled=false)
	public void CheckInAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying Clicking Check-in for an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "Confirmed");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find confirmed appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("Confirmed")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a confirmed appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				break;
			}
			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");

		Thread.sleep(1000);

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);

		UI.click(interpreterDb.AcceptedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Accepted tab");

		Thread.sleep(3000);

		UI.sendKeys(interpreterDb.Search(), view_Text);
		logger.log(LogStatus.INFO, "entered id " + view_Text + " in search box");

		Thread.sleep(2000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

		WebElement View_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());

		String acceptedId = View_accepted.getText();
		Assert.assertEquals(view_Text, acceptedId);
		logger.log(LogStatus.PASS, "Accepted appointment is avaialable in Accepted tab list");

		WebElement Status_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColStatus());
		String acceptedStatus = Status_accepted.getText();
		Assert.assertEquals("Confirmed", acceptedStatus);
		logger.log(LogStatus.PASS, "The status is confirmed in the list");

		View_accepted.click();
		logger.log(LogStatus.INFO, "Clicked the Appointment id in view column");

		UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

		Thread.sleep(1000);

		UI.waitForElementVisibility(interpreterDb.CheckInButton());

		UI.click(interpreterDb.CheckInButton());
		logger.log(LogStatus.PASS, "Clicked Check in button");

		Thread.sleep(10000);
		UI.waitForElementVisibility(dashboard.logOut());

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logged out as interpreter");

	}

	@Test(priority = 5,enabled=false)
	public void CheckOutAndFinaliseAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying Clicking Check-out for an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "Confirmed");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find confirmed appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("Confirmed")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a confirmed appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				break;
			}
			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");

		Thread.sleep(1000);

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);

		UI.click(interpreterDb.AcceptedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Accepted tab");

		Thread.sleep(3000);

		UI.sendKeys(interpreterDb.Search(), view_Text);
		logger.log(LogStatus.INFO, "entered id " + view_Text + " in search box");

		Thread.sleep(2000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

		WebElement View_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());

		String acceptedId = View_accepted.getText();
		Assert.assertEquals(view_Text, acceptedId);
		logger.log(LogStatus.PASS, "Accepted appointment is avaialable in Accepted tab list");

		WebElement Status_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColStatus());
		String acceptedStatus = Status_accepted.getText();
		Assert.assertEquals("Confirmed", acceptedStatus);
		logger.log(LogStatus.PASS, "The status is confirmed in the list");

		View_accepted.click();
		logger.log(LogStatus.INFO, "Clicked the Appointment id in view column");

		UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

		Thread.sleep(2000);
		UI.waitForElementVisibility(interpreterDb.CheckOutButton());

		UI.click(interpreterDb.CheckOutButton());
		logger.log(LogStatus.PASS, "Clicked Check out button");

		Thread.sleep(3000);

		UI.waitForElementVisibility(interpreterDb.FinalizeAppointmentPageTitle());

		List<WebElement> dropdown = driver.findElements(By.xpath("//input[contains(@id,'react-select')]"));

		dropdown.get(1).sendKeys("Spanish");
		dropdown.get(1).sendKeys(Keys.ENTER);
		dropdown.get(2).sendKeys("No");
		dropdown.get(2).sendKeys(Keys.ENTER);

		UI.click(interpreterDb.FinalizeAppointmentButton());

		Thread.sleep(10000);

		UI.waitForElementVisibility(dashboard.logOut());

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logged out as interpreter");

		/*
		 * UI.clear(interpreterDb.Search()); UI.sendKeys(interpreterDb.Search(),
		 * view_Text); logger.log(LogStatus.INFO, "entered id in search box");
		 */

		/*
		 * Thread.sleep(1000);
		 * 
		 * WebElement View_accepted_after_finalize = driver
		 * .findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());
		 * 
		 * View_accepted_after_finalize.click();
		 * 
		 * Thread.sleep(1000);
		 * 
		 * js.executeScript("window.scrollBy(0,100)", "");
		 * 
		 * //js.executeScript("window.scrollBy(0,250)", "");
		 * 
		 * UI.click(interpreterDb.FinalizeAppointmentButton());
		 * logger.log(LogStatus.PASS, "Clicked Finalize Appointment");
		 */

	}

	@Test(priority = 6,enabled=false)
	
	public void ViewFinancialBreakdown() throws InterruptedException, IOException {

		logger = report.startTest("Verifying Clicking Financial breakdown for an appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "Completed");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find confirmed appointment");

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("Completed")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a Completed appointment");

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				break;
			}
			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "clicked Log-out button");

		Thread.sleep(1000);

		CU.login(data.getDataAsString(sheet_Reject, "Interpreter Username", 1),
				data.getDataAsString(sheet_Reject, "Interpreter Password", 1));
		logger.log(LogStatus.INFO, "logged in as interpreter");

		Thread.sleep(2000);
		UI.waitForElementVisibility(navPanel.Home_Interpreter());

		UI.click(navPanel.Home_Interpreter());
		Thread.sleep(1000);
		UI.click(navPanel.SubInterpreter());
		Thread.sleep(1000);

		UI.click(interpreterDb.AcceptedTab());
		logger.log(LogStatus.INFO, "Clicked Interpreter>Accepted tab");

		Thread.sleep(3000);

		UI.sendKeys(interpreterDb.Search(), view_Text);
		logger.log(LogStatus.INFO, "entered id " + view_Text + " in search box");

		Thread.sleep(2000);
		UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

		WebElement View_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());

		String acceptedId = View_accepted.getText();
		Assert.assertEquals(view_Text, acceptedId);
		logger.log(LogStatus.PASS, "Accepted appointment is avaialable in Accepted tab list");

		WebElement Status_accepted = driver.findElement(interpreterDb.InterpreterDashboardAppointmentTableColStatus());
		String acceptedStatus = Status_accepted.getText();
		Assert.assertEquals("Completed", acceptedStatus);
		logger.log(LogStatus.PASS, "The status is completed in the list");

		View_accepted.click();
		logger.log(LogStatus.INFO, "Clicked the Appointment id in view column");

		UI.waitForElementVisibility(interpreterDb.AppointmentPageTitle());

		Thread.sleep(2000);

		UI.click(interpreterDb.FinancialBreakDownButton());
		logger.log(LogStatus.INFO, "clicked the FinancialBreakDown button ");

		Thread.sleep(1000);

		UI.click(interpreterDb.FinancialBreakDownPageCrossButton());
		logger.log(LogStatus.INFO, "clicked cross button of financial break down page cross button to exit the page ");

		Thread.sleep(1000);

		UI.click(interpreterDb.AppointmentCrossButton());
		logger.log(LogStatus.INFO, "clicked cross button of appointment page ");

		Thread.sleep(10000);

		UI.waitForElementVisibility(dashboard.logOut());
		Thread.sleep(2000);

		UI.click(dashboard.logOut());
		logger.log(LogStatus.INFO, "logged out as interpreter");
		Thread.sleep(2000);

	}

	@Test
	public void SelfBookAppointment() throws InterruptedException, IOException {

		logger = report.startTest("Verifying self book appointment by interpreter");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		CU.login(data.getDataAsString(sheet_Reject, "Scheduler Username", 1),
				data.getDataAsString(sheet_Reject, "Scheduler Password", 1));
		logger.log(LogStatus.INFO, "Logged in as scheduler");

		UI.waitForElementVisibility(dashboard.newAppointment());

		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "current page is all appointments dashboard");

		UI.sendKeys(dashboard.search(), "new");
		logger.log(LogStatus.INFO, "Entered new in search box");

		Thread.sleep(2000);

		int allAppRowsSize = CU.readNumberOfRowsInTable(dashboard.allAppointmentTableBody());

		logger.log(LogStatus.INFO, "Found number of rows in the page: " + allAppRowsSize);

		List<WebElement> column_status = UI.getElements(dashboard.allAppointmentTableBodyRowsStatusColumn());
		logger.log(LogStatus.INFO, "selected the column Status");

		List<WebElement> column_language = UI.getElements(dashboard.allAppointmentTableBodyRowsLanguageColumn());
		logger.log(LogStatus.INFO, "selected the column Language");

		String view_Text = null;// for caturing appointment id

		logger.log(LogStatus.INFO, "looping through all rows in Status column till we find new appointment with language "+data.getDataAsString(sheet_Reject, "Requested Language", 1));

		for (int i = 0; i <= allAppRowsSize - 1; i++) {

			String status = column_status.get(i).getText();
			System.out.println(column_status.get(i).getText());

			String language = column_language.get(i).getText();
			System.out.println(column_language.get(i).getText());

			if (status.equalsIgnoreCase("new")
					&& language.equalsIgnoreCase(data.getDataAsString(sheet_Reject, "Requested Language", 1))) {

				logger.log(LogStatus.INFO, "found a new appointment with requested language "+data.getDataAsString(sheet_Reject, "Requested Language", 1));

				List<WebElement> column_view = UI.getElements(dashboard.allAppointmentTableBodyRowsViewColumn());

				WebElement view_id = column_view.get(i);

				view_Text = view_id.getText();

				System.out.println(view_Text);

				Thread.sleep(1000);

				view_id.click();
				logger.log(LogStatus.INFO, "able to click appointment id:" + view_Text);
				Thread.sleep(3000);
				UI.waitForElementVisibility(appDetails.appointmentId());
				logger.log(LogStatus.INFO, " navigated to the appointment details page");
				String appId = driver.findElement(appDetails.appointmentId()).getAttribute("value");
				Assert.assertEquals(view_Text, appId);
				logger.log(LogStatus.PASS,
						"clicked appointment id:" + view_Text + " and verified same appointment is being displayed");

				break;
			}

			js.executeScript("window.scrollBy(0,100)", "");
		}

		UI.click(appDetails.tabInterpreterMatching());

		logger.log(LogStatus.INFO, " Clicked INTERPRETER MATCHING Tab");

		Thread.sleep(1000);
		UI.waitForElementVisibility(appDetails.buttonFindInterpreters());

		UI.click(appDetails.buttonFindInterpreters());

		logger.log(LogStatus.INFO, " Clicked the button FIND INTERPRETERS");

		UI.waitForElementVisibility(appDetails.interpreterListTableBody());
		Thread.sleep(1000);
		
		int interpreterListRowsSize = CU.readNumberOfRowsInTable(appDetails.interpreterListTableBody());
		
		List<WebElement> column_Interpreter_Email = driver.findElements(appDetails.interpreterListTableEmailCol());
		logger.log(LogStatus.INFO, "selected the column Interpreter name");
		
		System.out.println(interpreterListRowsSize);
		
		System.out.println(column_Interpreter_Email.size());
		logger.log(LogStatus.INFO, "number of interpreters available "+column_Interpreter_Email.size());
		
		String[] interpreters = new String[column_Interpreter_Email.size()];
		
		for (int i = 0; i < interpreterListRowsSize; i++) {

			System.out.println(column_Interpreter_Email.get(i).getText());
			
			interpreters[i] = column_Interpreter_Email.get(i).getText();
			    
		        // Printing using for each loop
		        for (String k : interpreters) {
		            System.out.println(k);
		        }
			
			
		}

		UI.click(appDetails.close());
		
		Thread.sleep(1000);

		UI.click(dashboard.logOut());

		for (int j = 0; j < interpreterListRowsSize; j++)  {

			System.out.println(interpreters[j]);

			String interpreter = interpreters[j];

			CU.login(interpreter, "Welcome@1");
			logger.log(LogStatus.INFO, "Logged in as interpreter " + interpreter + " found in the list for the id "+view_Text);

			Thread.sleep(2000);

			if (UI.isDisplayed(dashboard.newAppointment())) {

				logger.log(LogStatus.INFO, "current page is all appointments dashboard");

				UI.waitForElementVisibility(navPanel.Interpreters());
				UI.click(navPanel.Interpreters());
				UI.waitForElementVisibility(navPanel.SubInterpreter());
				UI.click(navPanel.SubInterpreter());
				Thread.sleep(1000);
				logger.log(LogStatus.INFO, "Clciked Interpreters>interpreters");

				UI.sendKeys(interpreterDb.Search(), interpreter);
				logger.log(LogStatus.INFO, "Entered the interpreter email " + interpreter + " in search");

				driver.findElement(By.xpath("//td[@class='MuiBox-root css-e1swko']/div/div")).click();
				logger.log(LogStatus.INFO, "clicked the interpreter");

				Thread.sleep(2000);
				UI.waitForElementVisibility(interpreterDb.EditInterpreter());
				UI.click(interpreterDb.EditInterpreter());
				logger.log(LogStatus.INFO, "clicked the Edit Interpreter to see the status of checkbox");

				Thread.sleep(2000);
				UI.waitForElementVisibility(interpreterDb.CanSelfBookAppointment());

				String selfbookCheckboxValue = UI.getElement(interpreterDb.CanSelfBookAppointment())
						.getAttribute("value");

				if (selfbookCheckboxValue.equalsIgnoreCase("true")) {
					
					logger.log(LogStatus.INFO, "ths check box is selected ie this interpreter can self book an appointment");

					UI.click(interpreterDb.Cancel());
					UI.click(interpreterDb.Close());

					UI.click(navPanel.Home_Interpreter());
					Thread.sleep(1000);
					UI.click(navPanel.SubInterpreter());
					Thread.sleep(1000);
					UI.isDisplayed(interpreterDb.AvailableTab());
					logger.log(LogStatus.PASS, "Available tab is displayed as self book appointment check box is checked.");
					UI.click(interpreterDb.AvailableTab());
					logger.log(LogStatus.PASS, "Clicked Available tab");
					UI.sendKeys(interpreterDb.Search(), view_Text);
					logger.log(LogStatus.INFO, "entered id "+view_Text+" in search box");

					Thread.sleep(2000);
					UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentTableColView());

					WebElement View_id = driver
							.findElement(interpreterDb.InterpreterDashboardAppointmentTableColView());

					View_id.click();

					logger.log(LogStatus.PASS, "appointment id " + view_Text + " is displayed in AVAILABLE tab page");

					Thread.sleep(3000);
					UI.waitForElementVisibility(interpreterDb.InterpreterDashboardAppointmentClickTitle());

					String appointment_offer_title = driver
							.findElement(interpreterDb.InterpreterDashboardAppointmentClickTitle()).getText();
					System.out.println(appointment_offer_title);
					Assert.assertTrue(appointment_offer_title.contains(view_Text));
					logger.log(LogStatus.PASS, "Verified the title has the view id selected i.e." + view_Text);

					UI.click(interpreterDb.AcceptButton());

					logger.log(LogStatus.PASS, "could click Accept Appointment");

					Thread.sleep(10000);
					
					break;

				} else if (selfbookCheckboxValue.equalsIgnoreCase("false")) {

					UI.click(interpreterDb.Cancel());
					UI.click(interpreterDb.Close());

					UI.click(navPanel.Home_Interpreter());
					Thread.sleep(1000);
					UI.click(navPanel.SubInterpreter());
					Thread.sleep(1000);

					Boolean availableTab = UI.isDisplayed(interpreterDb.AvailableTab());

					if (availableTab) {
						logger.log(LogStatus.FAIL, "Available tab is displayed even though the check box is unchecked");
						
					} else {
						logger.log(LogStatus.PASS, "Available tab is not displayed");
					}
				}

			}

			else if (UI.isDisplayed(loginPage.invalidCredentialsErrorMsg())) {

				System.out.println("considering another interpreter in list as password is unknown");

			}

		}

	}

	@AfterMethod
	public void signout(ITestResult result) throws InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path " + path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));

			System.out.println("Entered After method");

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "This test skipped");
		}

		report.endTest(logger);
		driver.quit();

	}

	@AfterClass
	public void teardown() {
		System.out.println("Entered After Class");
		// driver.quit();
	}

}
