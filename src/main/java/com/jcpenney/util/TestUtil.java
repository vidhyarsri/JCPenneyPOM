package com.jcpenney.util;
import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.jcpenney.base.TestBase;

public class TestUtil extends TestBase{

	//*********Using Utility class to store all reusable codes such as Waits, Alerts, Takescreenshot, Actions, Select, Frames and multiple windows

	public static long PAGE_LOAD_TIMEOUT = 20; //static belongs to the class. Can be invoked without an object 
	public static long IMPLICIT_WAIT = 10;
	public static String parentWinId;
	public static Set<String> ids;



	public static void actions(WebElement element) //WebElement element is a parameter. Method use this as a local variable.
	{
		Actions action=new Actions(driver); //Creating an Object for Actions class to handle mouse hover 
		action.moveToElement(element).build().perform(); // navigtes to desired element
	}
	

	//This method is to capture the screenshot and return the path of the screenshot.
	public static String takeScreenshot(String methodName) 
	{

		String destination = System.getProperty("user.dir") + "/FailedScreenshot/" + methodName + ExtentManager.getCurrentTime() + ".png";  //Failed screenshot is stored in folder, "FailedScreenshot"
		try {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //typecasting Takescreenshot interface with driver instance
			File finalDestination = new File(destination);
			FileUtils.copyFile(src, finalDestination);

		}catch (Exception e) {
			e.printStackTrace();
		}
		return destination;
	}

    //****************Have not used the following methods in this Project************
	public static void selectfromDropDown(WebElement element, String value) //Passing two parameters
	{
		Select select = new Select(element);    //creating an object for select class to handle dropdown values
		select.selectByVisibleText(value);      
        
	}

	public static void switchToFrame()   //Method to handle elements within a frame.
	{
		driver.switchTo().frame("frame"); //switchTo()function navigates to frame through "id", "index" or "WebElement"
	}

	public static void switchToDefaultPage() 
	{
		driver.switchTo().defaultContent();   //naviagtes to defaultcontent from a Frame.
	}

    public static void multipleWindows() //method for handling multipes windows
    {

		ids = driver.getWindowHandles();   //Captures all window ids

		for(String winId : ids) {
			driver.switchTo().window(winId);
		}
	}

	public static void parentWindow() {
		parentWinId = driver.getWindowHandle();
		multipleWindows();
		driver.switchTo().window(parentWinId);
	}

	public static String alertText()   //Method to handle JavaScript pop-ups such as alerts
	{
		return driver.switchTo().alert().getText(); //captures alert text
	}

	public static void alertAccept() {
		Alert alert =driver.switchTo().alert();
		alert.accept();     //clicks "Accept" or "Ok" in alert pop up
	}



}
