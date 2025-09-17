package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opemcart.constants.AppConstants;
import com.qa.opencart.base.BaseTest;



public class AccountsPageTest extends BaseTest {

	@BeforeClass 
	public void accPageSetUp() {
		accPage =	loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); 

	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}
	
	@Test
	public void accPageHeadersTest() {
		List<String>actHeadersList= accPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actHeadersList, AppConstants.expectedAccPageHeadersList);
	}

}
