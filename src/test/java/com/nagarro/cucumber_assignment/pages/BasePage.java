package com.nagarro.cucumber_assignment.pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.cucumber_assignment.utilities.ConfigReader;


public class BasePage {

	protected static final Logger logger = LogManager.getLogger(BasePage.class);

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected ConfigReader configReader;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.configReader = new ConfigReader();
		try {
			int globalWaitTime = configReader.getGlobalWaitTime();
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
			logger.info("WebDriver initialized with global wait time of {} seconds.", globalWaitTime);
		} catch (Exception e) {
			logger.error("Error initializing WebDriver wait time: {}", e.getMessage());
			throw e;
		}

		// Initialize page elements using PageFactory
		PageFactory.initElements(driver, this);
		logger.info("Page elements initialized using PageFactory.");
	}

	// Common wait method
	protected WebDriverWait getWait() {
		return wait;
	}

	// Generic method to wait and click on any element
	protected WebElement clickElement(WebElement element) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
			logger.info("Clicked on element: {}", element);
		} catch (Exception e) {
			logger.error("Failed to click on element: {}", element, e);
			throw e;
		}
		return element;
	}

	// Generic method to wait for visibility of any element
	protected WebElement waitForVisibility(WebElement element) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(element));
			logger.info("Element is visible: {}", element);
		} catch (Exception e) {
			logger.error("Element not visible after waiting: {}", element, e);
			throw e;
		}
		return element;
	}	
}

