package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.xpath("//div[@id='content']/h2");
	private By search = By.name("search");
	private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");
	
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		//driver.findElement(RelativeLocator.with(By.tagName("inpit")).bel)
	}
	
	public String getAccountsPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE,AppConstants.DEFAULT_LONG_TIMEOUT);
		System.out.println("Accounts page title is :: " + title);
		return title;
	} 
	public String getAccountsPageURL()
	{	
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.ACCOUNTS_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("URL of Accounts page is: " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.isElementDisplayed(logoutLink);		
	}
	
	public boolean isMyAccountLinkExist()
	{
		return eleUtil.isElementDisplayed(myAccountLink);		
	}
	public List<String> getAccPageHeaders()
	{ 
		List<WebElement> allHeaders = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<String> headerList = new ArrayList<String>();
		for(WebElement e : allHeaders)
		{
			String headers= e.getText();
			System.out.println(headers);
			headerList.add(headers);
		}
		return headerList;
	}
	
	public int getHeaderCount()
	{
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		WebElement searchTxt = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_LONG_TIMEOUT);
		eleUtil.doSendKeys(searchTxt, searchKey);	
		eleUtil.doClick(searchButton);	
		return new SearchResultsPage(driver);
	}


}
