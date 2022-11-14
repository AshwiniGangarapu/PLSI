package com.pom;

import org.openqa.selenium.By;

public class AllAppointmentsPage {
		
	public By appointmentDate() {
    	return By.name("appointments.0.aptDate");
    	    }
	public By appointmentStartTime() {
		return By.name("appointments.0.aptStartTime");
	}
	
	public By appointmentEndTime() {
		return By.name("appointments.0.aptEndTime");
	}
	
	public By client() {
		return By.xpath("//input[@id='react-select-4-input']");		
	}
		
	public By Facility() {
	return By.xpath("//input[@id='react-select-5-input']");
	}
	
	public By appointmentType() {
		return By.xpath("//input[@id='react-select-6-input']");	
	}
	
	public By building() {
		return By.xpath("//input[@id='react-select-7-input']");	
	}
	
	public By department() {
		return By.xpath("//input[@id='react-select-8-input']");
	}
	
	public By searchPatient() {
		return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-text.MuiButton-textPrimary.MuiButton-sizeSmall.MuiButton-textSizeSmall.css-u3aylm");
		}
	
	public By patientInfoTable() {		
		return By.xpath("//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation24 MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthXl MuiDialog-paperFullWidth css-1liyizg']//table");
	}
	
	public By patientInfoTableRows() {
		return By.xpath("//tr[@class='MuiTableRow-root css-1pe2zvv']");
	}
	
	public By requestedLanguage() {
		return By.xpath("//input[@id='react-select-11-input']");
	}
	
	public By requesterName() {
		return By.name("requesterName");
	}
	
	public By requesterPhone() {
		return By.name("requesterPhone");
	}
	
	public By requesterEmail() {
		return By.name("requesterEmail");
	}
	
	public By setAppointment() {
		return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.css-pafbcf");
		
	}
	
	
	
}