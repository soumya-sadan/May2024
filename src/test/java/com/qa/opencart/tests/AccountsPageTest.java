package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
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
	public void accPageTitleTest()
	{
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest()
	{
		String accURL = accPage.getAccountsPageURL();
		Assert.assertTrue(accURL.contains(AppConstants.ACCOUNTS_PAGE_FRACTION_URL));
	}
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	@Test
	public void AccPageHeadersCountTest()
	{
		int count = accPage.getHeaderCount();
	 //	Assert.assertEquals(count, 4);  OR
		Assert.assertEquals(count, AppConstants.ACCOUNTS_PAGE_LEFT_HEADING_COUNT);
	}
	
	@Test
	public void AccPageHeaderHeaderTest()
	{
		List<String> actualAccHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actualAccHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	@Test
	public void searchTitleTest()
	{
		searchResultsPage = accPage.doSearch("macbook");
		String searchTitle = searchResultsPage.getSearchPageTitle();
		//Assert.assertEquals(searchResultsPage.getSearchPageTitle(), AppConstants.SEARCH_RESULTS_PAGE_PARTIAL_TITLE);
		Assert.assertTrue(searchTitle.contains(AppConstants.SEARCH_RESULTS_PAGE_PARTIAL_TITLE));
	}
	
	@Test(dataProvider="getSearchKey")
	public void searchResultsCount(String searchKey, int resultsCount)
	{
		searchResultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getResultsCount(), resultsCount);
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},			
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"}
		};
	}
	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey, String productName) throws InterruptedException
	{
		Thread.sleep(500);
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.geProducttHeader(), productName);
		Thread.sleep(500);
	}
}
