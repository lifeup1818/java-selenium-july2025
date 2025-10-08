package com.lifeup.swaglab.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lifeup.swaglab.utils.ExcelUtils;
import com.lifeup.swaglab.utils.GlobalIdentifier;
import com.lifeup.swaglab.utils.MyTestListener;
import com.lifeup.swaglab.utils.PageManager;

@Listeners(MyTestListener.class)
public class ProductsTest extends BaseClassTest {
	String userName = GlobalIdentifier.getProperty("userName");
	String password = GlobalIdentifier.getProperty("password");

	@Test
	public void verifyProductsTitle() {
		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();

		boolean isProductsTitleDisplayed = PageManager.productsPage().isProductsTitleDisplayed();
		Assert.assertTrue(isProductsTitleDisplayed, "Products title not displayed after login");
		System.out.println("Test Case Pass: Products title displayed after login");
	}

	@Test
	public void verifyProducts() {
		List<String> expectedProducts = new ArrayList<String>();
		expectedProducts.add("Sauce Labs Backpack");
		expectedProducts.add("Sauce Labs Bike Light");
		expectedProducts.add("Sauce Labs Bolt T-Shirt");
		expectedProducts.add("Sauce Labs Fleece Jacket");
		expectedProducts.add("Sauce Labs Onesie");
		expectedProducts.add("Test.allTheThings() T-Shirt (Red)");

		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();

		Assert.assertTrue(PageManager.productsPage().isProductsTitleDisplayed(), "Products page not found after login");

		List<String> actualProducts = PageManager.productsPage().getProducts();
		Assert.assertEquals(actualProducts, expectedProducts);

		System.out.println("Test Case Pass: Products Verified Successfully");
	}

	@DataProvider(name = "getproducts")
	public Object[][] getProductsFromExcel() {
		return ExcelUtils.readExcelData("Products", "VerifyProductsInShoppingCart");
	}

	@Test(dataProvider = "getproducts")
	public void VerifyProdcutsAddedOrNotInShoppingCart(String productName) {
		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();

		Assert.assertTrue(PageManager.productsPage().isProductsTitleDisplayed(), "Products page not found after login");
		System.out.println("Application logged in successfully");

		PageManager.productsPage().addProductInShoppingCart(productName);
		PageManager.productsPage().clickOnTheShoppingCartLink();

		boolean isYourCartPageDisplayed = PageManager.yourCartPage().isYourCartPageDisplayed();

		Assert.assertEquals(isYourCartPageDisplayed, true, "Your cart page not found");
		System.out.println("Your cart page displayed");

		boolean item = PageManager.yourCartPage().isProdcutItemDisplayed(productName);

		Assert.assertTrue(item, productName + " product not added in shopping cart");
		System.out.println(productName + " product added in shopping cart");
	}

	@DataProvider(name = "checkout")
	public Object[][] checkout() {
		return ExcelUtils.readExcelDataInKeyValuePair("Products", "CheckoutProdcut");
	}

	@Test(dataProvider = "checkout")
	public void checkoutProduct(Map<String, String> data) {
		System.out.println(">>>>>>>>>:" + data.get("SCN_ID"));
		String productName = data.get("ProductName");
		String firstName = data.get("FirstName");
		String lastName = data.get("LastName");
		String zipCode = data.get("ZipCode");
		String expectedCompleteMessage = data.get("CompleteMessage");
		/**
		 * Login Page
		 */
		PageManager.loginPage().enterUserName(userName);
		PageManager.loginPage().enterPassword(password);
		PageManager.loginPage().clickOnTheLoginButton();
		/**
		 * Products Page
		 */
		Assert.assertTrue(PageManager.productsPage().isProductsTitleDisplayed(), "Products page not found after login");
		System.out.println("Application logged in successfully");

		PageManager.productsPage().addProductInShoppingCart(productName);
		PageManager.productsPage().clickOnTheShoppingCartLink();

		/**
		 * Your cart Page
		 */
		boolean isYourCartPageDisplayed = PageManager.yourCartPage().isYourCartPageDisplayed();

		Assert.assertEquals(isYourCartPageDisplayed, true, "Your cart page not found");
		System.out.println("Your cart page displayed");

		boolean item = PageManager.yourCartPage().isProdcutItemDisplayed(productName);
		Assert.assertTrue(item, productName + " product not added in shopping cart");

		PageManager.yourCartPage().clickOnTheCheckoutButton();

		/**
		 * Checkout Your Information Page
		 */
		boolean isCheckoutInformationPageDisplayed = PageManager.checkoutYourInformationPage()
				.isDisplayedCheckoutYourInformationPage();
		Assert.assertTrue(isCheckoutInformationPageDisplayed, "Checkout Your Information Page not displayed");

		PageManager.checkoutYourInformationPage().enterFirstName(firstName);
		PageManager.checkoutYourInformationPage().enterLastName(lastName);
		PageManager.checkoutYourInformationPage().enterZipCode(zipCode);

		PageManager.checkoutYourInformationPage().clickOnTheContinueButton();

		/**
		 * Checkout Overview Page
		 */
		PageManager.checkoutOverviewPage().clickOnTheFinishButton();

		/**
		 * Checkout Complete Page
		 */
		boolean isCheckoutCompletePageDisplayed = PageManager.checkoutcompletePage().isCheckoutCompletePageDisplayed();
		Assert.assertTrue(isCheckoutCompletePageDisplayed, "Checkout Complete Page not displayed");

		String actualSuccessMessage = PageManager.checkoutcompletePage().getCompleteMessage();

		Assert.assertEquals(actualSuccessMessage, expectedCompleteMessage);
	}

	@Test
	public void demo(String data) {

	}

}
