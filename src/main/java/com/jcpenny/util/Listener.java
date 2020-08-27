package com.jcpenny.util;

import java.io.IOException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.jcpenny.base.TestBase;


public class Listener extends ExtentManager implements ITestListener 
{
	//ITestListener from TestNG is an interface, which "listen" to the event defined in the scripts and behave accordingly.
	//All the methods of an interface are abstract methods. An interface cannot be instantiated. It can be accessed ONLY through inheritence by using the keyword "implements", which Overrides all the abstract methods to child class. 
	//This class inherits the values and properties of "ExtentManager" class by using the keyword "extends"
	
   
	
	@Override
	public void onTestStart(ITestResult result) //"onTestStart" method is called when any test starts
	{
		extentTest = extent.createTest(result.getName()); //Makes entries for each test case in the report. Parameter "result" gets the name of every test, through the method "getName"
		TestBase.logger.info("*****Test method " + result.getName()+ " has started"); // "TestBase.logger"---calling static variable "logger" from TestBase class
	}

	@Override
	public void onTestSuccess(ITestResult result) //This method is called on the success of any test
	{
           extentTest.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));  //MarkupHelper is used to display the output in different colors for PASS/ FAIL OR SKIP
           TestBase.logger.info("*****Test method " + result.getName()+ " has executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) //This method is invoked when a test fails and captures a screenshot which gets attached to extent report along with Exceptions
	{

		TestBase.logger.info("*****Test method " + result.getName()+ " has Failed. Find failed screenshot under \"FailedScreenshot\" folder");//A screenshot of failed test case is saved under folder "FailedScreenshot"
		TestBase.logger.info("*****Failure reason:Assertion::" + result.getThrowable()); //prints failure reason in log4j file
		
		extentTest.log(Status.FAIL, "TEST CASE FAILED IS:  " + result.getName()); //prints the name of failed test case in the report
		extentTest.log(Status.FAIL, "Assertion: " + result.getThrowable()); // prints Assertion of failed test in the report

		//takeScreenshot is a static method in TestUtil class. Static methods can be invoked through its class name, without an object 
		String screenshotPath = TestUtil.takeScreenshot(result.getName());   //To capture screenshot path and store the path of the screenshot in string "screenshotPath". 
		try {
			extentTest.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",  
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()); //passing the path captured in previous line into the extent reports using "addScreenCapture" method.
		} catch (IOException e) {
			extentTest.fail("Test failed cannot attach screenshot"); //In case of exception, catch block will handle it, without failing the scripts
		}
     }
	
	@Override
	public void onTestSkipped(ITestResult result) //This method is invoked when a test is skipped
	{
		TestBase.logger.info("*****Test method " + result.getName()+ " has been skipped");
		extentTest.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.YELLOW));
	}	

}





