package com.pom;

import org.openqa.selenium.By;

public class FilterInDashboard {

	public By startDate() {
		return By.name("startDate");
	}
	
	public By endDate() {
		return By.name("endDate");
	}
	
	public By applyButton() {
		return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-fullWidth.css-x2tih8");
	}
	
	public By crossButton() {
		return By.cssSelector(".MuiSvgIcon-root.MuiSvgIcon-fontSizeInherit.css-1amtie4");
	}
	
	public By language() {
		
		return By.id("react-select-34-input");
	}
	
}
