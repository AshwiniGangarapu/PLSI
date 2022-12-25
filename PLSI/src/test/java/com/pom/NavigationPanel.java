package com.pom;

import org.openqa.selenium.By;

public class NavigationPanel {
	
	 public By Home_Interpreter () {
		 
		 return By.xpath("//li[@class='MuiListItem-root MuiListItem-padding css-1u6s8c4'][4]");
	 }//span[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-wbk34u' and text()='Interpreter']
	
	  public By SubInterpreter () {
          return By.xpath("//li[@class='MuiListItem-root MuiListItem-padding css-9bln5v']");
      }

}
