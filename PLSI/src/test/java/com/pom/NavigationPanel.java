package com.pom;

import org.openqa.selenium.By;

public class NavigationPanel {
	
	public By Interpreters(){
	
		 return By.xpath("//li[@class='MuiListItem-root MuiListItem-padding css-1u6s8c4'][2]");
		 
	}
	
	 public By Home_Interpreter () {
		 
		 return By.xpath("//li[@class='MuiListItem-root MuiListItem-padding css-1u6s8c4'][4]");
		 
	 }
	
	  public By SubInterpreter () {
		  
          return By.xpath("//li[@class='MuiListItem-root MuiListItem-padding css-9bln5v']");
          
      }
	  
	  

}
