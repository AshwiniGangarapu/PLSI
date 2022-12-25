package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.pom.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class CommonUtils extends BaseClass {

	WebDriver driver = null;

	SeleniumUIUtils UI = null;

	LoginPage loginPage = new LoginPage();

	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		UI = new SeleniumUIUtils(driver);
	}

	public String getUserName(XSSFSheet sheet) throws IOException {

		String UserName = data.getDataAsString(sheet, "UserName", 2);
		return UserName;
	}

	public void login(String username, String password) {

		UI.sendKeys(loginPage.emailAddress(), username);

		UI.sendKeys(loginPage.password(), password);

		UI.click(loginPage.logIn());

	}

	public int readNumberOfRowsInTable(By ele) {

		WebElement toGetRows = UI.getElement(ele);
		List<WebElement> rowList = toGetRows.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : " + rowList.size());
		return rowList.size();

	}

	public FileInputStream readFile(String filePath) {
		File file = new File(filePath);
		FileInputStream read = null;
		try {
			read = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			new FileNotFoundException("File not found in >>> " + filePath);
		}
		return read;
	}

}
