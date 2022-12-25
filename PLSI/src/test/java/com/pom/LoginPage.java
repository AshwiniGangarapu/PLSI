package com.pom;

import org.openqa.selenium.By;

public class LoginPage{

	public By emailAddress() {
		return By.name("email");
	}

	public By password() {
		return By.name("password");
	}

	public By logIn() {
		//return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-fullWidth.css-x2tih8");
		//return By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-fullWidth.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-fullWidth.css-1y0tw5r");
		return By.xpath("//button[@type='submit']");
	}

	public By invalidCredentialsErrorMsg() {
		return By.cssSelector(".MuiTypography-root.MuiTypography-body2.css-1xyju2g");
	}

}

