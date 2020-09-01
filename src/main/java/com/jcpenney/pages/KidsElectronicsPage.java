package com.jcpenney.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jcpenney.base.TestBase;

public class KidsElectronicsPage extends TestBase {



	@FindBy(xpath= "//h6[text()='Frozen 2 Earbuds with Mic and Pouch']")
	WebElement product;
	
	
	public KidsElectronicsPage() 
	{
		PageFactory.initElements(driver, this); 
	}


	public String KidsElectronicsPageTitle()  //gets page title of KidsElectronicsPage
	{  
		return driver.getTitle();
	}
	
	public ProductDetailsPage selectAProduct() //return type of this method is ProductDetailsPage
	{
		product.click();                       //clicks on desired product
        return new ProductDetailsPage();       //navigates to ProductDetailsPage
	}

	




}
