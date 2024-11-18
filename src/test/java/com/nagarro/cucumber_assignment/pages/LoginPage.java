package com.nagarro.cucumber_assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//span[normalize-space()='Account & Lists']")
	private WebElement accountAndListsMenu;

	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[contains(text(), 'Sign in')]")
	private WebElement signInButton;

	@FindBy(xpath = "//h1[contains(text(), 'Sign in')]")
	private WebElement signInHeader;

	@FindBy(xpath = "//input[@id='ap_email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@id='ap_password']")
	private WebElement password;

	@FindBy(id = "signInSubmit")
	private WebElement clickSignInButton;

	@FindBy(xpath = "//span[@class='a-list-item']")
	private WebElement wrongPassText;

	@FindBy(xpath = "//div[contains(text(),'Enter your email or mobile phone number')]")
	private WebElement emailRequired;

	@FindBy(xpath = "//div[contains(text(),'Enter your password')]")
	private WebElement passwordRequired;

	@FindBy(xpath = "//i[@class='a-icon a-icon-logo']")
	private WebElement loggedInIconLogo;

	// Method to hover on Account And List
	public void hoverAccountAndList() {
		Actions actions = new Actions(driver);
		waitForVisibility(accountAndListsMenu);
		actions.moveToElement(accountAndListsMenu).perform();
		logger.info("Hovered over 'Account & Lists' menu.");
	}

	// Method to click on "Sign-In" button
	public void clickSignIn() {
		clickElement(signInButton);
		isSignInPageDisplayed();
		logger.info("Clicked on 'Sign In' button.");
	}

	// Method to check if the Sign IN page has loaded
	public boolean isSignInPageDisplayed() {
		waitForVisibility(signInHeader);
		boolean isDisplayed = signInHeader.isDisplayed();
		logger.info("Sign-In page displayed: " + isDisplayed);
		return isDisplayed;
	}

	// Method to enter an email
	public void enterEmail(String email) {
		waitForVisibility(emailInput);
		emailInput.clear();
		emailInput.sendKeys(email);
		logger.info("Entered email: " + email);
	}

	// Method to click on continue after email input
	public void clickContinue() {
		clickElement(continueButton);
		logger.info("Clicked 'Continue' button.");
	}

	// Method to enter a password with parameterization
	public void enterPassword(String passwordText) {
		waitForVisibility(password);
		password.clear();
		password.sendKeys(passwordText);
		logger.info("Entered password.");
	}

	// Method to check if the "Sign In" button is visible
	public boolean isSignInButtonVisible() {
		waitForVisibility(signInButton);
		return signInButton.isDisplayed();
	}

	// Method to click on continue after email input
	public void clickAmazonSignIn() throws InterruptedException {
		clickElement(clickSignInButton);
		logger.info("Clicked 'Amazon Sign-In' button.");
		Thread.sleep(5000);
	}

	//Method to display message Password Wrong
	public boolean getAlertBoxText() throws InterruptedException {
		waitForVisibility(wrongPassText);
		Thread.sleep(2000);
		boolean isDisplayed = wrongPassText.isDisplayed();
		logger.info("Alert box displayed: " + isDisplayed);
		return isDisplayed;
	}

	// Method to check if the password required message is displayed
	public boolean isPasswordRequiredMessageDisplayed() throws InterruptedException {
		waitForVisibility(passwordRequired);
		Thread.sleep(2000);
		boolean isDisplayed = passwordRequired.isDisplayed();
		logger.info("Password required message displayed: " + isDisplayed);
		return isDisplayed;
	}

	// Method to check if the email required message is displayed
	public boolean isEmailRequiredMessageDisplayed() throws InterruptedException {
		waitForVisibility(emailRequired);
		Thread.sleep(2000);
		boolean isDisplayed = emailRequired.isDisplayed();
		logger.info("Email required message displayed: " + isDisplayed);
		return isDisplayed;
	}

	public boolean isLoggedIn() {
		waitForVisibility(loggedInIconLogo);
		return loggedInIconLogo.isDisplayed();
	}
}
