package com.jcpenney.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jcpenney.base.TestBase;

public class CartPage extends TestBase {

	//*******Page Factory or Object repository--@FindBy()
	@FindBy(css= "[data-automation-id='at-product-title-link']")  
	WebElement productInCart;

	@FindBy(css="[data-automation-id='at-remove-item-link']")   
	WebElement removeProduct;
	
	@FindBy(css="[data-automation-id='cart-is-empty']")
	WebElement emptyCart;

	//*******initElements a static method of pageFactory used to initialize all the webElements located by @FindBy annotation.
	public CartPage()   //Constructor has same name as the class
	{
		PageFactory.initElements(driver, this); 
	}

	//*******methods or actions with return type
	public String ProductInCart() {
		return productInCart.getText();   //Captures the product name in the cart
	}

	public String removeProductFromCart() {     
		removeProduct.click();
		return emptyCart.getText();      //Captures the text of an empty cart
	}
	
}