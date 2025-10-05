package com.lifeup.swaglab.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lifeup.swaglab.utils.GlobalIdentifier;
import com.lifeup.swaglab.utils.MyTestListener;
import com.lifeup.swaglab.utils.PageManager;

@Listeners(MyTestListener.class)
public class LoginTest extends BaseClassTest {
	String userName = GlobalIdentifier.getProperty("userName");
	String password = GlobalIdentifier.getProperty("password");

	@Test
	public void loginWithValidCredentials() {
		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();

		Assert.assertTrue(PageManager.productsPage().isMenuBurgerIconDisplayed(),
				"Burger Menu Option not displayed after login");
		System.out.println("Application log in successfully");
	}

	@Test
	public void loginWithValidUserNameAndInvalidPassword() {
		String password = "abcd@123";

		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();
		boolean errorMessage = PageManager.loginPage().isInvalidCredentilasErrorMessageDisplayed();

		// 2nd way
		Assert.assertTrue(errorMessage, "Application Loged in successfully with invalid credentilas");
		System.out.println("Test Case Pass: Application did not Log in successfully with invalid credentilas");

	}

	@Test
	public void loginWithInValidUserNameAndValidPassword() {
		String userName = "bhagwan123";

		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();
		boolean errorMessage = PageManager.loginPage().isInvalidCredentilasErrorMessageDisplayed();

		Assert.assertTrue(errorMessage, "Application Loged in successfully with invalid credentilas");
		System.out.println("Test Case Pass: Application did not Log in successfully with invalid credentilas");
	}

}
