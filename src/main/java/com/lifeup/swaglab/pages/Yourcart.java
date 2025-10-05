package com.lifeup.swaglab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class Yourcart {
	WebDriver driver;
	WebUtils webUtils;

	@FindBy(xpath = "//*[text()='Your Cart']")
	private WebElement titleYourCart;

	@FindBy(xpath = "//*[text()='Sauce Labs Backpack']")
	private WebElement titleBackPack;

	@FindBy(xpath = "//button[@id='checkout']")
	private WebElement btnCheckout;

	public Yourcart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	public boolean isYourCartPageDisplayed() {
		return webUtils.isDisplayed(titleYourCart, "Your Cart Title");
	}

	public boolean isBackPackItemDisplayed() {
		return webUtils.isDisplayed(titleBackPack, "Title BackPack");
	}

	public boolean isProdcutItemDisplayed(String productName) {
		WebElement product = driver.findElement(By.xpath("//*[text()='" + productName + "']"));
		return webUtils.isDisplayed(product, productName);
	}

	public void clickOnTheCheckoutButton() {
		webUtils.clickByAction(btnCheckout, "Checkout button");
	}

}
