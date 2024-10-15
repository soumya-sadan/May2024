package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class SearchResultsPageTest extends BaseTest {
	
	
	@BeforeClass
	
	public void accSetup()
	{
		//**************PAGE CHAINING STARTS HERE **************************
		// From here, it started creating zig-zag pattern. doLogin() giving account Page reference  variabe accPage 
		//from accounts page,  >> perform search, which results in results page> click on product, returns product info page, >>
		//from there enter add to cart, this results in add to cart page,>> then payement, >> and so on,.. 
		//so 5 pages are involved in this page chanining model and we can keep on adding pages as required.. 
		//Thus we can achieve end-to-end workflow with this pattern.
		//************************************** **************************
		
		//'accPage' is the landing page class's object returned by doLogin() method of LoginPage class
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void searchSetup()
	{
		searchResultsPage =	accPage.doSearch("mac");
	}
	
	@Test
  public void  SearchPageTitleTest()
  {
	 String searchTitle = searchResultsPage.getSearchPageTitle();
	 Assert.assertTrue(searchTitle.contains(AppConstants.SEARCH_RESULTS_PAGE_PARTIAL_TITLE), searchTitle);
  }
	

}
