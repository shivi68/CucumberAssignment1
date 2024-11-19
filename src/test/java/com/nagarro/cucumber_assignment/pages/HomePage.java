package com.nagarro.cucumber_assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		// Calls BasePage constructor which initializes PageFactory
		super(driver);
	}

	@FindBy(id = "nav-hamburger-menu")
	private WebElement allMenu;

	@FindBy(xpath = "//a[normalize-space(text()) = 'Customer Service']")
	private WebElement customerServiceLink;

	@FindBy(xpath = "//h1[contains(text(), 'Hello. What can we help you with?')]")
	private WebElement customerServiceHeader;

	// Method to get Page Title
	public String getHomePageTitle() {
		logger.info("Getting home page title.");
		return driver.getTitle();
	}

	// Method to click on the hamburger menu
	public void openHamburgerMenu() {
		logger.info("Opening hamburger menu.");
		clickElement(allMenu);
	}

	public boolean isMenuVisible() {
		logger.info("Checking if hamburger menu is visible.");
		waitForVisibility(allMenu);
		boolean isVisible = allMenu.isDisplayed();
		logger.debug("Hamburger menu visibility: {}", isVisible);
		return isVisible;
	}

	// Method to click on the Customer Service Link
	public void navigateToCustomerService() {
		logger.info("Navigating to Customer Service page.");
		clickElement(customerServiceLink);
		if (isCustomerServicePageDisplayed()) {
			logger.info("Customer Service page is displayed successfully.");
		} else {
			logger.warn("Customer Service page is not displayed.");
		}
	}

	// Method to check if the Customer Service page has loaded
	public boolean isCustomerServicePageDisplayed() {
		logger.info("Checking if Customer Service page is displayed.");
		waitForVisibility(customerServiceHeader);
		boolean isDisplayed = customerServiceHeader.isDisplayed();
		logger.debug("Customer Service page visibility: {}", isDisplayed);
		return isDisplayed;
	}

}
