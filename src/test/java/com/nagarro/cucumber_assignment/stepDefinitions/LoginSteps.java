package com.nagarro.cucumber_assignment.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.cucumber_assignment.hooks.Hooks;
import com.nagarro.cucumber_assignment.pages.LoginPage;
import com.nagarro.cucumber_assignment.utilities.ConfigReader;
import com.nagarro.cucumber_assignment.utilities.WebDriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private ConfigReader configReader;

	// Log4j2 Logger
	protected static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	// Constructor (Hooks will provide the WebDriver)
	public LoginSteps(Hooks hooks) {
		this.configReader = hooks.getConfigReader();
		this.driver = hooks.getDriver();
		this.loginPage = hooks.getLoginPage();
	}

	@Given("the user is on the Home Page")
	public void the_user_is_on_the_Home_Page() {
		String url = configReader.getUrl();
		logger.info("Navigating to URL: " + url);
		driver.get(url);
	}

	@When("the user hovers over the {string} menu")
	public void the_user_hovers_over_the_menu(String menu) {
		logger.info("Hovering over the " + menu + " menu");
		loginPage.hoverAccountAndList();
	}

	@Then("the {string} button should be visible and user clicks on the button")
	public void the_button_should_be_visible_and_click_on_button(String button) {
		logger.info("Verifying visibility of the " + button + " button.");
		Assert.assertTrue(loginPage.isSignInButtonVisible(), button + " button is not visible");
		loginPage.clickSignIn();
	}

	@Then("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		Assert.assertTrue(loginPage.isSignInPageDisplayed(), "Sign-In page is not displayed");
	}

	@When("the user enters an email {string}")
	public void the_user_enters_an_email(String email) {
		logger.info("Entering email: " + email);
		loginPage.enterEmail(email);
	}

	@And("the user clicks the Continue button")
	public void the_user_clicks_the_continue_button() {
		logger.info("Clicking the Continue button.");
		loginPage.clickContinue();
	}

	@When("the user enters a password {string}")
	public void the_user_enters_a_password(String password) {
			logger.info("Entering password.");
			loginPage.enterPassword(password);
	}

	@And("the user clicks the Amazon Sign-In button")
	public void the_user_clicks_the_amazon_sign_in_button() throws InterruptedException {
		logger.info("Clicking the Amazon Sign-In button.");
		loginPage.clickAmazonSignIn();
	}

	@Then("the user should be logged in successfully")
	public void the_user_should_be_logged_in_successfully() {
		logger.info("Verifying user login status.");
		Assert.assertTrue(loginPage.isLoggedIn(), "User was not logged in successfully");
	}

	@Then("an alert box with error message should be displayed")
	public void an_alert_box_with_error_message_should_be_displayed() throws InterruptedException {
		logger.info("Verifying if error message is displayed in the alert box.");
		Assert.assertTrue(loginPage.getAlertBoxText(), "Error message was not displayed");
	}

	@Then("an error message email required should be displayed")
	public void an_error_message_email_required_should_be_displayed() throws InterruptedException {
		logger.info("Verifying if email required error message is displayed.");
		Assert.assertTrue(loginPage.isEmailRequiredMessageDisplayed(), "Error message was not displayed");
	}

	@Then("an error message password required should be displayed")
	public void an_error_message_password_required_should_be_displayed() throws InterruptedException {
		logger.info("Verifying if password required error message is displayed.");
		Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed(), "Error message was not displayed");
	}
}
