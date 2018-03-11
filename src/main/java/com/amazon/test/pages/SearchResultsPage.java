package com.amazon.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.test.base.TestBase;

public class SearchResultsPage extends TestBase {

	@FindBy(id="twotabsearchtextbox")
	WebElement searchInput;
	@FindBy(name="low-price")
	WebElement minPrice;
	@FindBy(name="high-price")
	WebElement maxPrice;
	@FindBy(xpath="//input[@type='submit' and @value='Go' and @class='a-button-input']")
	WebElement buttonGo;
	@FindBy(xpath=".//*[@id='result_0']/div//div[1]/a/span[contains(@class, 'price')]")
	WebElement firstProductPrice;
	
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifySearchInput() {
		String searchValue = searchInput.getAttribute("value");
		return searchValue;
	}
	
	public String clickOnBrandCheckbox(String brandName) {
		driver.findElement(By.xpath("//span[text()='"+brandName+"']//parent::span//preceding-sibling::input[@type='checkbox']")).click();	
		return driver.findElement(By.xpath("//span[@id='s-result-count']//span//descendant::a[contains(text(), '"+brandName+"')]")).getText();
	}
	
	public String enterPriceRange(String min, String max) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("low-price")));
		minPrice.sendKeys(min);
		maxPrice.sendKeys(max);
		buttonGo.click();
		return driver.findElement(By.xpath("//span[@id='s-result-count']//span//descendant::a[contains(text(), '"+min+"')]")).getText();
	}
	
	public String clickOnAvgCustomerRev(String avgRating) {
		driver.findElement(By.xpath("//span[text()='"+avgRating+"']")).click();
		return driver.findElement(By.xpath("//span[@id='s-result-count']//span//descendant::a[contains(text(), '4 Stars')]")).getText();
	}
	
	public ProductPage selectCheapestProduct() {
		int n = 0;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='atfResults']//ul//li[starts-with(@id, 'result')]")));
		int noOfProducts = driver.findElements(By.xpath(".//*[@id='atfResults']//ul//li[starts-with(@id, 'result')]")).size();
		String sFirstPrice = firstProductPrice.getText().trim();
		double firstPrice = Double.parseDouble(sFirstPrice);
		for(int i=1;i<noOfProducts;i++)
		{
			System.out.println(i);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='result_"+i+"']/div//div[1]/a/span[contains(@class, 'price')]")));
			WebElement product = driver.findElement(By.xpath(".//*[@id='result_"+i+"']/div//div[1]/a/span[contains(@class, 'price')]"));
			String sPrice = product.getText().trim();
			if(sPrice.length()<=3 )
			{
			double price = Double.parseDouble(sPrice);		
				if(firstPrice>price)
				{
					firstPrice = price;
					n = i;
				}
			}
		}
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath(".//*[@id='result_"+n+"']/div/div[2]/div/div/a/img")).click();
		for(String x : driver.getWindowHandles()) {
			if(!x.equalsIgnoreCase(parent)) {
				driver.switchTo().window(x);
			}
		}
		return new ProductPage();	
	}
}
