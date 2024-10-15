package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	
	
	@BeforeClass
	public void productInfoSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("username"));
	}
	
	@Test
	public void productPageHeaderTest()
	{
		searchResultsPage = accPage.doSearch("macbook");		
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.geProducttHeader(), "MacBook Pro");
	
	}
	
	@Test
	public void productInfoTest()
	{
		searchResultsPage = accPage.doSearch("macbook");		
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map <String,String> actualproductMap = productInfoPage.getProductData();
		softAssert.assertEquals(actualproductMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualproductMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualproductMap.get("Reward Points"), "800");
		softAssert.assertEquals(actualproductMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actualproductMap.get("Product Price"), "$2,000.00");
		softAssert.assertEquals(actualproductMap.get("Ex Tax Price"), "$2,000.00");
		softAssert.assertAll();
		//Assert.assertEquals(actualproductMap.get("Product Header"), "2,000.00");
		
		
		
	}
}
