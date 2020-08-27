package com.jcpenny.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.jcpenny.base.TestBase;
import com.jcpenny.util.TestUtil;

public class HomePage extends TestBase {

	//*******Page Factory or Object repository--@FindBy()
	@FindBy(xpath= "//div[@data-automation-id='menu-item-link-1']" )
	WebElement moveTo;

	@FindBy(xpath= "//a[text()='Home Electronics']")
	WebElement homeElectronicOption ;

	@FindBy(css= "[data-automation-id='jcp-logo']")
	WebElement logo ;

	//*******initElements a static method of pageFactory used to initialize all the webelements located by @FindBy annotation.
	public HomePage() //constructor same name as the class. 
	{
		PageFactory.initElements(driver, this); //"driver" comes from TestBase class through inheritance and "this" keyword refers to current class objects like logo, moveTo.... 
	}

	//*******methods or actions with return type
	
	public String HomePageTitle() //returns HomePage title
	{  
		return driver.getTitle();
	}

	public boolean logo() {       //return type of this method is boolean- True or False
		return logo.isDisplayed();  //Checks whether logo is dipalyed
	}

	public String getcolor() {
		return logo.getCssValue("color");  //retieves CssProperty(color) of logo
	}

	public String getFontSize() {
		return logo.getCssValue("font-size"); //retieves font size of logo
	}

	public String getFontFamily() {
		return logo.getCssValue("font-family"); //retieves font-family of logo
	}


	public HomeElectronicsPage mouseHoverToElement() //return type of this method is Home Electronics page
	{   
		TestUtil.actions(moveTo);             //Calling "actions" method from TestUtil class, which helps with mouse hover.
		homeElectronicOption.click();         //clicking "Home electronics" option from dropdown options
		return new HomeElectronicsPage();     //navigates to HomeElectronicsPage. 
	}




}
