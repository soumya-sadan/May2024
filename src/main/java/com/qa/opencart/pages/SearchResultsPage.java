package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By searchInput = By.cssSelector("input[placeholder='Search']");
	By resultsCount = By.cssSelector("div.product-thumb");
	By searchHeading = By.cssSelector("div#content h1");
	By macBookAirLink = By.xpath("//a[normalize-space()='MacBook Air']");
	
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getSearchPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.SEARCH_RESULTS_PAGE_PARTIAL_TITLE,AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Search page title is: " + title);
		return title;
	} 
	

  public String getSearchHeader()
  {
	 String searchHeader = eleUtil.waitForElementVisible(searchHeading, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	 return searchHeader;
	  
  }
  
	public int getResultsCount()
	{
		int searchResultsCount = eleUtil.waitForElementsVisible(resultsCount, AppConstants.DEFAULT_LONG_TIMEOUT).size();
		System.out.println("Results count: " + searchResultsCount);
		return searchResultsCount;
		
	}
	public ProductInfoPage selectProduct(String productName)
	{
		//eleUtil.waitForElementVisible(macBookAirLink, AppConstants.DEFAULT_LONG_TIMEOUT).click();
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	//public WebElement searchAndValidateHeading(String keyword)
//	{
		
	//	eleUtil.waitForElementVisible(searchInput, AppConstants.DEFAULT_SHORT_TIMEOUT);
	//	eleUtil.doActionsSendKeysAndEnter(searchInput, "mac");
	//	System.out.println("success");
//}
}
