package com.jcpenney.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jcpenney.base.TestBase;

public class HomeElectronicsPage extends TestBase {
	
	@FindBy(css= "[title='Kids Tech']")
	WebElement kids;
	

	public HomeElectronicsPage() 
	{
		PageFactory.initElements(driver, this); 
	}


	public String getElectronicsPageTitle()  //gets title of HomeElectronics Page
	{  
		return driver.getTitle();
	}
	
	public KidsElectronicsPage selectKidsElectronics()  //return type of this method is "KidsElectronicsPage"
	{     
		kids.click();                       //Clicking "Kids Tech" element
		return new KidsElectronicsPage();   //navigates to "KidsElectronicsPage"
	}
	

	
}
