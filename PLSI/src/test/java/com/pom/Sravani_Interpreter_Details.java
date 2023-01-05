package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Sravani_Interpreter_Details {
	
	public WebElement userName(WebDriver driver) {
		return driver.findElement(By.name("email"));
	}
	
	public WebElement password(WebDriver driver) {
		return driver.findElement(By.name("password"));
	}
	
	public WebElement loginButton(WebDriver driver) {
		return driver.findElement(By.xpath("//button[@type='submit']"));
		
	}
public WebElement urgentTab(WebDriver driver) {
	return driver.findElement(By.xpath("//span[contains(text(),'URGENT')]"));
}
public WebElement appointment(WebDriver driver) {
	/*return driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr[1]/td[1]"));*/
	return driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr[3]/td[count(//table[@class='MuiTable-root css-jiyur0']/thead/tr/th/div[text()='View'])]"));
}
public WebElement Interpreter(WebDriver driver) {
	/*return driver.findElement(By.xpath("//span[@class='MuiBadge-root BaseBadge-root css-1rzb3uu' and text()='INTERPRETER MATCHING']"));*/
	return driver.findElement(By.xpath("//span[@class='MuiBadge-root MuiBadge-root css-1rzb3uu' and text()='INTERPRETER MATCHING']"));
}

public WebElement findInterpreter(WebDriver driver) {
	return driver.findElement(By.xpath("//img[@class='MuiBox-root css-1iulk6e']"));
}
public WebElement makeAnOffer(WebDriver driver) {
	return driver.findElement(By.xpath("//span[text()='make an offer']"));
}
public WebElement RescindOffer(WebDriver driver) {
	return driver.findElement(By.xpath("//*[local-name()='svg' and @data-testid='ReplayIcon']/*[local-name()='path']"));
}
public WebElement PatientConsumer(WebDriver driver) {
	return driver.findElement(By.xpath("//table[@class='MuiTable-root css-jiyur0']/tbody/tr[2]/td[7]"));
}
public WebElement preference(WebDriver driver) {
	return driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant preferences']"));
}
public WebElement requester(WebDriver driver) {
	return driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant comments']"));
}
public WebElement scheduler_notes(WebDriver driver) {
	return driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiInputBase-colorPrimary MuiInputBase-multiline css-116j8tg' ]//*[@placeholder='Enter relevant notes']"));
}
public WebElement Save(WebDriver driver) {
	return driver.findElement(By.xpath("//button[text()=' Save']"));
}

}
