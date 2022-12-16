package com.pom;

import org.openqa.selenium.By;

public class AppointmentDetails {
	
	public By tabInterpreterMatching() {
		return By.xpath("//span[text()='INTERPRETER MATCHING']");
	}
	public By buttonFindInterpreters() {
		return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-outlined.MuiButton-outlinedPrimary.MuiButton-sizeMedium.MuiButton-outlinedSizeMedium.css-1kqzef6");
	}
	
	
	public By interpreterListTable() {
		return By.xpath("//table[@class='MuiTable-root css-jiyur0']");
		}
	
	public By makeAnOffer() {
		return By.xpath("//span[text()='make an offer']");
	}
	
	public By close() {
		return By.xpath("//button[text()='Close']");
	}
	

}
