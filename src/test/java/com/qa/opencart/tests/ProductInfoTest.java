package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{
	//BT(Chrome+URL) --> BC(LOGIN) -->@TEst
	@BeforeClass
	public void ProductInfoSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); 
	}
	
	@DataProvider
	public Object[] [] getProducts(){
		return new Object[] [] {
			{"macbook" , "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"imac", "iMac"},
			{"canon" , "Canon EOS 5D"}
		};
		
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return ExcelUtil.getTestData("product");
	}
	
	@Test(dataProvider ="getProductTestData" )
	public void productHeaderTest(String searchKey , String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String accHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(accHeader, productName);			
	}
	
	@DataProvider
	public Object[] [] getProductsImages(){
		return new Object[] [] {
			{"macbook" , "MacBook Pro", 4},
			{"samsung", "Samsung SyncMaster 941BW" , 1},
			{"canon" , "Canon EOS 5D" ,3}
		};
		
	}
	
	@Test(dataProvider = "getProductsImages" )
	public void productImagesCountTest(String searchKey , String productName ,int imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImages();
		Assert.assertEquals(actImagesCount, imageCount);			
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDataMap = productInfoPage.getProductData();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(productDataMap.get("productname"), "MacBook Pro");
		
		softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");

		softAssert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productDataMap.get("extaxprice"), "$2,000.00");

		softAssert.assertAll();//7 --> 1 failed
	}
}
