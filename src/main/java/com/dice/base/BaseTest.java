package com.dice.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected WebDriver driver;
	protected Logger log;
		
	@BeforeClass(alwaysRun = true)
	protected void setUpClass(ITestContext ctx ) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);
	}
	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	protected void methodSetUp(String browser) throws Exception {
		log.info("Method set up");
		driver = BrowserFactory.getDriver(browser, log);
	}
	
	@AfterMethod(alwaysRun = true)
	protected void methodTearDown() {
		log.info("Method tear down");
		driver.quit();
	}
	
}
