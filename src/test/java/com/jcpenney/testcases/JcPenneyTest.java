package com.jcpenney.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jcpenney.pages.CartPage;
import com.jcpenney.pages.HomeElectronicsPage;
import com.jcpenney.pages.HomePage;
import com.jcpenney.pages.KidsElectronicsPage;
import com.jcpenney.pages.ProductDetailsPage;
import com.jcpenney.util.AbstractMethods;

public class JcPenneyTest extends AbstractMethods //****Inherting the methods of "AbstractMethods" class by using the keyword "extends"
{                                                //All "@Override" methods are inherited from "AbstractMethods" class

	//**Creating object for all Page classes to access its methods
	HomePage homePage;
	HomeElectronicsPage homeElectronicPage;
	KidsElectronicsPage kidsElectronicsPage;
	ProductDetailsPage  productDetailsPage;
	CartPage cartPage;


	@BeforeClass  //Annotation from TestNG. This annotation is executed first, before all the methods in a class.
	public void setup() 
	{
		initialization();  //calling initialization() from TestBase
		homePage = new HomePage();
	}

	//Navigates to HomePage and verify HomePage Title
	@Test(priority=1)  //@Test annotation, marks a method as part of the test.
	@Override 
	public void verifyHomePageTitle() 
	{
		logger.info("Validating HomePage Title");
		String homePageTitle= homePage.HomePageTitle(); //Method "HomePageTitle" in "HomePage" class, returns page title, which is stored in variable "homePageTitle"
		Assert.assertEquals(homePageTitle, "JCPenney: Window & Home Decor, Bedding, Clothing & Accessories"); //validating actual and expected results
	}

	//Verify if HomePage Logo is displayed
	@Test(priority=2)
	@Override
	public void verifyLogo() 
	{
		logger.info("Verifying if Logo is Displayed");
		boolean logo = homePage.logo(); //Calling method "logo" from "HomePage" class, which returns whether the logo is displayed or not. Return type is a Boolean(true/false)
		Assert.assertTrue(logo, "Logo is Displayed"); 
	}

	//Verify HomePage logo color
	@Test(priority=3)
	@Override
	public void verifyLogoColor() 
	{
		logger.info("Validating Logo color");
		String color = homePage.getcolor();  //Calling method "getcolor" from "HomePage" class, which returns the color of Logo, and it is stored in variable String color.
		Assert.assertEquals(color, "rgba(231, 19, 36, 1)"); //TestNG assertion to validate actual and expected result
	}

	//Validate HomePage Logo Font Size
	@Test(priority=4)
	@Override
	public void verifyLogoFontSize() 
	{
		logger.info("Validating Logo font size ");
		String fontSize = homePage.getFontSize(); //Calling method "getFontSize" from "HomePage" class, which returns the font-size of the Logo, and it is stored in variable String fontSize.
		Assert.assertEquals(fontSize, "12px");
	}

	//Validate Homepage Logo's font family
	@Test(priority=5)  
	@Override
	public void verifyLogoFontFamily() 
	{
		logger.info("Validating Logo fontFamily ");
		String fontFamily = homePage.getFontFamily(); //Calling method "getFontFamily" from "HomePage" class, which returns the font-family of the Logo, and it is stored in variable String fontFamily
		Assert.assertEquals(fontFamily, "\"Open Sans\", OpenSans, sans-serif");
	}

	//Navigate to HomeElectronicsPage and verify Title
	@Test(priority=6)
	public void navigateToHomeElectronicsPage() 
	{
		logger.info("Click \"HomeElectronics\" from dropdown options");
		homeElectronicPage = homePage.mouseHoverToElement(); //Calling method "mouseHoverToElement" from "HomePage" class. This method navigates to "HomeElectronics" page
		logger.info("Navigated to \"HomeElectronics\" page");
		
		logger.info("Validating HomeElectronics page title");
		String electronicPageTitle = homeElectronicPage.getElectronicsPageTitle();
		Assert.assertEquals(electronicPageTitle, "Electronics For The Home - JCPenney");
	
	}

	//Navigate to KidsElectronicsPage and verify title
	@Test(priority=7)
	public void navigateToKidsElectronicsPage()
	{
		logger.info("Click \"Kids Tech\" element");
		kidsElectronicsPage= homeElectronicPage.selectKidsElectronics(); //Calling method "selectKidsElectronics" from "HomeElectronicPage" class. This method navigates to "KidsElectronicsPage" after clicking "Kids Tech" option 
		logger.info("Navigated to \"Kids Electronics\" page");
		
		logger.info("Validating KidsElectronics page title");
		String KidsPageTitle = kidsElectronicsPage.KidsElectronicsPageTitle();
		Assert.assertEquals(KidsPageTitle, "Electronics For The Home - JCPenney");
	}

	//Select a product from kids electronics and verify the product title 
	@Test(priority=8)
	public void SelectAProductAndVerify() 
	{
		logger.info("click on product \"Frozen 2 Earbuds with Mic and Pouch\"");
		productDetailsPage = kidsElectronicsPage.selectAProduct(); //Method "selectAProduct" is called from "kidsElectronicsPage" class.
		logger.info("Navigated to \"Product Details\" page");
		
		logger.info("Validating title of selected product");
		String text = productDetailsPage.getProductTitle();
		Assert.assertEquals(text, "Frozen 2 Earbuds with Mic and Pouch");
		
	}

	//Verify Product title in cart
	@Test(priority=9)
	public void verifyProductInCart() 
	{
		logger.info("Add product to cart and checkOut");
		cartPage = productDetailsPage.AddToCart();
		logger.info("Navigated to \"Cart page\"");
		
		logger.info("Validating product in cart");
		String getText = cartPage.ProductInCart();
		Assert.assertEquals(getText, "Frozen 2 Earbuds with Mic and Pouch");
		
	}

	//Demonstration of a simulated failure test case, to generate failure screenshot and failure reason in extent report 
	@Test(priority=10)
	public void verifyCartAfterRemovingProduct() 
	{
		logger.info("click \"remove\" WebElement in CartPage");  
		String emptyCartText = cartPage.removeProductFromCart();  //Remove the product from cart and verify empty cart's text
		
		logger.info("Validating empty cart text, after the product is being removed ");
		Assert.assertEquals(emptyCartText, "Start with an Empty Cart");
	}

	@AfterClass
	public void teardown() {
		quit();
	}










}
