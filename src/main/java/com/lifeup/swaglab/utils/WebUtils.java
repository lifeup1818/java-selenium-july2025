package com.lifeup.swaglab.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebUtils {

	private final Logger log = LogManager.getLogger(WebUtils.class);

	WebDriver driver;
	Select select;

	public WebUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void click(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click(WebElement element, String elementName) {
		try {
			element.click();
			Report.info("Clicked on the element:" + elementName);
			log.info("Clicked on the element:" + elementName);
		} catch (Exception e) {
			Report.info("Not clicked on the element:" + elementName);
			log.info("Not clicked on the element:" + elementName);
			throw new Error("Not clicked on the element:" + elementName);
		}
	}

	public void clickByAction(WebElement element, String elementName) {
		try {
			Actions actions = new Actions(driver);
			actions.click(element).build().perform();
			Report.info("Clicked on the element:" + elementName);
			log.info("Clicked on the element:" + elementName);
		} catch (Exception e) {
			Report.info("Not clicked on the element:" + elementName);
			log.info("Not clicked on the element:" + elementName);
			throw new Error("Not clicked on the element:" + elementName);
		}
	}

	public void sendKeys(WebElement element, String value, String elementName) {
		try {
			element.sendKeys(value);
			Report.info("Value entered in " + elementName + ":" + value);
			log.info("Value entered in " + elementName + ":" + value);
		} catch (Exception e) {
			Report.info("Value not entered in " + elementName + ":" + value);
			log.info("Value not entered in " + elementName + ":" + value);
			throw new Error("Value not entered in " + elementName + ":" + value);
		}
	}

	public String getText(WebElement element, String elementName) {
		try {
			String text = element.getText();
			Report.info("Captured text for " + elementName + " is" + text);
			log.info("Captured text for " + elementName + " is" + text);
			return text;
		} catch (Exception e) {
			Report.info("Not captured text for " + elementName);
			log.info("Not captured text for " + elementName);
			throw new Error("Not captured text for " + elementName);
		}
	}

	public boolean isDisplayed(WebElement element, String elementName) {
		try {
			boolean vp = element.isDisplayed();
			Report.info(elementName + " Element Displayed=" + vp);
			log.info(elementName + " Element Displayed=" + vp);
			return vp;
		} catch (Exception e) {
			Report.info(elementName + " Element not Displayed");
			log.info(elementName + " Element not Displayed");
			throw new Error(elementName + " Element not Displayed");
		}
	}

	public void selectByValue(WebElement element, String value, String elementName) {
		try {
			select = new Select(element);
			select.selectByValue(value);
			Report.info(value + " :Value Selected In Element:" + elementName);
			log.info(value + " :Value Selected In Element:" + elementName);
		} catch (Exception e) {
			Report.info(value + " :Value Not Selected In Element:" + elementName);
			log.info(value + " :Value Not Selected In Element:" + elementName);
			throw new Error(value + " :Value Not Selected In Element:" + elementName);
		}
	}

	public void selectByVisibleText(WebElement element, String value, String elementName) {
		try {
			select = new Select(element);
			select.selectByVisibleText(value);
			Report.info(value + " :Value Selected In Element:" + elementName);
			log.info(value + " :Value Selected In Element:" + elementName);
		} catch (Exception e) {
			Report.info(value + " :Value Not Selected In Element:" + elementName);
			log.info(value + " :Value Not Selected In Element:" + elementName);
			throw new Error(value + " :Value Not Selected In Element:" + elementName);
		}
	}

	public void selectByIndex(WebElement element, int index, String elementName) {
		try {
			select = new Select(element);
			select.selectByIndex(index);
			Report.info(index + " :Value Selected In Element:" + elementName);
			log.info(index + " :Value Selected In Element:" + elementName);
		} catch (Exception e) {
			Report.info(index + " :Value Not Selected In Element:" + elementName);
			log.info(index + " :Value Not Selected In Element:" + elementName);
			throw new Error(index + " :Value Not Selected In Element:" + elementName);
		}
	}

	public void scrollToElemet(WebElement element, String elementName) {
		try {
			Actions actions = new Actions(driver);
			actions.scrollToElement(element).build().perform();
			log.info("Element scrolled successfully to:" + elementName);
			Report.info("Element scrolled successfully to:" + elementName);
		} catch (Exception e) {
			Report.info("Element Not scrolled successfully to:" + elementName);
			log.info("Element Not scrolled successfully to:" + elementName);
			throw new Error("Element Not scrolled successfully to:" + elementName);
		}
	}

}
