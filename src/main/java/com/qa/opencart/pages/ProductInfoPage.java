package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//maintain By locators for Product Info page
	By productInfoTabs = By.cssSelector("ul.nav-tabs li a");
	By productHeader = By.tagName("h1");
	
	By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
	By productPricingData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");
	
	
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String geProducttHeader()
	{
		String header = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
		System.out.println("Product header : " + header);
		return header;
	}
	public String getPageURL()
	{
		return eleUtil.waitForURLContainsAndReturn(AppConstants.PRODUCT_INFO_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
	}
	
	public List<WebElement> getProductInfoTabs()
	{
		List<WebElement> productTabs = eleUtil.waitForElementsVisible(productInfoTabs, AppConstants.DEFAULT_SHORT_TIMEOUT);
	    return productTabs;
	}
	
	//Brand: Apple
	//Product Code: Product 16
	//Reward Points: 600
	//Availability: In Stock
	
	private void getProductMetaData()
	{
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		
		for(WebElement e : metaList)
		{
			String metaText = e.getText();
			String[] metaData = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
			
		}
	}
	
	//$602.00
	//Ex Tax: $500.00
	private void getProductPriceData()
	{
		List<WebElement> priceList = eleUtil.getElements(productPricingData);
		String price = priceList.get(0).getText().trim();
		String ExTaxprice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("Product Price", price);
		productMap.put("Ex Tax Price", ExTaxprice);
		
		
	}
	
	public Map<String, String> getProductData()
	{
		productMap = new HashMap<String,String>();
		productMap.put("Product Header", geProducttHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Info: " + productMap);
		return productMap;
	}
	
}
