package com.dice.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BrowserFactory {

	public static WebDriver getDriver(String browser, Logger log) throws Exception {
		WebDriver driver;

		// if set as an Environment Variable then SauceLabs Login and Access Key can be hidden
		final String Username = "johnmaw21";
		final String AccessKey = "6fedea4a-5dd6-4f18-a22e-81398ddf4fee";
		final String sauceLabsHub = "https://" + Username + ":" + AccessKey + "@ondemand.saucelabs.com:443/wd/hub";
		
		log.info("Starting " + browser + " driver.");
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
			driver = new FirefoxDriver();
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");	
			driver = new ChromeDriver();
			break;

		case "saucelabs":
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "Windows 10");
			caps.setCapability("version", "59.0");
			driver = new RemoteWebDriver(new URL(sauceLabsHub), caps);
			break;
			
		default:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}

}
