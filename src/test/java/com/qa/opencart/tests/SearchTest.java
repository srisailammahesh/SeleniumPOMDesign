package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	//BT(Chrome+URL) --> BC(LOGIN) -->@TEst
	@BeforeClass
	public void searchSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); 
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String accHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(accHeader, "MacBook Pro");
				
	}

}
