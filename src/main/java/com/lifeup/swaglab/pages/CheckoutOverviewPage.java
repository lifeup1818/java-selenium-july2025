package com.lifeup.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class CheckoutOverviewPage {

	WebDriver driver;
	WebUtils webUtils;

	@FindBy(id = "finish")
	private WebElement btnFinish;

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}
	
	public void clickOnTheFinishButton() {
		webUtils.click(btnFinish,"Finish Button");
	}

}
