package com.lifeup.swaglab.utils;

import java.nio.channels.SelectableChannel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebUtils {
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
			System.out.println("Clicked on the element:" + elementName);
		} catch (Exception e) {
			System.out.println("Not clicked on the element:" + elementName);
			throw new Error("Not clicked on the element:" + elementName);
		}
	}

	public void clickByAction(WebElement element, String elementName) {
		try {
			Actions actions = new Actions(driver);
			actions.click(element).build().perform();
			System.out.println("Clicked on the element:" + elementName);
		} catch (Exception e) {
			System.out.println("Not clicked on the element:" + elementName);
			throw new Error("Not clicked on the element:" + elementName);
		}
	}

	public void sendKeys(WebElement element, String value, String elementName) {
		try {
			element.sendKeys(value);
			System.out.println("Value entered in " + elementName + ":" + value);
		} catch (Exception e) {
			System.out.println("Value not entered in " + elementName + ":" + value);
			throw new Error("Value not entered in " + elementName + ":" + value);
		}
	}

	public String getText(WebElement element, String elementName) {
		try {
			String text = element.getText();
			System.out.println("Captured text for " + elementName + " is" + text);
			return text;
		} catch (Exception e) {
			System.out.println("Not captured text for " + elementName);
			throw new Error("Not captured text for " + elementName);
		}
	}
	
	public boolean isDisplayed(WebElement element, String elementName) {
		try {
			boolean vp = element.isDisplayed();
			System.out.println(elementName+" Element Displayed="+vp);
			return vp;
		} catch (Exception e) {
			System.out.println(elementName+" Element not Displayed");
			throw new Error(elementName+" Element not Displayed");
		}
	}
	
	public void selectByValue(WebElement element, String value,String elementName) {
		try {
			select= new Select(element);
			select.selectByValue(value);
			System.out.println(value+" :Value Selected In Element:"+elementName);
		} catch (Exception e) {
			System.out.println(value+" :Value Not Selected In Element:"+elementName);
			throw new Error(value+" :Value Not Selected In Element:"+elementName);
		}	
	}
	
	public void selectByVisibleText(WebElement element, String value,String elementName) {
		try {
			select= new Select(element);
			select.selectByVisibleText(value);
			System.out.println(value+" :Value Selected In Element:"+elementName);
		} catch (Exception e) {
			System.out.println(value+" :Value Not Selected In Element:"+elementName);
			throw new Error(value+" :Value Not Selected In Element:"+elementName);
		}	
	}
	
	public void selectByIndex(WebElement element, int index,String elementName) {
		try {
			select= new Select(element);
			select.selectByIndex(index);
			System.out.println(index+" :Value Selected In Element:"+elementName);
		} catch (Exception e) {
			System.out.println(index+" :Value Not Selected In Element:"+elementName);
			throw new Error(index+" :Value Not Selected In Element:"+elementName);
		}	
	}
	
	public void scrollToElemet(WebElement element, String elementName) {
		try {
			Actions actions= new Actions(driver);
			actions.scrollToElement(element).build().perform();
			System.out.println("Element scrolled successfully to:"+elementName);
		} catch (Exception e) {
			System.out.println("Element Not scrolled successfully to:"+elementName);
			throw new Error("Element Not scrolled successfully to:"+elementName);
		}
	}

}
