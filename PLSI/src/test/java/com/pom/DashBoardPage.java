package com.pom;

import org.openqa.selenium.By;

public class DashBoardPage {
	
	public By logOut() {
    	return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorInherit.MuiIconButton-sizeSmall.css-9ixttz");
    }

	public By newAppointment() {
	return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeSmall.MuiButton-containedSizeSmall.MuiButton-fullWidth.css-l781vv");
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
}
