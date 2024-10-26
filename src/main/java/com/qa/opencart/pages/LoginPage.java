package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	// PLEASE NOTE : Page classes (this class) will not have any TESTNG code and vice versa
	// Maintain "By locatosr, constructors and page actions here. No assertions should be done in page class

	// 1. maintain private webdriver declaration (initialization will be in
	// pagefactory class)
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. Maintain private 'By Locators': Page Objects

	private By usernameInput = By.id("input-email");
	private By passwordInput = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']"); 
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logoImg = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	// 3. Maintain Public Page Constructors
	// When methods of this class are called, object need to be created
	// from the calling class. The below Constructor will be invoked
	// at that time and driver will be passed.

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 4. Maintain Public page Actions/Methods
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Heloooo.... Login page title is: " + title);
		return title;
	}
	
	public String getLoginPageURL()
	{	
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("URL of Login page is: " + url);
		return url;
	}
	
	public boolean isLogoExist()
	{
		return driver.findElement(logoImg).isDisplayed();
	}
	
	public boolean isForgotPasswordLinkExist()
	{
		return eleUtil.isElementDisplayed(forgotPwdLink);		
	}
	
	public AccountsPage doLogin(String userName, String pwd) {
		
		eleUtil.waitForElementVisible(usernameInput,AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(userName);	
		eleUtil.doSendKeys(passwordInput, pwd);
		eleUtil.doClick(loginBtn);
		
		//PAGE CHAINING MODEL (after login, account page will be loaded. 
		//so this method should return accountpage's object which will be received 
		//by other methods that calls this method like accSetup() method from AccountsPageTest class
		// and loginTest() from LoginPageTest class
		
		
		return new AccountsPage(driver);
		
		//String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		//return title;
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
