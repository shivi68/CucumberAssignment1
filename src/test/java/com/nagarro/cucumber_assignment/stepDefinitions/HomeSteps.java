package com.nagarro.cucumber_assignment.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.nagarro.cucumber_assignment.hooks.Hooks;
import com.nagarro.cucumber_assignment.pages.HomePage;
import com.nagarro.cucumber_assignment.utilities.ConfigReader;
import com.nagarro.cucumber_assignment.utilities.WebDriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps {

	private WebDriver driver;
	private HomePage homePage;
	private ConfigReader configReader;

	// Log4j2 Logger
	protected static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	// Constructor (Hooks will provide the WebDriver)
	public HomeSteps(Hooks hooks) {
		this.configReader = hooks.getConfigReader();
		this.driver = hooks.getDriver();
		this.homePage = hooks.getHomePage();
	}

	// To navigate to the Home Page
	@Given("I am on the Home Page")
	public void i_am_on_the_home_page() {
		String url = configReader.getUrl();
		logger.info("Navigating to URL: " + url);
		driver.get(url);
	}

	// Step To get the page title
	@When("I get the title of the page")
	public void i_get_the_title_of_the_page() {
		String title = homePage.getHomePageTitle();
		logger.info("Page title: " + title);
	}

	// Step to verify the page title
	@Then("The page title should be {string}")
	public void the_page_title_should_be(String expectedTitle) {
		String actualTitle = homePage.getHomePageTitle();
		logger.info("Verifying page title: Expected = " + expectedTitle + ", Actual = " + actualTitle);
		Assert.assertEquals(homePage.getHomePageTitle(), expectedTitle);
	}

	// Step to open the hamburger menu
	@When("I open the hamburger menu by clicking All")
	public void i_open_the_hamburger_menu_by_clicking_all() {
		logger.info("Opening the hamburger menu.");
		homePage.openHamburgerMenu();
	}

	// Step to verify if the hamburger menu is visible
	@Then("The hamburger menu should be visible")
	public void the_hamburger_menu_should_be_visible() {
		boolean isVisible = homePage.isMenuVisible();
		logger.info("Hamburger menu visibility: " + isVisible);
		Assert.assertTrue(isVisible, "The hamburger menu is not visible.");
	}

	// // Step to navigate to Customer Service page
	// @When("I click on customer service link")
	// public void i_click_on_customer_service_link() {
	// 	logger.info("Clicking on the customer service link.");
	// 	homePage.navigateToCustomerService();
	// }
	 // Step to navigate to Customer Service page
    @When("I click on customer service link")
    public void i_click_on_customer_service_link() {
        logger.info("Clicking on the customer service link.");
        homePage.closePopUpIfVisible(); // Close any pop-ups or overlays
        homePage.navigateToCustomerService(); // Attempt to navigate
    }

	@Then("I should be navigated to the Customer Service Page")
	public void i_should_be_navigate_to_the_customer_service_page() {
		boolean isCustomerServicePageDisplayed = homePage.isCustomerServicePageDisplayed();
		logger.info("Customer Service Page displayed: " + isCustomerServicePageDisplayed);
		Assert.assertTrue(isCustomerServicePageDisplayed, "The Customer Service Page is not displayed.");
	}

}
