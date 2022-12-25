package com.pom;

import org.openqa.selenium.By;

public class AppointmentDetails {
	
	public By appointmentId() {
		return By.xpath("//input[@name='AppointmentId']");
	}
	
	public By tabInterpreterMatching() {
		return By.xpath("//span[text()='INTERPRETER MATCHING']");
	}
	public By buttonFindInterpreters() {
		return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-outlined.MuiButton-outlinedPrimary.MuiButton-sizeMedium.MuiButton-outlinedSizeMedium.css-1kqzef6");
	}
	
	
	public By interpreterListTable() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']");
		}
	
	public By interpreterListTableBody() {
	
		return By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 MuiGrid-grid-lg-12 css-1y5l420']//table/tbody");
	}
	
	public By interpreterListTableActionsCol() {
		
		return By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 MuiGrid-grid-lg-12 css-1y5l420']//table//tr/td[6]");
	}
	
    public By interpreterListTableInterpreterCol() {
		
		return By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 MuiGrid-grid-lg-12 css-1y5l420']//table//tr/td[1]");
	}
	
	public By makeAnOffer() {
		return By.xpath("//span[text()='make an offer']");
	}
	
	public By close() {
		return By.xpath("//button[text()='Close']");
	}
	

}
