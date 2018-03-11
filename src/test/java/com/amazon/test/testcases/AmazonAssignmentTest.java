package com.amazon.test.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.test.base.TestBase;
import com.amazon.test.pages.HomePage;
import com.amazon.test.pages.LoginPage;
import com.amazon.test.pages.ProductPage;
import com.amazon.test.pages.SearchResultsPage;
import com.amazon.test.util.TestUtil;

public class AmazonAssignmentTest extends TestBase {
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	ProductPage productPage;
	LoginPage loginPage;
	
		public AmazonAssignmentTest() {
			super();
		}
		
		@BeforeTest
		public void setUp() {
			initialization();
			homePage = new HomePage();
		}
		
		@Test(priority=1)
		public void homePageTitleTest() {
			try {
			String title = homePage.verifyHomepageTitle();
			Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
			TestUtil.printResult("Pass", 1);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 1);
			}
		}
		
		@DataProvider
		public Object[][] getSearchTestData(){
			String sheetName = "SearchProduct";
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
		}
		
		@Test(priority=2, dataProvider="getSearchTestData")
		public void searchTest(String searchItem) {
			try {
			searchResultsPage = homePage.searchProduct(searchItem);
			TestUtil.printResult("Pass", 2);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 2);
			}
		}
		
		@Test(priority=3)
		public void validateSearchInputTest() {
			try {
				String value = searchResultsPage.verifySearchInput();
				Assert.assertEquals(value, "pen drive");
				TestUtil.printResult("Pass", 3);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 3);
			}
			
		}
		
		@DataProvider
		public Object[][] getBrandTestData() {
			String sheetName = "BrandDetails";
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
		}
		
		@Test(priority=4, dataProvider="getBrandTestData")
		public void validateFilteredBrandTest(String brandNameCheck) {
			try {
			String brandName = searchResultsPage.clickOnBrandCheckbox(brandNameCheck);
			Assert.assertEquals(brandName, "SanDisk");
			TestUtil.printResult("Pass", 4);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 4);
			}
		}
		
		@DataProvider
		public Object[][] getPriceRangeTestData() {
			String sheetName = "PriceRange";
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
		}		
		
		@Test(priority=5, dataProvider="getPriceRangeTestData") 
		public void validateFilteredPriceRangeTest(String min, String max) {
			try {
			String priceRange = searchResultsPage.enterPriceRange(min, max);
			Assert.assertEquals(priceRange, "₹300 - ₹1,000");
			TestUtil.printResult("Pass", 5);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 5);
			}
		}
		
		@DataProvider
		public Object[][] getRatingFilterTestData() {
			String sheetName = "Ratings";
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
		}	
		
		@Test(priority=6, dataProvider="getRatingFilterTestData")
		public void validateFilteredRatingTest(String avgRating) {
			try {
			String ratings = searchResultsPage.clickOnAvgCustomerRev(avgRating);
			Assert.assertEquals(ratings, "4 Stars & Up");
			TestUtil.printResult("Pass", 6);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 6);
			}
		}
		
		@Test(priority=7)
		public void validateCheapestSelectionTest() {
			try {
			productPage = searchResultsPage.selectCheapestProduct();
			TestUtil.printResult("Pass", 7);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 7);
			}
		}
		
		@Test(priority=8)
		public void validateBuyNowTest() {
			try {
			loginPage = productPage.clickForBuy();
			TestUtil.printResult("Pass", 8);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 8);
			}
		}
		
		@Test(priority=9)
		public void validateLoginPageTitleTest() {
			try {
			String lTitle = loginPage.verifySignInPageTitle(); 
			Assert.assertEquals(lTitle, "Amazon Sign In");
			TestUtil.printResult("Pass", 9);
			}
			catch (Exception e) {
				TestUtil.printResult("Fail", 9);
			}
		}			
		
		@AfterTest
		public void tearDown(){
			driver.quit();
		}

	}
