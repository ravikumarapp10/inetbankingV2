package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Enter username");
		lp.setPassword(pwd);
		logger.info("Enter password");
		lp.clickSubmit();
		logger.info("Clicked on submit button");
		
		if(isAlertPerson()==true) {
			driver.switchTo().alert().accept();//close alert
			logger.info("Switch to alert popup");
			driver.switchTo().defaultContent();
			logger.info("Switch to main screen");
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			logger.info("Clicked on logout button");
			driver.switchTo().alert().accept();//close logout alert
			logger.info("Switch to alert popup");
			driver.switchTo().defaultContent();
			logger.info("Switch to main screen");
		}
	}
	
	//user defined method created to check alert is present of not
	public boolean isAlertPerson() {
		try {
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e) {
			return false;
		}		
	}
	
	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for(int i = 1; i <= rownum; i++) {
			for(int j = 0; j < colcount; j++ ) {
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
	

}
