package com.lifeup.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class LoginPage {
	WebDriver driver;
	WebUtils webUtils;

	@FindBy(id = "user-name")
	private WebElement txtUserName;

	@FindBy(id = "password")
	private WebElement txtPassword;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	@FindBy(xpath = "//*[contains(text(),'Username and password do not match any user in this service')]")
	private WebElement errorMessage;

	@FindBy(className = "login_logo")
	private WebElement loginLogo;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	public void enterUserName(String userName) {
		webUtils.sendKeys(txtUserName, userName, "User Name");
	}

	public void enterPassword(String password) {
		webUtils.sendKeys(txtPassword, password, "Password");
	}

	public void clickOnTheLoginButton() {
		webUtils.click(btnLogin, "Login");
	}

	public boolean isInvalidCredentilasErrorMessageDisplayed() {
		return webUtils.isDisplayed(errorMessage, "Error Message Invalid Credentilas");
	}

	public String getLoginLogo() {
		return webUtils.getText(loginLogo, "Login Logo");
	}

}
