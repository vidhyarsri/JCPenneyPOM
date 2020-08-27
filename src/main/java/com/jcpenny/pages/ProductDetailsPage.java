package com.jcpenny.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.jcpenny.base.TestBase;

public class ProductDetailsPage extends TestBase{



	@FindBy(css= "[data-automation-id='addToCart']")
	WebElement addToCart;

	@FindBy(css= "[data-automation-id='at-panel-checkout-button']")
	WebElement checkOut;

	@FindBy(xpath="//h1[@id='productTitle-false']")
	WebElement productText;

	public ProductDetailsPage() 
	{
		PageFactory.initElements(driver, this); 
	}

	public String getProductTitle()   //gets title of the product that has been selected
	{  
		return productText.getText();
	}

	public CartPage AddToCart()  //return type of this method is "CartPage"
	{
		addToCart.click();      //click "Add to cart" button
		WebDriverWait wait = new WebDriverWait(driver, 10);   //Explicit wait for checkout button.It waits until visibility of checkout button element.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-automation-id='at-panel-checkout-button']")));
		checkOut.click();       //clicks "View Cart & Checkout" button
		return new CartPage();  //navigates to "CartPage"
	}





}
