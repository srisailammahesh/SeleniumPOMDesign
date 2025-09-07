package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opemcart.constants.AppConstants;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: Design open cart login feature")
@Story("US-50: Develop login Core feature: Title, URL,User is able to Login ")
public class LoginPageTest extends BaseTest{
	@Description("Login Page title Test")
	@Owner("Srisailam Mahesh")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actTitile = loginpage.getloginPageTitle();
		ChainTestListener.log("Login page Title: " + actTitile);
		Assert.assertEquals(actTitile,AppConstants.LOGIN_PAGE_TITLE);
	}
	@Description("Login Page URL Test")
	@Owner("Srisailam Mahesh")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest() {
		String actURL = loginpage.getloginPageURL();
		ChainTestListener.log("Login page URL: " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	
	@Description("PWD Link Test")
	@Owner("Srisailam Mahesh")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgotPwdlinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdlinkExist());
	}
	@Description("Login Page HEader Test")
	@Owner("Srisailam Mahesh")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isLoginPageHeaderExistTest() {
		Assert.assertTrue(loginpage.isheaderExist());
	}
	@Description("User is able to Login Page Test")
	@Owner("Srisailam Mahesh")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {

		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));      
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
