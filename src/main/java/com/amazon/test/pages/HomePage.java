package com.amazon.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.test.base.TestBase;

public class HomePage extends TestBase {
	@FindBy(id="twotabsearchtextbox")
	WebElement searchButton;
	

	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomepageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public SearchResultsPage searchProduct(String searchFor) {
		searchButton.sendKeys(searchFor+Keys.ENTER);
		return new SearchResultsPage();
	}
	
}
