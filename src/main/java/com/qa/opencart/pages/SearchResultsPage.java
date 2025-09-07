package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opemcart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;



	//public  constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resultsHeader = By.cssSelector("div#content h1");

	public int getSearchResultsCount() {
		int resultCount = 
				eleUtil.waitForElementsPresence(searchResults,AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("results count---> " + resultCount);
		return resultCount;
	}

	public String getResultsHeadervalue() {
		String header =eleUtil.doElementGetText(resultsHeader);
		System.out.println("results Headers---> "+ header);
		return header;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("poduct name ---> "+ productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
