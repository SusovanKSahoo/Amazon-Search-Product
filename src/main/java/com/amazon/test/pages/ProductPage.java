package com.amazon.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.test.base.TestBase;

public class ProductPage extends TestBase {

	@FindBy(name="submit.buy-now")
	WebElement buyNowButton;
	
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage clickForBuy() {
		buyNowButton.click();
		return new LoginPage();
	}
}
