package com.lifeup.swaglab.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lifeup.swaglab.utils.WebUtils;

public class ProductsPage {
	WebDriver driver;
	WebUtils webUtils;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement burgerMenu;

	@FindBy(xpath = "//*[text()='Products']")
	private WebElement titleProducts;

	@FindBy(xpath = "//*[@class='inventory_item_name ']")
	private List<WebElement> productList;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement btnAddToCart_BackPack;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	public boolean isMenuBurgerIconDisplayed() {
		return webUtils.isDisplayed(burgerMenu, "Burger Menu");
	}

	public boolean isProductsTitleDisplayed() {
		return webUtils.isDisplayed(titleProducts, "Products Title");
	}

	public List<String> getProducts() {
		List<String> products = new ArrayList<String>();

		for (WebElement ele : productList) {
			String actualProduct = ele.getText();
			products.add(actualProduct);
		}
		return products;
	}

	public void clickOnTheAddCart_BackPack() {
		webUtils.click(btnAddToCart_BackPack, "Add To Cart");
	}

	public void clickOnTheShoppingCartLink() {
		webUtils.click(cartLink, "Shopping Cart Link");
	}

	public void addProductInShoppingCart(String prodcutName) {
		WebElement product = driver.findElement(By.xpath("//*[contains(text(),'" + prodcutName
				+ "')]/ancestor::div[@class='inventory_item_label']/following-sibling::div//button"));
		webUtils.click(product, prodcutName);
	}

}
