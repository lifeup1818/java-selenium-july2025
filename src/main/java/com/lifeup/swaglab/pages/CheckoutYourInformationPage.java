package com.lifeup.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class CheckoutYourInformationPage {
	WebDriver driver;
	WebUtils webUtils;

	@FindBy(xpath = "//*[contains(text(),'Checkout: Your Information')]")
	private WebElement titleCheckoutYourInformation;

	@FindBy(id = "first-name")
	private WebElement txtFirstName;

	@FindBy(id = "last-name")
	private WebElement txtLastName;

	@FindBy(id = "postal-code")
	private WebElement txtZipCode;

	@FindBy(id = "continue")
	private WebElement btnContinue;

	public CheckoutYourInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	public boolean isDisplayedCheckoutYourInformationPage() {
		try {
			return titleCheckoutYourInformation.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void enterFirstName(String firstName) {
		webUtils.sendKeys(txtFirstName, firstName, "First Name");
	}

	public void enterLastName(String lastName) {
		webUtils.sendKeys(txtLastName, lastName, "Last Name");
	}

	public void enterZipCode(String zipCode) {
		webUtils.sendKeys(txtZipCode, zipCode, "Zip Code");
	}

	public void clickOnTheContinueButton() {
		webUtils.click(btnContinue, "Continue button");
	}

}
