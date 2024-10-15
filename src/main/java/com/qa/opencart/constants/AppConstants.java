package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_SHORT_TIMEOUT = 5;
	public static final int DEFAULT_MEDIUM_TIMEOUT = 10;
	public static final int DEFAULT_LONG_TIMEOUT = 20;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";	
	public static final String SEARCH_RESULTS_PAGE_FRACTION_URL= "?route=product/search&search=";
	
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";	
	public static final String ACCOUNTS_PAGE_FRACTION_URL = "route=account/account";
	public static final int ACCOUNTS_PAGE_LEFT_HEADING_COUNT = 4;
	
	public static final String SEARCH_RESULTS_PAGE_PARTIAL_TITLE = "Search -";
	
	public static final String PRODUCT_INFO_PAGE_FRACTION_URL = "product/product&product_id=";
	
	// List.of() creates an immutable list. List is interface. of() is method.
	public static List<String> EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST =List.of("My Account", "My Orders", "My Affiliate Account","Newsletter");
	
	public static final String ACCOUNT_REGISTER_SUCCESS_MESG = "Your Account Has Been Created!";
	
	public static final String REG_SHEET_NAME = "register";

}


