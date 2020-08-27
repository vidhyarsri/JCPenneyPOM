package com.jcpenny.base;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jcpenny.util.ExtentManager;
import com.jcpenny.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase{

	//Variables are declared globally (at class level), so all the methods can access it
	//All global variables are declared as static. Static Keyword belongs to the Class. Can be invoked without an object
	public static WebDriver driver;  // Created Webdriver reference
	public static Properties prop;  //Created Properties class object to read properties file
	public static Logger logger;    //log4j-Logger class captures info/activities at the time of program execution, using logs such as, info, warn, error and fatal

	
	@BeforeSuite //Annotation of TestNG(testing framework).This method will run before the execution of all the test methods in the suite.
	public void beforeSuite() {
		ExtentManager.startReport(); //calling method "startReport" from "ExtentManager" class to create an extent report
	}

	@AfterSuite  //The @AfterSuite annotated method will run after the execution of all the test methods in the suite  
	public void afterSuite() {
		ExtentManager.endReport();  //calling method "endReport" from "ExtentManager" class
	}


	public void initialization()
	{
		logger = Logger.getLogger("JCPenny Test");  //getLogger method- Retrieves a logger named according to the value of the name in parameter("JCPenny Test")
		PropertyConfigurator.configure("Log4j.properties"); //A Log report is generated under "log" folder, based on the configurations specified in Log4j.properties file
		
		try {
			prop =new Properties();   //Creating an object for Properties class to read data in config.properties file.
			FileInputStream file = new FileInputStream("./Configuration/config.properties");//FileInputStream class to specify path for properties file.
			prop.load(file); //Integrate Properties class with FileInputStream path
		}catch (Exception e) {
			System.out.println("Exception is: "+ e.getMessage());//In case of failure, the fail message is captured here without failing other scripts.
		}

		String browserName =prop.getProperty("browser"); //"getProperty("browser")", gets browser information from config.properties file, and stores the value in variable "browserName"
		
		if(browserName.equalsIgnoreCase("firefox"))   //method to open firefox browser
		{
			WebDriverManager.firefoxdriver().setup(); //WebDriverManager manages the binary version (.exefiles) of browsers
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")) //method to open chrome browser
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else {
			System.out.println("Sorry browser not supported");
		}
		
		logger.info(browserName+ " browser is opened.");
		
		logger.info("Maximize Browser");
		driver.manage().window().maximize();  //maximize the browser
		
		logger.info("Apply \"Pageload\" and \"Implicit wait\"");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);  //pageLoadTimeout sets an amount of time to wait for a page to load before throwing an error
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS); //ImplicitWait is applied globally for all the web elements.
		   //Both PAGE_LOAD_TIMEOUT and IMPLICIT_WAIT are static variables in TestUtil class. Static Keyword belongs to the class. It can be invoked through its class name and not through an object
	    
		logger.info("Navigate to Url");
		driver.manage().deleteAllCookies();   //delete cookies
		driver.get(prop.getProperty("url")); //getProperty(), retrieves url from config.properties file
		
		
		
	}

	public  void quit(){
		driver.quit(); //quits the whole browser session and its associated windows, tabs and pop-ups
		logger.info("Browser closed");
	}



}
