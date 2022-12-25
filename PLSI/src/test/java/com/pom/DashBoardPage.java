package com.pom;

import org.openqa.selenium.By;

public class DashBoardPage {
	
	public By Menu() {
		return By.cssSelector(".MuiBox-root.css-w2sxf");
	}
	
	public By logOut() {
    	
		return By.xpath("//span[text()='logout']");
		//return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorInherit.MuiIconButton-sizeSmall.css-9ixttz");
    }////span[text()='logout']
	
	public By search() {
		return By.xpath("//input[@class='MuiInputBase-input css-1bqqmdo']");	
	}
	//input[@class='MuiInputBase-input css-1bqqmdo']

	public By newAppointment() {
		return By.cssSelector(".css-1txeit4");
	//return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeSmall.MuiButton-containedSizeSmall.MuiButton-fullWidth.css-l781vv");
	}

	public By allAppointmentTable() {
	return By.xpath("//table[@class='MuiTable-root css-jiyur0']");
	}
	
	public By allAppointmentTableBody() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody");
		}
	
	public By allAppointmentTableBodyRows() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr");
	}
	
	public By allAppointmentTableBodyRowsDatecolumn() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[2]");
	}
	
	public By allAppointmentTableBodyRowsStatusColumn() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[9]");
	}
	
	public By allAppointmentTableBodyRowsViewColumn() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[1]");
	}
	
	public By allAppointmentTableBodyRowsPatientColumn() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[7]");
	}
	
	public By allAppointmentTableBodyRowsLanguageColumn() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr/td[8]");
	}
	
	
	public By appointmentCreatedSuccessMsg() {
		return By.cssSelector(".MuiBox-root.css-lhz7xj");
	}
	
	public By filter() {
		return By.cssSelector(".MuiBox-root.css-8kdvm0");
	}
}
