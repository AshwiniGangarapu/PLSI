package com.pom;

import org.openqa.selenium.By;

public class InterpreterDashboard {

	public By OfferedTab() {
		
		return By.xpath("//span[text()='OFFERED']");
		
	}
	public By OfferedTabCount() {
		
		return By.xpath("//span[text()='OFFERED']/div");
		
	}

	public By AcceptedTab() {
		
		return By.xpath("//span[text()='ACCEPTED']");
	}
	
    public By AvailableTab() {
		
		return By.xpath("//span[text()='AVAILABLE']");
	}

	public By Search() {
		
		return By.xpath("//input[@class='MuiInputBase-input css-1bqqmdo']");
	}

	public By InterpreterDashboardAppointmentTable() {

		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody");
	}

	public By InterpreterDashboardAppointmentTableColView() {

		return By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr/td[1]");
	}
	
	public By InterpreterDashboardAppointmentTableColStatus() {

		return By.xpath("//table[@class='MuiTable-root css-jiyur0']//tr[1]/td[12]");
	}

	public By InterpreterDashboardAppointmentClickTitle() {
		
		return By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-16o4yi7']");
	}

	public By DeclineButton() {

		return By.xpath("//button[text()='Decline Appointment']");
	}

	public By AcceptButton() {

		return By.xpath("//button[text()='Accept Appointment']");
	}

	public By ReturnAppointmentButton() {

		return By.xpath("//button[text()='Return Appointment']");
	}

	public By HoverTextOnReurnAppointmentButton() {

		return By.xpath(
				"//div[@class='MuiTooltip-tooltip MuiTooltip-tooltipArrow MuiTooltip-tooltipPlacementTop css-8milxz']");
	}

	public By CheckInButton() {

		return By.xpath("//button[text()='Check In']");

	}

	public By CheckOutButton() {

		return By.xpath("//button[text()='Check Out']");

	}

	public By FinalizeAppointmentPageTitle() {

		return By.xpath("//p[text()='Finalize Appointment']");
	}

	public By FinalizeAppointmentButton() {

		return By.xpath("//button[text()='Finalize Appointment']");
	}
	
    public By FinalizeAppointmentPageCrossButton() {
		
		return By.xpath("//p[text()='View Financial Breakdown - undefined']/button");
		
	}

	public By FinancialBreakDownPageTitle() {

		return By.xpath("//p[text()='View Financial Breakdown - undefined']");

	}

	public By FinancialBreakDownButton() {
		
		return By.xpath("//button[text()='View Financial Breakdown']");
		
	}
	
	public By FinancialBreakDownPageCrossButton() {
		
		return By.xpath("//p[text()='View Financial Breakdown - undefined']/button");
		
	}
	
	public By AppointmentPageTitle() {
		
		return By.xpath("//p[ contains (text(),'Appointment -')]");
	}
	
public By AppointmentCrossButton() {
		
		return By.xpath("//p[ contains (text(),'Appointment -')]/button");
		
	}

public By EditInterpreter() {
	
	return By.xpath("//button[text()='EDIT INTERPRETER']");
	
}

public By CanSelfBookAppointment() {
	return By.xpath("//span[text()='Can Self-Book for Appointments']");
}

public By Cancel() {
	
	return By.xpath("//button[text()='Cancel']");
	
}

public By Close() {
	return By.xpath("//button[text()='close']");
}


	
}
