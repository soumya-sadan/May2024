package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;


public class RegisterPageTest extends BaseTest {
	private static int counter = 0;
	
	@BeforeClass
	public void regSetup()
	{
		registerPage = loginPage.navigateToRegisterPage();
	}
	
		
	public String generateEmail() {
		
		    counter++;
		    return "uiautomation" + System.currentTimeMillis() + counter + "@gmail.com";
	}
	
	
	
	
	/*
	 * @DataProvider public Object[][] getNewUsersData() { return new Object[][] {
	 * {"Priya", "pal", generateEmail(), "9494485503", "sts@1984", "sts@1984",
	 * "No"}, {"Naveen", "sir", generateEmail(), "9497445503", "sts@1984",
	 * "sts@1984", "Yes"}, {"adwaith", "baby", generateEmail(), "9497454503",
	 * "sts@1984", "sts@1984", "Yes"},
	 * 
	 * }; }
	 */
	

	/*
	 * @Test(dataProvider="getNewUsersData") 
	 * public void userRegisterTest(String
	 * fname, String lname, String email, String telphone, String password, String
	 * confirmPassword, String subscribe) 
	 * {
	 * 	 * Boolean success = registerPage.registerAccount(fname, lname, email, telphone,
	 * password, confirmPassword, subscribe); Assert.assertTrue(success); //OR
	 * //Assert.assertTrue(registerPage.registerAccount("Soumya", "TS",
	 * "soumyats2021@gmail.com", "9497685503", "sts@1984", "sts@1984", "Yes")); }
	 */
	
	@DataProvider
	public Object[][] getRegisterdatafromExcel()
	{
		return ExcelUtil.getExcelTestData(AppConstants.REG_SHEET_NAME);
		
	}
	
	@Test(dataProvider="getRegisterdatafromExcel")
	public void userRegisterTest(String firstname, String lastname, String telphone, String password, String subscribe)
	{
		
		Boolean success = registerPage.registerAccount(firstname, lastname, generateEmail(), telphone, password,subscribe);
		Assert.assertTrue(success);
				//OR
		//Assert.assertTrue(registerPage.registerAccount("Soumya", "TS", "soumyats2021@gmail.com", "9497685503", "sts@1984", "sts@1984", "Yes"));
	}
	
	
	@AfterMethod
    public void navigateBackToLoginPage() 
	{
		// Navigate back to the login page after each test method execution
		// This method should ensure the navigation to the login page
		 registerPage.doLogout();		
            try {
            registerPage = loginPage.navigateToRegisterPage();
        	String title=registerPage.getRegisterPageTitle();
        	System.out.println(title);      	
     
            	} catch (Exception e) {
            		System.out.println("Failed to navigate back to the login page: " + e.getMessage());
            		}
	}
}



