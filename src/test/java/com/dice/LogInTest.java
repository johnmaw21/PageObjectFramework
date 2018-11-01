package com.dice;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProvider;
import com.dice.pages.LogInPage;
import com.dice.pages.ProfilePage;

public class LogInTest extends BaseTest {

	@Test(priority = 1, groups = { "positive" })
	public void positiveLogInTest() {
		LogInPage logInPage = new LogInPage(driver, log);
		String expectedPageTitle = "Seeker Dashboard - Profile";
		String correctProfileName = "John Maw";

		// Open dice login page - https://www.dice.com/dashboard/login
		logInPage.openLogInPage();

		// Fill up email and password
		logInPage.fillUpEmailAndPassword("johnmaw21@yahoo.co.uk", "Shald0n123");

		// Push Sign In button and wait for profile page to load
		ProfilePage profilePage = logInPage.pushSignInButton();
		profilePage.waitForProfilePageToLoad();

		// Verifications
		log.info("Verifications");
		// - Verify Title of the page is correct - Seeker Dashboard - Profile
		String actualTitle = profilePage.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualTitle),
				"Page Title is not as expected.\nExpected: " + expectedPageTitle + "\nActual: " + actualTitle + ".");

		// - Verify correct name on profile page
		Assert.assertTrue(profilePage.isCorrectProfileLoaded(correctProfileName), "Profile Name is not as expected.");
	}
	
	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = { "negative" } )
	public void negativeLogInTest(Map<String, String> testData) {
		String expectedErrorMessage = "Email and/or password incorrect.";
		String testNumber  = testData.get("no");
		String email       = testData.get("email");
		String password    = testData.get("password");
		String description = testData.get("description");
		
		log.info("Test No #" + testNumber + " for " + description + "  log.infowhere\nEmail: " + email + "\nPassword : "
				+ password);
		
		LogInPage logInPage = new LogInPage(driver, log);

		// Open dice login page - https://www.dice.com/dashboard/login
		logInPage.openLogInPage();

		// Fill up email and password
		logInPage.fillUpEmailAndPassword(email, password);

		// Push Sign In button
		
		logInPage.pushSignInButton();
		
		String errorMessage = logInPage.getLoginErrorMessage();
		Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
				"Error Message is not as expected.\nExpected: " + expectedErrorMessage + "\nActual: " + errorMessage + ".");
		
	}
}
