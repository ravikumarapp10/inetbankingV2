package com.inetbanking.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.Reporting;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
			
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickSubmit();
		logger.info("Button clicked");
		
		if(driver.getTitle().equalsIgnoreCase("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test pass");
		}else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test fail");
		}	
	}
}