# JCPenneyPOM


Automation of Web Application using POM(PageFactory)+Selenium WebDriver(Java)+TestNG+Log4j+ExtentReport

This is a sample project to demonstrate testing of JCPenney web application. Test cases under "TestCases folder", are written in excel, and based on it, demonstrated the following concepts.
Data Driven Framework with Page Object Model, Page Factory and how it is used with Selenium WebDriver, Java OOPs concepts, Maven and TestNG functions. 
Implemented Log4j (in "log" folder) to trace logs and to track information. 
Implemented ITestListener interface to generate HTML Extent report with results such as pass/fail/skip. Took screenshots of failed test cases and attached it to the report. 

The automation routine performs the following actions.
Verifies the color, font, and size of the JCPenney logo.
Uses the Actions class to mouse hover and selects "Home Electronics" option from "Home & Lifestyle" dropdown menu.
Selects "Kids Tech" web element.
Selects a product from the Kids Electronics.
Adds the product to the cart and proceeds to checkout.
Verifies the product is added to the cart.
Deletes the product and verifies if the product has been removed from the cart.
