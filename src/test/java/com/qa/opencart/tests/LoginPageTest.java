package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.AnnotationTransformer;

@Listeners({ExtentReportListener.class, AnnotationTransformer.class})
public class LoginPageTest extends BaseTest {
	
	// PLEASE NOTE : TESTNG class (this class) will not have any Selenium code. Just call the page classes and keep Asserting in this Test Class
	
	
	
	// Page class should not have any hardcoded data. Everything should be pure methods and parameters passed. 
	//data to be supplied from the user side, i.e Test class/Test methods
	// Data is supplied to the Test methods by DataProviders.
	@Test
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPasswordLinkTest()
	{
		
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test
	public void isLogoExistTest()
	{
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest()
	{
		//String accPageTitle = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		//PAGE CHAINING MODEL (ZIG - ZAG PATTERN)
		// METHOD UPDATED TO RECEIVE accPage (Accountspage's object) RETURNED BY doLogin() from  LoginPage class
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		//Assert.assertEquals(accPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
}
