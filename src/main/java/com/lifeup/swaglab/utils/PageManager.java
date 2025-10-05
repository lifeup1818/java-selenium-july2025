package com.lifeup.swaglab.utils;

import com.lifeup.swaglab.pages.CheckoutCompletePage;
import com.lifeup.swaglab.pages.CheckoutOverviewPage;
import com.lifeup.swaglab.pages.CheckoutYourInformationPage;
import com.lifeup.swaglab.pages.LoginPage;
import com.lifeup.swaglab.pages.ProductsPage;
import com.lifeup.swaglab.pages.Yourcart;

public class PageManager {

	public static LoginPage loginPage() {
		return new LoginPage(GlobalIdentifier.getDriver());
	}

	public static ProductsPage productsPage() {
		return new ProductsPage(GlobalIdentifier.getDriver());
	}
	
	public static Report report() {
		return new Report();
	}
	
	public static Yourcart yourCartPage() {
		return new Yourcart(GlobalIdentifier.getDriver());
	}
	
	public static CheckoutYourInformationPage checkoutYourInformationPage() {
		return new CheckoutYourInformationPage(GlobalIdentifier.getDriver());
	}
	
	public static CheckoutOverviewPage checkoutOverviewPage() {
		return new CheckoutOverviewPage(GlobalIdentifier.getDriver());
	}
	
	public static CheckoutCompletePage checkoutcompletePage() {
		return new CheckoutCompletePage(GlobalIdentifier.getDriver());
	}
}
