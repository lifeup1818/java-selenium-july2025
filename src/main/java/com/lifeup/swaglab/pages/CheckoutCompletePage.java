package com.lifeup.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class CheckoutCompletePage {
	WebDriver driver;
	WebUtils webUtils;

	@FindBy (xpath="//*[contains(text(),'Checkout: Complete')]")
	private WebElement titleCheckoutComplete;

	@FindBy(className = "complete-header")
	private WebElement headerComplete;

	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}
	
	public boolean isCheckoutCompletePageDisplayed() {
		return webUtils.isDisplayed(titleCheckoutComplete, "Title Checkout Complete");
	}
	
	public String getCompleteMessage() {
		return webUtils.getText(headerComplete, "Complete Header");
	}
}
