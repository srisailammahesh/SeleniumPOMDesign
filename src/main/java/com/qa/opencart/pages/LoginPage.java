package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opemcart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	//private By Locators: Page objects
	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	
	private final By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	

	private static final Logger log =LogManager.getLogger(LoginPage.class);

	//public  constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//public page methods/actions
	@Step("getting Login page title...")
	public String getloginPageTitle() {
		String title = 	eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page title: " + title);
		log.info("Login page title: " + title);
		return title;
	}
	@Step("getting Login page URL...")
	public String getloginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page URL: " + url);
		log.info("Login page URL: " + url);
		return url;
	}
	
	@Step("Forgot psw Link Exist..")
	public boolean isForgotPwdlinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("Header Exist..")
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(header);
				
	}
	
	@Step("Login with Username {0} and password {1}")
	public AccountsPage doLogin(String appUsername, String appPassword) {
		//System.out.println("Application Credentials: "+ appUsername + ":" +appPassword);
		log.info("Application Credentials: "+ appUsername + ":" + "*********");
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appUsername);
		eleUtil.doSendKeys(password, appPassword);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	
	@Step("login with in-correct username: {0} and password: {1}")
	public boolean doLoginWithInvalidCredentails(String invalidUN, String invalidPWD) {
		log.info("Invalid application credentials: " + invalidUN + " : " + invalidPWD);
		WebElement emailEle = eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUN);
		eleUtil.doSendKeys(password, invalidPWD);
		eleUtil.doClick(loginBtn);
		String errorMessg = eleUtil.doElementGetText(loginErrorMessg);
		log.info("invalid creds error messg: " + errorMessg);
		if (errorMessg.contains(AppConstants.LOGIN_BLANK_CREDS_MESSG)) {
			return true;
		}
		else if (errorMessg.contains(AppConstants.LOGIN_INVALID_CREDS_MESSG)) {
			return true;
		}
		return false;
	}
	
	@Step("Navigating to ResterPage")
	public RegisterPage navigateToRegisterPage() {
		log.info("tring to navigate to register Page");
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegisterPage(driver);
	}

}
