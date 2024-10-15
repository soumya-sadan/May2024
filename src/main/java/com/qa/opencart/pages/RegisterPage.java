package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By LastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']"); 
	//private By subscribeYes = By.xpath("//div[@class=\"col-sm-10\"]/label/input[1]");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']"); 
	private By policyAgreeCheckbox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueButton = By.xpath("//input[@value='Continue']");
	
	private By success = By.cssSelector("div.container h1");
	private By logout = By.linkText("Logout");
	private By RegisterLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");
	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
				
	}
	
	public String getRegisterPageTitle()
	{
		return eleUtil.waitForTitleContainsAndReturn(AppConstants.REGISTER_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
	}

	
	public boolean registerAccount(String fname, String lname, String email, String telphone, String password, String subscribe)
	{
		eleUtil.waitForElementPresence(firstName, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(fname);
		eleUtil.doSendKeys(LastName, lname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(telephone, telphone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(policyAgreeCheckbox);
		eleUtil.doClick(continueButton);
		
		String successMsg = eleUtil.waitForElementVisible(success, AppConstants.DEFAULT_LONG_TIMEOUT).getText();
		//System.out.println(successMsg);
		
		if(successMsg.contains(AppConstants.ACCOUNT_REGISTER_SUCCESS_MESG))
		{
			System.out.println("Registered successfully");
			return true;
		}
	    else
	    {
	    	System.out.println("Registered failed");
		return false;
	    }
	
  		
}
	
	public void doLogout()
	  {
		eleUtil.waitForElementVisible(logout, AppConstants.DEFAULT_SHORT_TIMEOUT);
		 eleUtil.doClick(logout);
		  System.out.println("loggedout.");
	  }
	
	public void navigateToRegisterPage() {
	   eleUtil.waitForElementVisible(RegisterLink, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
	}
	
}
