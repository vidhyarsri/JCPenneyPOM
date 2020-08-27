package com.jcpenny.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	//***************This class defines the  pre-conditions and post-conditions for generating Extent report************************

	public static ExtentHtmlReporter htmlReporter;  //ExtentHtmlReporter class creates a standalone HTML file. Helps with look and feel of a report. Like, report name, document title, theme etc..
	public static ExtentReports extent; //Created object of ExtentReports class. This class will create report
	public static ExtentTest extentTest;  //ExtentTest class is used to update the logs such as fail, pass and skip
	
	public static void startReport() 
	{
		
		htmlReporter = new ExtentHtmlReporter("./HtmlReport/extent" + getCurrentTime()+".html" );//generates report under folder "HtmlReport", alonmg with current date and time 
		htmlReporter.config().setReportName("JCPenny Automation Test Report");  //Title of the report
		htmlReporter.config().setDocumentTitle("Automation Test Report"); //Name of the Report
		htmlReporter.config().setTheme(Theme.DARK); //Dark Theme

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("ProjectName", "JCPenny");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Sri");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public static void endReport() 
	{
		extent.flush();  //This method is used to erase any previous data on the report and create a new report
	}
	
	public static String getCurrentTime() //method to retrieve current date 
	{
		String currentDate = new SimpleDateFormat("--yyyy-MM-dd-(hhmmss)").format(new Date());
		return currentDate;
	}

}
