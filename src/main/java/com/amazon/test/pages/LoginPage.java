package com.amazon.test.pages;

import com.amazon.test.base.TestBase;

public class LoginPage extends TestBase {
	
	public String verifySignInPageTitle() {
		return driver.getTitle();
	}
	
}
