package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest{

	//BT(chrome+Login URL) --> BC(Move to register page)-->@Test

	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginpage.navigateToRegisterPage();
	}
	@DataProvider
	public Object[] [] getRegData() {
		return new Object [] [] {
			{"MaheshBhavishya" , "automation" , "123456987" , "mahesh2123" , "yes"},
			{"Bhavishya" , "automation" , "123458987" , "mini2123" , "no"},
			{"MaheshMadhulika" , "automation" , "103456987" , "bhavishy@2123" , "yes"},
		};
		
	}
	
	@DataProvider
	public Object [] [] getRegSheetData(){
		return ExcelUtil.getTestData("register");
	}
	
	@DataProvider
	public Object [] [] getRegCSVData(){
		return CsvUtil.csvData("register");
	}


	@Test(dataProvider = "getRegCSVData")
	public void registerTest (String firstName ,String lastName, String telephone , String password , String subscribe ) {
		Assert.assertTrue(registerPage.userRegister(firstName, lastName, StringUtils.getRandomEmail(), telephone , password, subscribe));
	}


}
